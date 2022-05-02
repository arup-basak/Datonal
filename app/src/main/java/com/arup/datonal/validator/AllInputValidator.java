package com.arup.datonal.validator;

public class AllInputValidator {
    private static final char[] SpecialCharacter = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '{', '}', '[', ']', '~'};

    private static boolean LowerCase(char c) {
        return c >= 97 && c <= 122;
    }

    private static boolean UpperCase(char c) {
        return c >= 65 && c <= 90;
    }

    private static boolean Number(char c) {
        return c >= 48 && c <= 57;
    }

    private static boolean ArrayExist(String[] arr, String obj) {
        boolean b = false;
        for (String o : arr) {
            if (o.equals(obj)) {
                b = true;
                break;
            }
        }
        return b;
    }

    private static boolean ArrayExistInteger(int[] arr, int integer) {
        boolean b = false;
        for (int a : arr) {
            if (a == integer) {
                b = true;
                break;
            }
        }
        return b;
    }

    private static boolean EnCheck(String str) {
        boolean b = true;
        for(int i = 0 ; i < str.length() ; i++) {
            char c = str.charAt(i);
            if (!LowerCase(c) && !UpperCase(c) && !Number(c)) {
                b = false;
                break;
            }
        }
        return b;
    }

    private static boolean SpecialCharacterCheck(String str) {
        boolean b = false;
        for(int i = 0 ; i < str.length() ; i++) {
            char current = str.charAt(i);
            for(char c : SpecialCharacter) {
                if (c == current) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    public static String UserName(String username) {
        if(username.equals("")) {
            return "Please Enter Username its Mandatory";
        }
        else if(username.contains(" ")) {
            return "You Cannot add Space in Username";
        }
        else if(SpecialCharacterCheck(username)) {
            return "You Cannot Add Special Character in Username";
        }
        else if(!EnCheck(username)) {
            return "Only En. Language available in Username";
        }
        else {
            return "";
        }
    }
    
    public static String DateValidator(String str) {
        if(!str.equals("")) {
            String[] arr = str.split("/");
            int[] ThirtyOneDateMonths = {1, 3, 5, 7, 8, 10, 12};
            try {
                int date = Integer.parseInt(arr[0]);
                int month = Integer.parseInt(arr[1]);
                int year = Integer.parseInt(arr[2]);
                if(arr.length != 3) {
                    return "Wrong Input";
                }
                else if(date > 31) {
                    return "Date is Not Valid";
                }
                else if(month > 12) {
                    return "Month is Not Valid";
                }
                else if(!ArrayExistInteger(ThirtyOneDateMonths, month) && date == 31) {
                    return "Date and Month is Not Valid";
                }
                else if(year % 4 != 0 && month == 2 && date >= 29) {
                    return "Date is Not Valid, February has 28 Days";
                }
                else if(year < 1920) {
                    return "Year is Not valid";
                }
                else {
                    return "";
                }
            }
            catch (Exception e) {
                return "You Cannot add Word in Integer";
            }
        }
        else {
            return "Please Enter Date, It's Mandatory";
        }
    }

    public static String PhoneNumber(String str) {
        if (!str.equals("")) {
            if(str.length() == 10) {
                if(PinOrPassValidator.CheckNumbers(str)) {
                    return "";
                }
                else {
                    return "Phone No is Not a Text";
                }
            }
            else {
                return "";
            }
        }
        else {
            return "Please Enter Phone No, Its Mandatory";
        }

    }

    public static String PostalCode(String str) {
        if(str.equals("")) {
            return "Please Enter Postal Code, It's Mandatory";
        }
        else {
            return "";
        }
    }
}
