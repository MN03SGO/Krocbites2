/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMySQL;

import clases.empleado;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;






/**
 *
 * @author anoni
 */
public class ConexionEmpleados {
     Connection con;
     conexion cn= new conexion();
     PreparedStatement ps;
     ResultSet rs;
    
    public boolean insertar(empleado e){
        String sql="insert into empleados (nombre,apellido,tipoDoc,documento,id_area,id_cargo,telefono,correo) values (?,?,?,?,?,?,?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getTipodoc());
            ps.setString(4, e.getDocumento());
            ps.setInt(5, e.getIdArea());
            ps.setInt(6, e.getIdCargo());
            ps.setString(7, e.getTelefono());
            ps.setString(8, e.getCorreo());
            int n=ps.executeUpdate();
            if(n!=0){
                return true;
            }else{
                return false;
            }
        }catch(Exception er){
            JOptionPane.showConfirmDialog(null, er);
            return false;
        }
    }
    
     
     
     
     
    
    
   
}
