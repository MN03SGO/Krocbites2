
package BDMySQL;


import clases.nomina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author anoni
 */
public class ConexionNomina {
    Connection con;
     conexion cn= new conexion();
     PreparedStatement ps;
     ResultSet rs;
    
    public boolean insertar(nomina no){
        String sql="insert into nomina (fecha,c.trabajo,total,idempleado,idcargo) values (?,?,?,?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setDate(1, no.getFecha());
            ps.setInt(2, no.getCtrabajo());
            ps.setDouble(3, no.getTotal());
            ps.setInt(4, no.getIdempleado());
            ps.setInt(5, no.getIdcargo());
          
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
