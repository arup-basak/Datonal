package com.arup.datonal.password.TextToNumber;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class convert {

    private String toMinimumNumber(String str, int maxLength) {
        List<Long> list = new LinkedList<>();
        for(int i = 0 ; i < str.length() ;) {
            String s;
            if(i + maxLength > str.length()) {
                s = str.substring(i);
            }
            else {
                s = str.substring(i, i + maxLength);
            }
            list.add(Long.parseLong(s));
            i = i + maxLength;
        }
        long output = 0;

        for (Long aLong : list) {
            output += aLong;
        }
        return String.valueOf(output);
    }

    public String It(String passText , String phone , String pin , String dob, int maxLength) {
        binary binary = new binary();
        BigInteger pass = binary.makeIt(passText);
        BigInteger phoneNo = binary.makeIt(phone);
        BigInteger pinNo = binary.makeIt(pin);
        BigInteger DOB = binary.makeIt(dob);
        return toMinimumNumber(String.valueOf(pass.add(phoneNo).add(pinNo).add(DOB)), maxLength);
    }

    public static int EncKeyChooser(String name) {
        char[] charGroup = name.toCharArray();
        int total = 0;
        for (char c : charGroup) {
            total += c;
        }
        return total%12;
    }
}
