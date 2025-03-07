package com.example.payment.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Utility for encrypting sensitive data.
 * In production, store and manage keys securely (e.g., in an HSM).
 */
public class EncryptionUtil {
    private static final String ALGORITHM = "AES";
    // For demonstration only; in production, use a secure key management system.
    private static final String KEY = "MySuperSecretKey"; // 16-byte key for AES-128

    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes("UTF-8"), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
