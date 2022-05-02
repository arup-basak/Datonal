package com.arup.datonal.data;

import com.arup.datonal.password.PasswordMatch.Make;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringData {
    private static final String nextColumn = ", :/ nextColumn: :/ ,";
    public static final String nextCell = ", :/ nextCell: :/ ,";

    public static String getRand(int MaxLength) {
        return Make.getRandomWord(MaxLength) + nextCell;
    }

    private static String StringCleaner(String str) {
        return str.split(StringData.nextCell)[1];
    }

    private static String[][] arrCleaner(String[][] arr) {
        String[][] newArr = new String[arr.length][arr[0].length];
        for(int i = 0 ; i < arr.length ; i++) {
            for(int j = 0 ; j < arr[i].length ; j++) {
                newArr[i][j] = StringCleaner(arr[i][j]);
            }
        }
        return newArr;
    }



    public static String ArrayToString(String[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String str : array) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    private static String addTwoSideRandom(String str) {
        return getRand(15) + str + getRand(15);
    }

    private static String removeLast(String str, String what) {
        return str.substring(0, str.length() - what.length());
    }

    public static String ArrayToString(String[][] array) {
        if(array.length != 0) {
            StringBuilder mainString = new StringBuilder();
            for(String[] arr1D : array) {
                StringBuilder str = new StringBuilder();
                for (String s : arr1D) {
                    try {
                        if(s.equals("")) {
                            s = "-";
                        }
                    }
                    catch (Exception e) {
                        s = "-";
                    }

                    str.append(s);
                    str.append(nextCell);
                }
                str = new StringBuilder(addTwoSideRandom(str.toString()));
                mainString.append(str);
                mainString = new StringBuilder(removeLast(mainString.toString(), nextCell));
                mainString.append(nextColumn);
            }
            return removeLast(mainString.toString(), nextColumn);
        }
        return "";
    }

    public static String[] StringToArray(String str) {
        if(str.equals("")) {
            return new String[0];
        }
        String[] arr1D;
        try {
            arr1D = str.split(nextColumn);
        }
        catch(Exception e) {
            arr1D = new String[0];
        }
        return arr1D;
    }

    public static String[] getDataInArray(String heading , String user, String password, String pin, String bank, String ifsc, String card, String ExDate, String CVV, String address, String note , String time, String phone) {
        return new String[]{heading, user, password, pin, bank, ifsc, card, ExDate, CVV, address, note, time, phone};
        //                      0       1       2     3   4       5     6     7       8      9      10     11
    }

    public static String getDataInString(String heading , String user, String password, String pin, String bank, String ifsc, String card, String ExDate, String CVV, String address, String note , String time, String phone) {
        String[] arr = getDataInArray(heading , user, password, pin, bank, ifsc,  card, ExDate, CVV, address, note , time, phone);
        StringBuilder stringBuilder = new StringBuilder();
        for(String str : arr) {
            if(str.equals("")) {
                str = "-";
            }
            stringBuilder.append(str).append(nextCell);
        }
        return stringBuilder.toString();
    }

/*    public static String[] addData(String[] realData, String data) {
        String[] newData = new String[realData.length + 1];
        int i = 0 ;
        while (i < realData.length) {
            newData[i] = realData[i];
            i++;
        }
        newData[i] = data;
        return newData;
    }*/

    public static String[][] addData(String[][] realData, String[] data) {
        String[][] newData = new String[realData.length + 1][data.length];//realData[0].length
        int i = 0 ;
        while (i < realData.length) {
            System.arraycopy(realData[i], 0, newData[i], 0, realData[i].length);
            i++;
        }
        newData[i] = data.clone();
        return newData;
    }

    public static String[][] removeData(String[][] realData, int pos) {
        List<String[]> list = new LinkedList<>();
        for (int i = 0 ; i < realData.length ; i++)
            if (i != pos) {
                list.add(realData[i]);
            }
        realData = new String[list.size()][realData[0].length];
        for (int i = 0 ; i < list.size() ; i++)
            realData[i] = list.get(i).clone();

        return realData;
    }

    public static String[] removeCorners(String[] arr) {
        if(arr.length > 2) {
            String[] newArr = new String[arr.length - 2];
            System.arraycopy(arr, 1, newArr, 0, arr.length - 1 - 1);
            return newArr;
        }
        return arr;
    }

    public static String[][] removeCorners(String[][] arr) {
        for(int i = 0 ; i < arr.length ; i++) {
            arr[i] = removeCorners(arr[i]);
        }
        return arr;
    }


    public static String[][] StringTo2DArray(String str) {
        if(str.equals("")) {
            return new String[0][0];
        }
        String[] arr1D = str.split(nextColumn);
        String[][] arr2D = new String[arr1D.length][10];
        for(int i = 0 ; i < arr1D.length ; i++) {
            String[] arr = arr1D[i].split(nextCell);
            arr2D[i] = removeCorners(arr);
        }
        return arr2D;
    }

    public static Map<String, Object> DataSnapshotToMap(DataSnapshot dataSnapshot) {
        HashMap<String, Object> map= new HashMap<>();
        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
            map.put(childSnapshot.getKey(), childSnapshot.getValue());
        }
        return map;
    }
}
