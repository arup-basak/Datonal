package com.arup.datonal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.arup.datonal.UserDetails.login;
import com.arup.datonal.UserDetails.signup;
import com.arup.datonal.validator.PinOrPassValidator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;
    public static FirebaseDatabase database;
    public static DatabaseReference myRef;
    public static Map<String, Object> map;


    public static String EncKey;
    public static String path;


    public static String username;
    public static String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loadAllDataAndOpenLogInOrSignUpPage();
    }

    private void loadAllDataAndOpenLogInOrSignUpPage() {
        database = FirebaseDatabase.getInstance("https://datonal-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference personalRef = database.getReference();
        personalRef.child("BasicDeclaration").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    PinOrPassValidator.specialCharacter = (String) snapshot.child("specialCharacter").getValue();
                    MainActivity.path = (String) snapshot.child("path").getValue();
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    openActivity();
                                }
                            },
                            1500
                    );
                    
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void openActivity() {
       sharedPreferences = getPreferences(MainActivity.MODE_PRIVATE);
       sharedEditor = sharedPreferences.edit();
       Intent intent;
       if(isItFirstTime()) {
           intent = new Intent(this, signup.class);
       }
       else {
           intent = new Intent(this, login.class);
       }
       startActivity(intent);
        //startActivity(new Intent(MainActivity.this, SmartViewActivity.class));
    }


    private boolean isItFirstTime() {
        if (sharedPreferences.getBoolean("firstTime", true)) {
            sharedEditor.putBoolean("firstTime", false);
            sharedEditor.commit();
            sharedEditor.apply();
            return true;
        } else {
            return false;
        }
    }
}