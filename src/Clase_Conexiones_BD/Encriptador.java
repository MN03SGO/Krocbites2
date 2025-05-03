/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clase_Conexiones_BD;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author anoni
 */


public class Encriptador {

    public static String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString().toUpperCase(); 
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
