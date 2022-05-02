package com.arup.datonal.password.TextToNumber;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class binary {
    private int count = 0;
    private final List<Integer> list = new LinkedList<>();

    private boolean indexMove(int curr , int next) {
        return count == 5 || curr != next;
    }

    private int countToValue(int curr) {
        return curr == 1 || curr == 0 ? (count - 1) * 2 + curr : 0;
    }


    public BigInteger makeIt(String input) {
        return convertStringToBinary(input);
    }

    public char[] toCharArray(String str) {
        char[] letter = new char[str.length()];
        for(byte i = 0 ; i <str.length() ; i++) {
            letter[i] = str.charAt(i);
        }
        return letter;
    }

    private BigInteger convertStringToBinary(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = toCharArray(input);
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar)).replaceAll(" ", "0")
            );
        }
        return toNumber(result.toString());
    }

    public BigInteger toNumber(String s) {
        s = s + "*";

        for(int i = 0 ; i < s.length()-1 ; i++) {
            int curr = s.charAt(i) - 48;
            int next = s.charAt(i+1) - 48;
            count++;
            if(indexMove(curr , next)) {
                list.add(countToValue(curr));
                count = 0;
            }
        }
        BigInteger number = new BigInteger("1");
        for (Integer integer : list) {
            number = number.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(integer));
        }
        return number;
    }
}
