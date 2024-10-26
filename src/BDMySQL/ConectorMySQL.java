/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMySQL;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author anoni
 */
public class ConectorMySQL {
    Connection conect;
     public Connection Conectar(){
        
        try{
         Class.forName("com.mysql.jdbc.Driver");
         conect=DriverManager.getConnection("jdbc:mysql://localhost/krocbites","root","");          
        } catch (Exception e){
            System.err.println("No es posible establecer la conexion con la base de datos");
            
        }
        return null;
     }
     public Connection getConnection(){
       return conect;
    }
}
