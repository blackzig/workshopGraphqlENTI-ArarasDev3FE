package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michel
 */
public class EncryptWihSha2 {

    public String encrypt(String password) {
        String passwordAux = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();

            for (byte b : messageDigest) {
                sb.append(String.format("%02X", 0xFF & b));
            }

            passwordAux = sb.toString().toLowerCase();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println("ERROR encrypt " + e.getMessage());
        }

        return passwordAux;
    }

}
