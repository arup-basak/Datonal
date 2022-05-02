package com.arup.datonal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.arup.datonal.data.BasicDeclaration;
import com.arup.datonal.data.StringData;
import com.arup.datonal.data.begin;

import java.time.LocalDate;

public class passwordAdd extends AppCompatActivity {
    EditText heading, username, password, pin, card, CVV, m, y, address, note, bank, ifsc, phone;
    TextView titleWarn, cardWarn, cvvWarn, pinWarn, passWarn, phoneWarn, ifscWarn, bankWarn;


    private boolean dateTimeValidation(String month, String Year) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate date = LocalDate.now();
            int currentMonth = date.getMonthValue();
            int currentYear = date.getYear() % 2000;
            return Integer.parseInt(Year) > currentYear || Integer.parseInt(month) >= currentMonth;
        }
        else {
            return false;
        }
    }

    public long pinCheck(String str) {
        if(!str.equals("")) {
            try {
                return Long.parseLong(str);
            }
            catch (Exception e) {
                return -1;
            }
        }
        return 0;
    }

    private void back() {
        Intent intent = new Intent(getApplicationContext() ,  MainPage.class);
        intent.putExtra("previousActivity", "PasswordAdd");
        startActivity(intent);
    }

    public void backToPrevious() {
        String totalString = username.getText().toString() +
                password.getText().toString() +
                pin.getText().toString() +
                card.getText().toString() +
                CVV.getText().toString() +
                m.getText().toString() +
                y.getText().toString() +
                address.getText().toString() +
                note.getText().toString() +
                bank.getText().toString() +
                ifsc.getText().toString() +
                phone.getText().toString();
        String head = heading.getText().toString();
        if(!totalString.equals("") && (head.equals("Default Title") || head.equals("")) ) {
            showDialog();
        }
        else {
            back();
        }
    }

    @Override
    public void onBackPressed() {
        Button button = findViewById(R.id.submit);
        button.performClick();
        backToPrevious();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_add);

        heading = findViewById(R.id.editTextHeading);
        username = findViewById(R.id.editTextUser);
        password = findViewById(R.id.editTextPass);
        pin = findViewById(R.id.editTextPin);
        card = findViewById(R.id.editTextCard);
        CVV = findViewById(R.id.editTextCVV);
        m = findViewById(R.id.month);
        y = findViewById(R.id.year);
        address = findViewById(R.id.editTextAddress);
        note = findViewById(R.id.editTextNote);
        bank = findViewById(R.id.editTextBank);
        ifsc = findViewById(R.id.editTextIfsc);
        phone = findViewById(R.id.editTextPhone);


        titleWarn = findViewById(R.id.TitleWarning);
        cardWarn = findViewById(R.id.cardWarn);
        cvvWarn = findViewById(R.id.cvvWarn);
        pinWarn = findViewById(R.id.pinWarn);
        passWarn = findViewById(R.id.passWarn);
        ifscWarn = findViewById(R.id.ifscWarn);
        bankWarn = findViewById(R.id.bankWarn);
        phoneWarn = findViewById(R.id.phoneNoWarn);



        heading.setText(R.string.default_title);

        ScrollView myScrollView = findViewById(R.id.scrollViewEdit);

        begin b = new begin(getApplicationContext());

        card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String initial = s.toString();
                String processed = initial.replaceAll("\\D", "");
                processed = processed.replaceAll("(\\d{4})(?=\\d)", "$1 ");
                if (!initial.equals(processed)) {
                    s.replace(0, initial.length(), processed);
                }
            }
        });

        findViewById(R.id.backFromEdit).setOnClickListener(v -> backToPrevious());

        Button submit = findViewById(R.id.submit);
        heading.setOnClickListener(v -> {
            if(heading.getText().toString().contains("Default Title")) {
                heading.setText("");
            }
            else {
                Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_SHORT).show();
            }
        });



        submit.setOnClickListener(v -> save(String.valueOf(java.time.LocalDateTime.now())));
    }

    private void save(String time) {
        String exDate = m.getText().toString() + "." + y.getText().toString();


        if(DataProblemDetector()) {
            if(pinCheck(password.getText().toString()) != -1 && pin.getText().toString().equals("")) {
                pin.setText(password.getText().toString());
                password.setText("");
            }

            String[] data = StringData.getDataInArray(heading.getText().toString() , username.getText().toString() , password.getText().toString(), pin.getText().toString(), bank.getText().toString(), ifsc.getText().toString(), card.getText().toString() , exDate, CVV.getText().toString(), address.getText().toString(), note.getText().toString(), time, phone.getText().toString());

            MainPage.data2D = StringData.addData(MainPage.data2D, data);

            String str = StringData.ArrayToString(MainPage.data2D);
            try {
                Log.d("String", str);
                //str = begin.EncDec(str, Long.parseLong(userPassword), true, false);

                MainActivity.map.put(BasicDeclaration.KEY_DATA, str);
                MainActivity.myRef.setValue(MainActivity.map)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                            back();
                        })
                        .addOnFailureListener(e -> Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show());
            }
            catch (Exception e ) {
                e.printStackTrace();
                Log.d("Error", e.getMessage());
                Log.d("Error2", e.toString());
                Log.d("Error3", String.valueOf(e.getCause()));
            }
        }
    }


    private void showDialog() {
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_back, null);


        alert.setView(view);
        alert.setCancelable(false);
        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

        view.findViewById(R.id.yes).setOnClickListener(v -> {
            back();
        });

        view.findViewById(R.id.no).setOnClickListener(v -> {
            save("notime");//Problem
            alertDialog.cancel();
        });

        view.findViewById(R.id.cancel).setOnClickListener(v -> {
            alertDialog.cancel();
        });
    }

    public boolean DataProblemDetector() {
/*
        String[][] combinations = {
                {"username", "password"},
                {"username", "pin"},
                {"phone", "password"},
                {"phone", "pin"},
                {"bank", "ifsc"},
                {"card", "ex", "cvv"}
        };
        String[] singleDetails = {"note", "address"};
        String[] notSingleDetails = {"username", "bank",  "ifsc", "card", "ex", "cvv"};

*/
        return true;
    }
}