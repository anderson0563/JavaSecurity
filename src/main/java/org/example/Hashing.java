package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    public void givenPassword_whenHashing_thenVerifying()
            throws NoSuchAlgorithmException {
        String hash = "35454B055CC325EA1AF2126E27707052";
        System.out.println(hash);
        String password = "ILoveJava";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        System.out.println(bytesToHex(digest));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        (new Hashing()).givenPassword_whenHashing_thenVerifying();
    }
}
