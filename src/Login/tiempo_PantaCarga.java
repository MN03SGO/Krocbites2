/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;


import Login.Login;
import javax.swing.Timer;

/**
 *
 * @author anoni
 */
public class tiempo_PantaCarga {
    public static void main(String[] args) {
        carga nCarga = new carga();
        nCarga.setVisible(true);
        
        Timer timer = new Timer(5000, e -> {
            nCarga.dispose();
            new Login().setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
