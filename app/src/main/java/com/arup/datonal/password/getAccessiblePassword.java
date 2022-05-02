package com.arup.datonal.password;

public class getAccessiblePassword {
    private char randomLetter() {
        int i = (int) (Math.random()*84 +32);
        if(47 < i) {
            i += 10;
        }
        return (char) i;
    }

    public String passwordMaker(String password ,  int maxLength) {
        int pass = 0;
        int rand = 0;
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < maxLength ; i++) {
            int j = (int) (Math.random() * 2);
            if(rand < (maxLength - password.length())) if (j == 0) {
                try {
                    str.append(password.charAt(pass));
                    pass++;
                } catch (Exception ignore) {
                }
            } else {
                str.append(randomLetter());
                rand++;
            }
            else {
                str.append(password.charAt(pass));
                pass++;
            }

        }
        return String.valueOf(str);
    }

    public String passwordBreak(String password) {
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < password.length() ; i++) {
            try {
                str.append(Integer.parseInt(String.valueOf(password.charAt(i))));
            }
            catch (Exception ignored) {}
        }
        return String.valueOf(str);
    }
}
