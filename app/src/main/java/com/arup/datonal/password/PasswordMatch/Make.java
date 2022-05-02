package com.arup.datonal.password.PasswordMatch;

import com.arup.datonal.data.EncryptionAndDecryption;

public class Make {
    public static String getRandomWord(int MaxLength) {
        StringBuilder str = new StringBuilder();
        int length = (int) (Math.random()*5) + (MaxLength - 4);
        for(int i = 0 ; i < length ; i++) {
            str.append((char)(int) (Math.random() * 94 + 32));
        }
        return String.valueOf(str);
    }

    public static String It(String password) {
        return getRandomWord(20) + password + getRandomWord(25);
    }

    private static String getLongestString(String[] arr) {
        int pos = 0;
        for(int i = 1 ; i < arr.length ; i++) {
            if(arr[pos].length() < arr[i].length()) {
                pos = i;
            }
        }
        return arr[pos];
    }

    public static String Break(String str, String key) {
        str = EncryptionAndDecryption.EncryptionProcess(str, Long.parseLong(key) , false);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < str.length() ; i++) {
            int c = str.charAt(i);
            if(c > 47 && c < 58) {
                stringBuilder.append((char) c);
            }
            else {
                stringBuilder.append("!");
            }
        }
        return getLongestString(stringBuilder.toString().split("!"));
    }
}
