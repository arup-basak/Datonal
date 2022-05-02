package com.arup.datonal.data;

public class BasicDeclaration {
     private static final Long pin = Long.parseLong("8784678646989");
     private static final String collectionName = "UserData";
     public static final String KEY_DATA = "data";
     public static final String KEY_LOGIN_TIME = "LogInTime";
     public static final String KEY_PIN = "pin";
     public static final String KEY_PIN_RECOVERY = "PinData";
     public static final String KEY_ATTEMPTS = "Attempts";


     public static final int totalLength = 17;

     public static long getPin() {
         return pin;
     }

}
