package com.arup.datonal;

import android.content.Context;
import android.widget.Toast;

import com.arup.datonal.data.BasicDeclaration;
import com.arup.datonal.data.begin;
import com.arup.datonal.password.PasswordMatch.Make;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword {
    public static String key;
    public static String username;
    public static String oldPin;
    public static String newPin;
    public static String data;

    public ChangePassword(String key, String username, String oldPin, String newPin, String data) {
        ChangePassword.key = key;
        ChangePassword.username = username;
        ChangePassword.oldPin = oldPin;
        ChangePassword.newPin = newPin;
        ChangePassword.data = data;
    }

    public void change(Context context) {
        DatabaseReference myRef = MainActivity.myRef;

        Map<String, Object> map = new HashMap<>();
        map.put(BasicDeclaration.KEY_DATA, ChangeLineEnc(data, false, false));
        map.put(BasicDeclaration.KEY_PIN_RECOVERY, begin.EncDec(Make.It(newPin), Long.parseLong(key), true, true));
        map.put(BasicDeclaration.KEY_PIN, ChangeLineEnc(data, false, false));

        myRef.setValue(map)
                .addOnSuccessListener(unused ->
                        Toast.makeText(context, "Password is Changed", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(context, "Password Cannot be Changed, Please try After some Time", Toast.LENGTH_SHORT).show()
                );
    }

    private String ChangeLineEnc(String str, boolean typeWhichIsUsedForLocked, boolean ReverseOrNot) {
        str = begin.EncDec(str, Long.parseLong(oldPin), !typeWhichIsUsedForLocked, ReverseOrNot);
        str = begin.EncDec(str, Long.parseLong(newPin), typeWhichIsUsedForLocked, ReverseOrNot);
        return str;
    }
}
