/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import clases.empleado;







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
     public DefaultTableModel listar(){
         DefaultTableModel modelo;
         String [] titulos={"ID","Nombre","Apellido","Tipo Doc","Documento","Id Area","Area","ID Cargo","Cargo","Telefono","Correo"};
         String [] registros=new String[11];
        modelo=new DefaultTableModel(null,titulos);
        
        String sql="SELECT e.id_empleado,e.nombre,e.apellido,e.tipoDoc,e.documento,e.id_area,\n" +
                    "a.nom_area,e.id_cargo,c.nom_cargo,e.telefono,e.correo\n" +
                    "from empleados e\n" +
                    "INNER JOIN areas a\n" +
                    "ON e.id_area=a.id_area\n" +
                    "INNER JOIN cargos c\n" +
                    "ON e.id_cargo=c.id_cargo";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                registros[0]=rs.getString("id_empleado");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("apellido");
                registros[3]=rs.getString("tipoDoc");
                registros[4]=rs.getString("documento");
                registros[5]=rs.getString("id_area");
                registros[6]=rs.getString("nom_area");
                registros[7]=rs.getString("id_cargo");
                registros[8]=rs.getString("nom_cargo");
                registros[9]=rs.getString("telefono");
                registros[10]=rs.getString("correo");
                modelo.addRow(registros);
            }
            return modelo;
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
      public boolean editar(empleado en){
       String sql="update empleados set nombre=?, apellido=?, tipoDoc=?, documento=?,id_area=?,id_cargo=?,telefono=?,correo=? "+
                    "where id_empleado=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, en.getNombre());
            ps.setString(2, en.getApellido());
            ps.setString(3, en.getTipodoc());
            ps.setString(4, en.getDocumento());
            ps.setInt(5, en.getIdArea());
            ps.setInt(6, en.getIdCargo());
            ps.setString(7, en.getTelefono());
            ps.setString(8, en.getCorreo());
            ps.setInt(9, en.getId());
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
    public boolean Buscar(empleado em){
       String sql="SELECT * from empleados where id_empleado=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, em.getId());
            rs=ps.executeQuery();
            if(rs.next()){
                em.setId(rs.getInt(1));
                em.setNombre(rs.getString(2));
                em.setApellido(rs.getString(3));
                em.setTipodoc(rs.getString(4));
                em.setDocumento(rs.getString(5));
                em.setIdArea(rs.getInt(6));
                em.setIdCargo(rs.getInt(7));
                em.setTelefono(rs.getString(8));
                em.setCorreo(rs.getString(9));
                return true;
            }else{
                 return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
   }
      public boolean eliminar(empleado em){
          
         String sql="delete from empleados where id_empleado=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql); 
            ps.setInt(1, em.getId());
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
       public boolean BuscarEmpleadoN(empleado em){
       String sql="SELECT * from empleados where documento=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDocumento());
            rs=ps.executeQuery();
            if(rs.next()){
                em.setId(rs.getInt(1));
                em.setNombre(rs.getString(2));
                em.setApellido(rs.getString(3));
                em.setTipodoc(rs.getString(4));
                em.setDocumento(rs.getString(5));
                em.setIdArea(rs.getInt(6));
                em.setIdCargo(rs.getInt(7));
                em.setTelefono(rs.getString(8));
                em.setCorreo(rs.getString(9));
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

     
     
    
    
   

