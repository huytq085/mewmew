package com.culicode.dating.mewmew.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean matches(String raw, String encodePass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(raw, encodePass);
    }

    public static void main(String[] args) {
        String password = "123456";
        String encrytedPassword = encrytePassword(password);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        System.out.println("Encryted Password: " + encrytedPassword);
        System.out.println(encoder.matches("123456", "$2a$10$.6xto6ajbSHPFhb5r2zWKeMJK7Fk/HvR1yfJ1eAokSR0wD7lQ0n7G"));
    }
}
