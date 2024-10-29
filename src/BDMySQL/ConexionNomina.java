
package BDMySQL;


import clases.nomina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        String sql="insert into nomina (fecha,ctrabajo,total,idempleado,idcargo,estado) values (?,?,?,?,?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setDate(1, no.getFecha());
            ps.setInt(2, no.getCtrabajo());
            ps.setDouble(3, no.getTotal());
            ps.setInt(4, no.getIdempleado());
            ps.setInt(5, no.getIdcargo());
            ps.setString(6, no.getEstado());
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
         String [] titulos={"ID Nomina","Fecha","ID Empleado","Nombre","Apellido","Documento",
            "ID Cargo","Cargo","Precio Cargo","Cant","Total","Estado"};
         String [] registros=new String[11];
        modelo=new DefaultTableModel(null,titulos);
        
        String sql="SELECT n.id,n.fecha,n.idempleado,e.nombre,e.apellido,e.documento,\n" +
                    "n.idcargo,c.nom_cargo,c.pago,n.ctrabajo,n.total FROM nomina n\n" +
                    "INNER JOIN empleados e\n" +
                    "ON n.idempleado=e.id_empleado\n" +
                    "INNER JOIN cargos c\n" +
                    "on e.id_cargo=c.id_cargo;";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                registros[0]=rs.getString("id");
                registros[1]=rs.getString("fecha");
                registros[2]=rs.getString("idempleado");
                registros[3]=rs.getString("nombre");
                registros[4]=rs.getString("apellido");
                registros[5]=rs.getString("documento");
                registros[6]=rs.getString("idcargo");
                registros[7]=rs.getString("nom_cargo");
                registros[8]=rs.getString("pago");
                registros[9]=rs.getString("ctrabajo");
                registros[10]=rs.getString("total");
               
                modelo.addRow(registros);
            }
            return modelo;
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        } 
    }
 public boolean editar(nomina cr){
       String sql="update nomina set fecha=?,ctrabajo=?,total=?,idempleado=?,idcargo=? where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setDate(1, cr.getFecha());
            ps.setInt(2, cr.getCtrabajo());
            ps.setDouble(3, cr.getTotal());
            ps.setInt(4, cr.getIdempleado());
            ps.setInt(5, cr.getIdcargo());
            ps.setInt(6, cr.getId());
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
 public boolean Buscar(nomina no){
       String sql="SELECT * from nomina where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, no.getId());
            rs=ps.executeQuery();
            if(rs.next()){
                no.setId(rs.getInt(1));
                no.setFecha(rs.getDate(2));
                no.setCtrabajo(rs.getInt(3));
                no.setTotal(rs.getDouble(4));
                no.setIdempleado(rs.getInt(5));
                no.setIdcargo(rs.getInt(6));
                return true;
            }else{
                 return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
   }
 public boolean elimiar(nomina no){
       String sql="delete from nomina where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql); 
            ps.setInt(1, no.getId());
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
                           
