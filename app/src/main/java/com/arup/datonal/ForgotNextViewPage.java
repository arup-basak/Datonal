package com.arup.datonal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arup.datonal.UserDetails.login;

public class ForgotNextViewPage extends AppCompatActivity {
    TextView pinView;

    public static String pin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_next_view_page);

        try{
            pinView = findViewById(R.id.forgettable_pin);
            pinView.setText(pin);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        findViewById(R.id.loginIntent).setOnClickListener(v -> startActivity(new Intent(ForgotNextViewPage.this, login.class)));

        findViewById(R.id.change).setOnClickListener(v -> showDialog(this));
    }

    public void showDialog(Context context) {
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_change_password, null);

        EditText pin = view.findViewById(R.id.pinFromChangePin);
        EditText re_pin = view.findViewById(R.id.reenterpin);


        alert.setView(view);
        alert.setCancelable(false);




        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();


        view.findViewById(R.id.changePIN).setOnClickListener(v -> {
            String newPin = pin.getText().toString();
            String re_newPin = re_pin.getText().toString();

            if(newPin.equals(re_newPin)) {
                Toast.makeText(ForgotNextViewPage.this, "PIN Changed", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(ForgotNextViewPage.this, "PIN Does Not Matched", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.close).setOnClickListener(v -> alertDialog.cancel());

    }

    public void copy_pin(View view) {
        Toast.makeText(this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("PIN", pinView.getText().toString());
        clipboard.setPrimaryClip(clip);
    }
}