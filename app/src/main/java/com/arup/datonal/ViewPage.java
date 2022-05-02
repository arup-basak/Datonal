package com.arup.datonal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPage extends AppCompatActivity {
    static String[] data;

    public void copyToClipboard(TextView textView) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        String getString = textView.getText().toString();
        ClipData clip = ClipData.newPlainText("Copied Text", getString);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
    }

    private void loadLayout(String[] data) {
        TextView viewTitle = findViewById(R.id.ViewTitle);

        TextView userIdTextView = findViewById(R.id.userIdTextView);
        TextView phoneNoTextView = findViewById(R.id.phoneNoTextView);
        TextView passWordTextView = findViewById(R.id.passWordTextView);
        TextView bankAccountTextView = findViewById(R.id.bankAccountTextView);
        TextView ifscTextView = findViewById(R.id.ifscTextView);
        TextView cardNoTextView = findViewById(R.id.cardNoTextView);
        TextView cvvTextView = findViewById(R.id.cvvTextView);
        TextView exDateTextView = findViewById(R.id.exDateTextView);
        TextView pinTextView = findViewById(R.id.pinTextView);
        TextView addressTextView = findViewById(R.id.addressTextView);
        TextView noteTextView = findViewById(R.id.noteTextView);

        LinearLayout userIdLayout = findViewById(R.id.userLayout);
        LinearLayout phoneNoLayout = findViewById(R.id.phoneNoLayout);
        LinearLayout passWordLayout = findViewById(R.id.passwordLayout);
        LinearLayout bankAccountLayout = findViewById(R.id.bankAccountLayout);
        LinearLayout ifscLayout = findViewById(R.id.ifscLayout);
        LinearLayout cardNoLayout = findViewById(R.id.cardNoLayout);
        LinearLayout cvvLayout = findViewById(R.id.cvvLayout);
        LinearLayout exDateLayout = findViewById(R.id.exDateLayout);
        LinearLayout pinLayout = findViewById(R.id.pinLayout);
        LinearLayout addressLayout = findViewById(R.id.addressLayout);
        LinearLayout noteLayout = findViewById(R.id.noteLayout);

        ImageView userIdCopy = findViewById(R.id.copyUserId);
        ImageView phoneNoCopy = findViewById(R.id.copyPhoneNo);
        ImageView passWordCopy = findViewById(R.id.copyPassword);
        ImageView bankAccountCopy = findViewById(R.id.copyBankAccount);
        ImageView ifscCopy = findViewById(R.id.copyIfsc);
        ImageView cardNoCopy = findViewById(R.id.copyCardNo);
        ImageView cvvCopy = findViewById(R.id.copyCvv);
        ImageView exDateCopy = findViewById(R.id.copyExDate);
        ImageView pinCopy = findViewById(R.id.copyPin);
        ImageView addressCopy = findViewById(R.id.copyAddress);
        ImageView noteCopy = findViewById(R.id.copyNote);

        viewTitle.setText(data[0]);
        userIdTextView.setText(data[1]);
        phoneNoTextView.setText(data[12]);
        passWordTextView.setText(data[3]);
        bankAccountTextView.setText(data[4]);
        ifscTextView.setText(data[5]);
        cardNoTextView.setText(data[6]);
        cvvTextView.setText(data[8]);
        exDateTextView.setText(data[7]);
        pinTextView.setText(data[3]);
        addressTextView.setText(data[9]);
        noteTextView.setText(data[10]);

        if(data[1].equals("-")) userIdLayout.setVisibility(View.GONE);
        if(data[12].equals("-")) phoneNoLayout.setVisibility(View.GONE);
        if(data[2].equals("-")) passWordLayout.setVisibility(View.GONE);
        if(data[4].equals("-")) bankAccountLayout.setVisibility(View.GONE);
        if(data[5].equals("-")) ifscLayout.setVisibility(View.GONE);
        if(data[6].equals("-")) cardNoLayout.setVisibility(View.GONE);
        if(data[8].equals("-")) cvvLayout.setVisibility(View.GONE);
        if(data[7].equals("/")) exDateLayout.setVisibility(View.GONE);
        if(data[3].equals("-")) pinLayout.setVisibility(View.GONE);
        if(data[9].equals("-")) addressLayout.setVisibility(View.GONE);
        if(data[10].equals("-")) noteLayout.setVisibility(View.GONE);

        userIdCopy.setOnClickListener(v -> copyToClipboard(userIdTextView));
        phoneNoCopy.setOnClickListener(v -> copyToClipboard(phoneNoTextView));
        passWordCopy.setOnClickListener(v -> copyToClipboard(passWordTextView));
        bankAccountCopy.setOnClickListener(v -> copyToClipboard(bankAccountTextView));
        ifscCopy.setOnClickListener(v -> copyToClipboard(ifscTextView));
        cardNoCopy.setOnClickListener(v -> copyToClipboard(cardNoTextView));
        cvvCopy.setOnClickListener(v -> copyToClipboard(cvvTextView));
        exDateCopy.setOnClickListener(v -> copyToClipboard(userIdTextView));
        pinCopy.setOnClickListener(v -> copyToClipboard(pinTextView));
        addressCopy.setOnClickListener(v -> copyToClipboard(addressTextView));
        noteCopy.setOnClickListener(v -> copyToClipboard(noteTextView));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        DataCorrection();
        loadLayout(data);


        findViewById(R.id.editView).setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), passwordAdd.class);
            //intent1.putExtra("getEditableData" , data);
            startActivity(intent1);
        });

        findViewById(R.id.backToMainFromView).setOnClickListener(v -> {
            Intent intent = new Intent(ViewPage.this, MainPage.class);
            intent.putExtra("previousActivity", "ViewPage");
            startActivity(new Intent());
        });
    }

    private void DataCorrection() {
        data[7] = data[7].replace(".", "/");
    }
}