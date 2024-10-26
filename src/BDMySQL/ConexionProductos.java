/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import clases.cargo;
import clases.productos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ConexionProductos {
     Connection con;
    conexion cn= new conexion();
    PreparedStatement ps;
    ResultSet rs;
   
    public boolean insertar( productos p){
        String sql="insert into productos(nombre,precio,descripcion) values(?,?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
             ps.setDouble(2, p.getPrecio());
             ps.setString(3, p.getDescripcion());
            int n=ps.executeUpdate();
                    if(n!=0){
                        return true;
                        
                    }else {
                        return false;
                    }
                    
        }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);
             return false;
        }
    }
   public List listar(){
        List<cargo> lista=new ArrayList<>();
         String sql="select * from productos";
         try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                productos p=new productos();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                //lista.add(p);
            }
         
                    
        }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);
            
         } 
         return lista;
    }  
}
