package Conexiones_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion_BD {
    Connection con;
    String bd = "crud";
    String url = "jdbc:postgresql://localhost:5432/" + bd;
    String user = "sigaran"; 
    String pass = "Peperecha22";
    
    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida con PostgreSQL...");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver no encontrado: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fallo de conexión: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}