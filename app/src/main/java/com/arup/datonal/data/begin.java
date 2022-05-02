package com.arup.datonal.data;


import android.content.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class begin {
    Context context;
    public begin(Context context) {
        this.context = context;
    }

    public static String EncDec(String line, long encPin, boolean type, boolean reverseOrNot) {
        long appPin = BasicDeclaration.getPin();
        if(!reverseOrNot) {
            appPin = Long.parseLong(String.valueOf(new StringBuilder(String.valueOf(appPin)).reverse()));
        }
        if(type){
            return EncryptionAndDecryption.EncryptionProcess(EncryptionAndDecryption.EncryptionProcess(line, encPin, true), appPin, true);
        }
        else{
            return EncryptionAndDecryption.EncryptionProcess(EncryptionAndDecryption.EncryptionProcess(line, appPin, false), encPin, false);
        }
    }

    public static String getTime() {
        LocalDateTime myDateObj;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            return myDateObj.format(myFormatObj);
        }
        else {
            return String.valueOf(-1);
        }
    }
}
