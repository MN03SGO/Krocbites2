/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMySQL;

import clases.datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author anoni
 */
public class ConexionDatos {
     Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public void Agregar(datos d){
        String sql="insert into datos (empresa,ruc,razonSocial,telefono,direccion,correo,imagen) values (?,?,?,?,?,?,?)";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getRUC());
            ps.setString(3, d.getRasonS());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getDireccion());
            ps.setString(6, d.getCorreo());
            ps.setBytes(7, d.getImagen());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            
        }finally {
            try {
                ps.close();
            } catch (SQLException ex) {
            }
        }
    }
    public ArrayList Listar(){
        ArrayList empresa=new ArrayList<>();
        String sql="select * from datos";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                datos d=new datos();
                d.setId(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setRUC(rs.getString(3));
                d.setRasonS(rs.getString(4));
                d.setTelefono(rs.getString(5));
                d.setDireccion(rs.getString(6));
                d.setCorreo(rs.getString(7));
                d.setImagen(rs.getBytes(8));
                empresa.add(d);
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        return empresa;
    }
     public boolean BuscarImagen(datos d){
       String sql="SELECT imagen from datos where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, d.getId());
            rs=ps.executeQuery();
            if(rs.next()){
                d.setImagen(rs.getBytes(1));
                return true;
            }else{
                 return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
   }
     public boolean editar(datos d){
       String sql="update datos set empresa=?,ruc=?,razonS=?,telefono=?,direccion=?,correo=?,imagen=? where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getRUC());
            ps.setString(3, d.getRasonS());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getDireccion());
            ps.setString(6, d.getCorreo());
            ps.setBytes(7, d.getImagen());
            ps.setInt(8, d.getId());
            int n=ps.executeUpdate();
            if(n!=0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
   }
}
