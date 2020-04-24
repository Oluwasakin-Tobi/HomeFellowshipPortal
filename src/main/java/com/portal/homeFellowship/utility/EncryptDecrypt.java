/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.homeFellowship.utility;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gkasumu
 */
public class EncryptDecrypt {


    // AES-GCM parameters
    public static final int AES_KEY_SIZE = 256; // in bits
    public static final int GCM_NONCE_LENGTH = 12; // in bytes
    public static final int GCM_TAG_LENGTH = 16; // in bytes

    public static void main(String[] args) throws Exception {

        //System.out.println("Ddescrypt : " + decrypt("Oh6XD/eNDUyK85joLJvfBuPCeYjY+seZ5ZBq4bC9NTk5x3cz887ymWkrb/X2cK7E"));
        System.out.println("ENCRYPT==> mmhuser:: " + encrypt("MMHUSER"));
        //System.out.println("DECRYPT:: " + decrypt(""));
    }

    public static String encrypt(String entry) {
        String noncebte = "";
        String encodedKey = "";
        try {

            byte[] input = entry.getBytes();

            // Initialise random and generate key
            SecureRandom random = SecureRandom.getInstanceStrong();
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(AES_KEY_SIZE, random);
            SecretKey key = keyGen.generateKey();
            encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
            // Encrypt
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
            final byte[] nonce = new byte[GCM_NONCE_LENGTH];
            random.nextBytes(nonce);
            noncebte = Base64.getEncoder().encodeToString(nonce);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);
            byte[] cipherText = cipher.doFinal(input);
            entry = Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ent = entry + "::" + noncebte + "::" + encodedKey;
        ByteBuffer byteBuffer = ByteBuffer.allocate(ent.getBytes().length);
        byteBuffer.put(ent.getBytes());
        byte[] cipherMessage = byteBuffer.array();
        return Base64.getEncoder().encodeToString(cipherMessage);
//        return entry + "::"+noncebte+"::"+encodedKey;
    }

    public static String decrypt(String input) {
        try {

            ByteBuffer byteBuffer = ByteBuffer.wrap(Base64.getDecoder().decode(input));
            byte[] full = new byte[byteBuffer.remaining()];
            byteBuffer.get(full);
            String[] split = new String(full, StandardCharsets.UTF_8).split("::");
            byte[] bytes = Base64.getDecoder().decode(split[0]);
            // Encrypt
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
            byte[] decode = Base64.getDecoder().decode(split[1]);
            String s = Base64.getEncoder().encodeToString(decode);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, Base64.getDecoder().decode(split[1]));
            byte[] decodedKey = Base64.getDecoder().decode(split[2]);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            cipher.init(Cipher.DECRYPT_MODE, originalKey, spec);

            try {
                byte[] plainText = cipher.doFinal(bytes);
                String ss = new String(plainText, StandardCharsets.UTF_8);
                input = ss;

            } catch (AEADBadTagException ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return input;
    }
}

