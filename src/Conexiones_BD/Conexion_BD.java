/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones_BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author anoni
 */
public class Conexion_BD {
    Connection con;
    String bd="CRUD_GALLETAS";
    String url="jdbc:sqlserver://localhost:1433;databaseName="+bd;
    String user="sa";
    String pass="root";
    
    public Connection conectar() {
    try {
        con = DriverManager.getConnection(url, user, pass);
        System.out.println("Conexion establecida...");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Fallo de conexion: " + e.getMessage());
        System.out.println("Error: " + e.getMessage());
    }
    return con;
}


}