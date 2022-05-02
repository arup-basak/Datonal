package com.arup.datonal.UserDetails;

import static com.arup.datonal.UserDetails.signup.MyPREFERENCES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arup.datonal.MainActivity;
import com.arup.datonal.MainPage;
import com.arup.datonal.R;
import com.arup.datonal.data.BasicDeclaration;
import com.arup.datonal.data.StringData;
import com.arup.datonal.data.begin;
import com.arup.datonal.password.TextToNumber.convert;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class login extends AppCompatActivity {

    EditText user;
    EditText pass;

    TextView passWarn;
    TextView userWarn;

    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        Button signIn = findViewById(R.id.SignIn);

        user = findViewById(R.id.userName);
        pass = findViewById(R.id.password);
        userWarn = findViewById(R.id.userWarningLogin);
        passWarn = findViewById(R.id.passWarningLogin);



        try{
            Intent intent = getIntent();
            user.setText(intent.getStringExtra("ThePinFromForget"));
        }
        catch (Exception ignored) {}

        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        try{
            user.setText(sharedPreferences.getString("username", null));
        }
        catch (Exception ignored) {}

        signIn.setOnClickListener(v -> {
            String username = user.getText().toString();
            String password = pass.getText().toString();

            if(username.equals("")) {
                userWarn.setText(R.string.please_enter_user_name_it_is_mandatory);
                userWarn.setVisibility(View.VISIBLE);
            }
            else if(password.equals("")) {
                passWarn.setText(R.string.please_enter_password_it_is_mandatory);
                passWarn.setVisibility(View.VISIBLE);
            }
            else{
                MainActivity.username = username;
                MainActivity.pin = password;
                readData();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        userWarn.setVisibility(View.INVISIBLE);
        passWarn.setVisibility(View.INVISIBLE);
        return false;
    }

    public void signupIntent(View view) {
        Intent intent = new Intent(this , signup.class);
        startActivity(intent);
    }

    public void forgetIntent(View view) {
        Intent intent = new Intent(this , forget.class);
        startActivity(intent);
    }

    private void readData() {
        final String[] username = {MainActivity.username};
        String pin = MainActivity.pin;

        Intent intent = new Intent(login.this, MainPage.class);


        final String[] finalUsername = {username[0]};
        MainActivity.myRef = MainActivity.database.getReference();
        MainActivity.myRef.child("Encryption").addValueEventListener(new ValueEventListener() { //Load Enc
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotEnc) {
                if(snapshotEnc.exists()) {
                    String chi = "enc" + convert.EncKeyChooser(finalUsername[0]);
                    MainActivity.EncKey = (String) snapshotEnc.child(chi).getValue();
                    finalUsername[0] = begin.EncDec(finalUsername[0], BasicDeclaration.getPin(), false, true);
                    MainActivity.myRef = MainActivity.database.getReference(MainActivity.path + finalUsername[0]);
                    Toast.makeText(login.this, finalUsername[0], Toast.LENGTH_SHORT).show();

                    MainActivity.myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {


                                String loadedPin = (String) snapshot.child(BasicDeclaration.KEY_PIN).getValue();
                                String data = (String) snapshot.child(BasicDeclaration.KEY_DATA).getValue();
                                String attempts = (String) snapshot.child(BasicDeclaration.KEY_ATTEMPTS).getValue();
                                loadedPin = begin.EncDec(loadedPin, Long.parseLong(pin), false, true);
                                data = begin.EncDec(data, Long.parseLong(pin), true, false);
                                String warn = "Problem";
                                if(loadedPin.contains(pin)) {
                                    MainActivity.map = StringData.DataSnapshotToMap(snapshot);
                                    //addLogInTime();
                                    MainPage.dataString = data;
                                    intent.putExtra("previousActivity", "login");
                                    pass.setText("");
                                    startActivity(intent);
                                }
                                else {
                                    passWarn.setVisibility(View.VISIBLE);
                                    try {
                                        int left = 0;
                                        if (attempts != null) {
                                            left = 5 - Integer.parseInt(attempts);
                                        }
                                        warn = R.string.mismatched_password + ", " + left + "attempts left";
                                    }
                                    catch (Exception e) {

                                    }
                                    passWarn.setText(warn);
                                    pass.setText("");
                                }
                            }
                            else {
                                userWarn.setText(R.string.mismatched_password_username);
                                userWarn.setVisibility(View.VISIBLE);
                                pass.setText("");
                                user.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            passWarn.setVisibility(View.VISIBLE);
                            passWarn.setText(R.string.mismatched_password);
                            pass.setText("");
                        }
                    });
                }
                else {
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static long getDateDiff(String oldDate, String newDate) {
        Date curr = Calendar.getInstance().getTime();
        /*String formatStr = "DD-MM-YYYY HH:MM:SS";
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }*/
        return 5;
    }

    private void addLogInTime() {//Problem
        MainActivity.map.put(BasicDeclaration.KEY_LOGIN_TIME, MainActivity.map.get(BasicDeclaration.KEY_LOGIN_TIME) + " | " + begin.getTime());
        //databaseReference.setValue(map);
    }
}