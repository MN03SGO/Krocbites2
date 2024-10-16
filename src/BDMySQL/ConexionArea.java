/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMySQL;
import clases.areas;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author anoni
 */
public class ConexionArea {
      Connection con;
    conexion cn= new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
     public List listar(){
        List<areas> lista=new ArrayList<>();
         String sql="select * from areas";
         try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                areas a=new areas();
                a.setIdarea(rs.getInt(1));
                a.setNomArea(rs.getString(2));
                lista.add(a);
            }
                    
        }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);
            
         } 
         return lista;
    }
    public boolean insertar( areas a){
        String sql="insert into areas(nom_area) values(?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, a.getNomArea());
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
      public boolean editar (areas ar){
        String sql="update areas set nom_area=? where id_area=?";  
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, ar.getNomArea());
            ps.setInt(2, ar.getIdarea());
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
     public boolean eliminar (areas ar){
        String sql="delete from areas where id_area = ?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ar.getIdarea());
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
      public boolean buscar (areas ar){
        String sql="select * from areas where id_area  = ?";
        
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ar.getIdarea());
            rs=ps.executeQuery();
            if(rs.next()){
                ar.setIdarea(rs.getInt(1));
                ar.setNomArea(rs.getString(2));
                return true;
            }else{
                return false;
        }
        }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);
             return false;
         } 
    }
    
    
}
