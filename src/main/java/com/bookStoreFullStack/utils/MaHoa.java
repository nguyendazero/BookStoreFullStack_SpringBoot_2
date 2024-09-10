package com.bookStoreFullStack.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import lombok.Data;

@Data
public class MaHoa {
    
    // Hàm sinh salt ngẫu nhiên
    public static String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hàm SHA-256
    public static String toSHA256(String str, String salt) {
        String result = null;
        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            result = Base64.getEncoder().encodeToString(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String salt = generateSalt();
        System.out.println("Salt: " + salt);
        System.out.println("Hash: " + toSHA256("123456", salt));
    }
}
