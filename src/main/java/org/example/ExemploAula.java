package org.example;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ExemploAula {
    byte[] textoEncriptado(SecretKey desKey, String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] message = plainText.getBytes();
        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage = desCipher.doFinal(message);
        return encryptedMessage;
    }

    String textoClaro(byte[] cipher, SecretKey desKey) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, desKey);
        String plainMessage = new String(desCipher.doFinal(cipher), StandardCharsets.UTF_8);
        return plainMessage;
    }

    String doHash() throws NoSuchAlgorithmException {
        SecureRandom secRandom = SecureRandom.getInstance("SHA1PRNG");
        secRandom.setSeed(711);
        byte[] bytes = new byte[20];
        secRandom.nextBytes(bytes);
        return "";
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        ExemploAula aula = new ExemploAula();
        SecretKey chave = KeyGenerator.getInstance("DES").generateKey();

        byte[] cryptText = aula.textoEncriptado(chave, "senac");
        System.out.println(cryptText.toString());
        String plainText = aula.textoClaro(cryptText, chave);
        System.out.println(plainText);
    }
}
