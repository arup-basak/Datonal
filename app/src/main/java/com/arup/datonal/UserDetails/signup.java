package com.arup.datonal.UserDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arup.datonal.MainActivity;
import com.arup.datonal.MainPage;
import com.arup.datonal.R;
import com.arup.datonal.data.StringData;
import com.arup.datonal.validator.AllInputValidator;
import com.arup.datonal.data.BasicDeclaration;
import com.arup.datonal.data.begin;
import com.arup.datonal.password.PasswordMatch.Make;
import com.arup.datonal.password.TextToNumber.convert;

import com.arup.datonal.validator.PinOrPassValidator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class signup extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    CheckBox saveForLetter;

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        TextView signIn = findViewById(R.id.trySignIn);

        EditText username = findViewById(R.id.signupUser);
        EditText passNum = findViewById(R.id.signupPassUser);
        EditText passText = findViewById(R.id.signupTextPassword);
        EditText phone = findViewById(R.id.signupPhoneNo);
        EditText postal = findViewById(R.id.signupPostal);
        EditText dob = findViewById(R.id.signupDob);


        TextView userWarn = findViewById(R.id.userWarnSignup);
        TextView passWarn = findViewById(R.id.signUpNumWarn);
        TextView passTextWarn = findViewById(R.id.passWarnSignup);
        TextView phoneWarn = findViewById(R.id.phoneWarnSignup);
        TextView pinWarn = findViewById(R.id.pinWarnSignup);
        TextView dobWarn = findViewById(R.id.dobWarnSignup);

        username.setText("arup003");
        passNum.setText("9932488107");
        phone.setText("9932488107");
        postal.setText("713519");
        dob.setText("24/12/2003");
        passText.setText("Aditya003%");

        saveForLetter = findViewById(R.id.saveForLater);

        dob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String initial = s.toString();
                String processed;
                if((s.length() == 2 || s.length() == 5) && s.toString().split("/").length < 3) {
                    processed = initial + "/";
                    s.replace(0, initial.length(), processed);
                }
            }
        });

        dob.setOnKeyListener((v, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_DEL) {
                dob.setText("");
            }
            return false;
        });


        findViewById(R.id.signUp).setOnClickListener(v -> {
            String user = username.getText().toString();
            String num = passNum.getText().toString();
            String text = passText.getText().toString();
            String ph = phone.getText().toString();
            String pi = postal.getText().toString();
            String d = dob.getText().toString();

            if(!AllInputValidator.UserName(user).equals("")) {
                SetMessageView(userWarn, AllInputValidator.UserName(user));
            }
            else if(!PinOrPassValidator.pin(num).equals("")) {
                SetMessageView(passWarn, PinOrPassValidator.pin(num));
            }
            else if(!PinOrPassValidator.password(text).equals("")) {
                SetMessageView(passTextWarn, PinOrPassValidator.password(text));
            }
            else if(!AllInputValidator.PostalCode(ph).equals("")) {
                SetMessageView(pinWarn, AllInputValidator.PostalCode(ph));
            }
            else if(!AllInputValidator.PhoneNumber(ph).equals("")) {
                SetMessageView(phoneWarn, AllInputValidator.PhoneNumber(ph));
            }
            else if(!AllInputValidator.DateValidator(d).equals("")) {
                SetMessageView(dobWarn, AllInputValidator.DateValidator(d));
            }
            else {
                convert c = new convert();
                String key = c.It(text, ph, pi, d, BasicDeclaration.totalLength);
                MainActivity.username = user;
                MainActivity.pin = num;
                SignUpProcess(key);
            }
        });

        signIn.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext() , login.class);
            intent1.putExtra("getLogInIdFromSignUp" , username.getText().toString());
            startActivity(intent1);
        });
    }
    public String[] EncData(String key) { //user, pin, key
        String[] newData = new String[4];
        newData[0] = begin.EncDec(MainActivity.username, BasicDeclaration.getPin(), false, true);//username
        newData[1] = begin.EncDec(Make.It(MainActivity.pin), Long.parseLong(key), true, false);//For Recovery
        newData[2] = begin.EncDec(Make.It(MainActivity.pin), Long.parseLong(MainActivity.pin), true, true);//PIN
        return newData;
    }

    public void SignUpProcess(String key) {
        Toast.makeText(this, "Sign", Toast.LENGTH_SHORT).show();
        MainActivity.myRef = MainActivity.database.getReference();
        MainActivity.myRef.child("Encryption").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshotEnc) {
                MainActivity.EncKey = (String) snapshotEnc.child("enc" + convert.EncKeyChooser(MainActivity.username)).getValue();
                String[] allDataWithEnc = EncData(key);
                MainActivity.myRef = MainActivity.myRef.child(MainActivity.path).child(allDataWithEnc[0]);
                MainActivity.myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(!snapshot.exists()) {
                            createNewUser(allDataWithEnc);
                        }
                        else {
                            Toast.makeText(signup.this, "Username Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(signup.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(signup.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createNewUser(String[] data) {
        MainActivity.map = new HashMap<>();
        MainActivity.map.put(BasicDeclaration.KEY_ATTEMPTS, "0");
        MainActivity.map.put(BasicDeclaration.KEY_PIN, data[2]);
        MainActivity.map.put(BasicDeclaration.KEY_PIN_RECOVERY, data[1]);
        MainActivity.map.put(BasicDeclaration.KEY_LOGIN_TIME, begin.getTime());
        MainActivity.map.put(BasicDeclaration.KEY_DATA, "");

        MainActivity.myRef.setValue(MainActivity.map)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(signup.this, "Sign Up Succeed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signup.this, MainPage.class);
                    intent.putExtra("previousActivity", "signup");
                    startActivity(intent);
                })
                .addOnFailureListener(e -> Toast.makeText(signup.this, "Sign UP not Succeed", Toast.LENGTH_SHORT).show());
    }


    private String getBlank() {
        StringBuilder str = new StringBuilder("-");
        for(int i = 0 ; i < 9 ; i++) {
            str.append("!").append("-");
        }
        return String.valueOf(str);
    }

    private void SetMessageView(TextView view, String message) {
        view.setText(message);
        view.setVisibility(View.VISIBLE);
    }
}