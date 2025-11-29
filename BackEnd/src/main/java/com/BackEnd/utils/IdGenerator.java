package com.BackEnd.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;
public class IdGenerator {

    public static String generateId() {

            return generateUUID();


    }

    public static String generateDeterministicId(String email) {
        try {
            String normalizedEmail = email.toLowerCase().trim();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(normalizedEmail.getBytes());

            // Usa apenas 12 bytes para um ID mais curto
            byte[] truncatedHash = new byte[12];
            System.arraycopy(hash, 0, truncatedHash, 0, 12);

            String base64Id = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(truncatedHash);

            return "usr_" + base64Id;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating ID", e);
        }
    }

    public static String generateUUID() {
        return  UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }


}