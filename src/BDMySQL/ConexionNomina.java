
package BDMySQL;


//**
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
//

/**
 *
 * @author anoni
 */
public class ConexionNomina {
    int id;
    Date fecha;
    int ctrabajo;
    Double total;
     int idempleado;
      int idcargo;

    public ConexionNomina() {
    }

    public ConexionNomina(int id, Date fecha, int ctrabajo, Double total, int idempleado, int idcargo) {
        this.id = id;
        this.fecha = fecha;
        this.ctrabajo = ctrabajo;
        this.total = total;
        this.idempleado = idempleado;
        this.idcargo = idcargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCtrabajo() {
        return ctrabajo;
    }

    public void setCtrabajo(int ctrabajo) {
        this.ctrabajo = ctrabajo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }
      
}
