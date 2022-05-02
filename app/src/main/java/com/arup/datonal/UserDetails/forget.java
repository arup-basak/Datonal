package com.arup.datonal.UserDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arup.datonal.ForgotNextViewPage;
import com.arup.datonal.MainActivity;
import com.arup.datonal.R;
import com.arup.datonal.data.BasicDeclaration;
import com.arup.datonal.data.begin;
import com.arup.datonal.password.PasswordMatch.Make;
import com.arup.datonal.password.TextToNumber.convert;

public class forget extends AppCompatActivity {

    public void startForgot(String key, String username) {
        String user = username;
        username = begin.EncDec(username, BasicDeclaration.getPin(), false, true);

        MainActivity.myRef = MainActivity.database.getReference(MainActivity.path + username);
        MainActivity.myRef.get()
                .addOnSuccessListener(snapshot -> {
                    if(snapshot.exists()) {
                        String passData = (String) snapshot.child(BasicDeclaration.KEY_PIN_RECOVERY).getValue();

                        String pin = Make.Break(passData, key);
                        String loadedPin = (String) snapshot.child(BasicDeclaration.KEY_PIN).getValue();
                        loadedPin = begin.EncDec(loadedPin, Long.parseLong(pin), false, true);
                        if(loadedPin.contains(pin)) {
                            ForgetNextProcess(user, pin);
                        }
                        else {
                            Toast.makeText(forget.this, "Details Does Not Matched", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(forget.this, "Username Does Not Exist", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        Button tryOut = findViewById(R.id.signup);
        ImageView backButton = findViewById(R.id.back_button_forget);
        EditText user = findViewById(R.id.ForgetUser);
        EditText textPass = findViewById(R.id.forgetTextPassword);
        EditText phone = findViewById(R.id.ForgetPhoneNo);
        EditText pin = findViewById(R.id.ForgetPin);
        EditText dob = findViewById(R.id.ForgetDob);

        TextView userWarn = findViewById(R.id.userWarnForgot);
        TextView passWarn = findViewById(R.id.passWarnForgot);
        TextView phoneWarn = findViewById(R.id.phoneWarnForgot);
        TextView pinWarn = findViewById(R.id.pinWarnForgot);
        TextView dobWarn = findViewById(R.id.dobWarnForgot);

        tryOut.setOnClickListener(v -> {
            convert c = new convert();
            String key = c.It(textPass.getText().toString(), phone.getText().toString(), pin.getText().toString(), dob.getText().toString(), BasicDeclaration.totalLength);
            if(user.getText().toString().equals("")) {
                userWarn.setVisibility(View.VISIBLE);
            }
            else if(textPass.getText().toString().equals("")){
                passWarn.setVisibility(View.VISIBLE);
            }
            else if(phone.getText().toString().equals("")){
                phoneWarn.setVisibility(View.VISIBLE);
            }
            else if(pin.getText().toString().equals("")){
                pinWarn.setVisibility(View.VISIBLE);
            }
            else if(dob.getText().toString().equals("")){
                dobWarn.setVisibility(View.VISIBLE);
            }
            else{
                startForgot(key, user.getText().toString());
            }
        });

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
        backButton.setOnClickListener(v -> startActivity(new Intent(forget.this, login.class)));
    }
    private void ForgetNextProcess(String username, String pin) {
        Intent intent = new Intent(this, ForgotNextViewPage.class);
        ForgotNextViewPage.pin = pin;
        intent.putExtra("UserNameFromForgot", username);
        intent.putExtra("ThePinFromForget", pin);
        startActivity(intent);
    }
}