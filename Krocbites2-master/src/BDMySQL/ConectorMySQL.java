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
     public Connection Conectar(){
        Connection conect=null;
        try{
         Class.forName("com.mysql.jdbc.Driver");
         conect=DriverManager.getConnection("jdbc:mysql://localhost/registros","root","");
            
        } catch (Exception e){
            
        }
        return conect;
    }
}
