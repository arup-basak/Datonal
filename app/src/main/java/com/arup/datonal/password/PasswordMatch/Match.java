package com.arup.datonal.password.PasswordMatch;

import com.arup.datonal.data.EncryptionAndDecryption;

public class Match {
    public boolean it(String password, String eForm) {
        return EncryptionAndDecryption.EncryptionProcess(eForm, Integer.parseInt(password), false).contains(password);
    }
    public String ToNumber(String eForm) {
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < eForm.length() ; i++) {
            int c = eForm.charAt(i);
            if(c > 47 && c < 58) {
                str.append((char) c);
            }
        }
        return String.valueOf(str);
    }
}
