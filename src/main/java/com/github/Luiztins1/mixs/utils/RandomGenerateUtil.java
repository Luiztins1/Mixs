package com.github.Luiztins1.mixs.utils;

import java.security.SecureRandom;

public class RandomGenerateUtil {

    private final static String AlphaNumericString =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvwxyz"
            +"!@#";
    private final static SecureRandom random = new SecureRandom();

    public static String generateLogin(int n){
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < n; i++){
            int index = random.nextInt(AlphaNumericString.length());
            stringBuilder.append(AlphaNumericString.charAt(index));
        }

        return "User" + stringBuilder.toString();
    }

    public static String generatePassword(int n){
        StringBuilder stringBuilder = new StringBuilder(n);

        for(int i = 0; i < n; i++){
            int index = random.nextInt(AlphaNumericString.length());
            stringBuilder.append(AlphaNumericString.charAt(index));
        }

        int password = 100 + random.nextInt(900);
        return (password) + stringBuilder.toString() ;
    }
}
