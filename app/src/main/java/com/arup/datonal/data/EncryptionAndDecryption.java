package com.arup.datonal.data;

import android.util.Log;

import com.arup.datonal.MainActivity;

public class EncryptionAndDecryption {
    /*
    keyString contains total 950 words, 95*10
    every word is changed with 95 * n + integer value of character + 32
    the password is number decides n
     */
    private static char EncryptionGenerator(char letter, boolean EncOrDec, String EncryptionData) {
        short word = (short) (letter - 32);
        try {
            if(!EncOrDec) {
                letter = EncryptionData.charAt(word);
            }
            else {
                letter = (char) (EncryptionData.indexOf(letter) + 32);
            }
        }
        catch (Exception ignored) {}
        return letter;
    }

    public static String EncryptionProcess(String line, long pass, boolean EncOrDec) {
//        char[] passArr = String.valueOf(pass).toCharArray();
//        char[] letters = line.toCharArray();
//        for(byte i = 0 ; i < line.length() ; i++) {      //Choose Every Letter from Line
//            int count = String.valueOf(pass).length();
//            byte code = (byte) Integer.parseInt(String.valueOf(passArr[i % count]));        //Random use Pass
//            letters[i] = EncryptionGenerator(letters[i], EncOrDec, MainActivity.EncKey.substring(code*95, (code+1)*95));
//        }
//        return String.valueOf(letters);
        return line;
    }
}