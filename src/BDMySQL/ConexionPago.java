/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import clases.pago;
import java.sql.Date;

/**
 *
 * @author anoni
 */
public class ConexionPago {
    Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertar(pago p){
        String sql="insert into pagos (idempleado,total,fecha1,fecha2) values (?,?,?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, p.getIdempleado());
            ps.setDouble(2, p.getTotal());
            ps.setDate(3, new java.sql.Date(p.getFecha1().getTime()));
            ps.setDate(4, new java.sql.Date(p.getFecha2().getTime()));
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
     public pago Total(int ide,String f1,String f2){
        pago pa=new pago();
        String sql="select sum(total) from nomina WHERE idempleado="+ide+" and fecha BETWEEN '"+f1+"' and '"+f2+"'";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                pa.setTotal(rs.getDouble(1));
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return pa;
    }
      public boolean editarN(int ide,String f1,String f2,String estado){
       String sql="UPDATE nomina set estado='"+estado+"' WHERE idempleado="+ide+" and fecha BETWEEN  '"+f1+"' and '"+f2+"'";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
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
       public DefaultTableModel listar(){
        DefaultTableModel modelo;
        
        String [] titulos={"ID Pago","ID Empleado","Nombre","Apellido","Documento",
            "ID Cargo","Cargo","fecha 1","fecha 2","Total"};
        
        String [] registros=new String[10];
        modelo=new DefaultTableModel(null,titulos);
        
        String sql="SELECT p.id,p.idempleado,e.nombre,e.apellido,e.documento,\n" +
                "e.id_cargo,c.nom_cargo,p.fecha1,p.fecha2,p.total FROM pagos p \n" +
                "INNER JOIN empleados e \n" +
                "on e.id_empleado=p.idempleado\n" +
                "INNER JOIN cargos c \n" +
                "ON c.id_cargo=e.id_cargo";
        
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                registros[0]=rs.getString("id");
                registros[1]=rs.getString("idempleado");
                registros[2]=rs.getString("nombre");
                registros[3]=rs.getString("apellido");
                registros[4]=rs.getString("documento");
                registros[5]=rs.getString("id_cargo");
                registros[6]=rs.getString("nom_cargo");
                registros[7]=rs.getString("fecha1");
                registros[8]=rs.getString("fecha2");
                registros[9]=rs.getString("total");
                modelo.addRow(registros);
            }
            return modelo;
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
       }
        public boolean editar(pago pa){
       String sql="update pagos set idempleado=?,total=?,fecha1=?,fecha2=? where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, pa.getIdempleado());
            ps.setDouble(2, pa.getTotal());
            ps.setDate(3, (Date) pa.getFecha1());
            ps.setDate(4, (Date) pa.getFecha2());
            ps.setInt(5, pa.getId());
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
         public boolean elimiar(pago pa){
       String sql="delete from pagos where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql); 
            ps.setInt(1, pa.getId());
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
