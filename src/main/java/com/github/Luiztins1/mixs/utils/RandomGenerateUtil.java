package com.github.Luiztins1.mixs.utils;

import java.util.Random;

public class RandomGenerateUtil {

    private final static String AlphaNumericString =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz"
            +"!@#";

    public static String generateLogin(int n){
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < n; i++){
            int index = (int) (AlphaNumericString.length() * Math.random());
            stringBuilder.append(AlphaNumericString.charAt(index));
        }

        return "User" + stringBuilder.toString();
    }

    public static String generatePassword(int n){
        StringBuilder stringBuilder = new StringBuilder(n);
        Random random = new Random();

        for(int i = 0; i < n; i++){
            int index = (int) (AlphaNumericString.length() * Math.random());
            stringBuilder.append(AlphaNumericString.charAt(index));
        }

        int password = 100 + random.nextInt(900);
        return String.valueOf(password) + stringBuilder.toString() ;
    }
}
