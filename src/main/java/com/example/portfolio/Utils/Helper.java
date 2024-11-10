package com.example.portfolio.Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helper {

    public static String hashingPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static Boolean verifyPassword(String inputPassword, String storedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(inputPassword, storedPassword);
    }
}
