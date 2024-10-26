
package clases;

import java.sql.Date;

/**
 *
 * @author anoni
 */
public class nomina {
    int id;
    Date fecha;
    int ctrabajo;
    double total;
    int idempleado;
    int idcargo;
    String estado;

    public nomina() {
    }

    public nomina(int id, Date fecha, int ctrabajo, double total, int idempleado, int idcargo, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.ctrabajo = ctrabajo;
        this.total = total;
        this.idempleado = idempleado;
        this.idcargo = idcargo;
        this.estado = estado;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  
    
    
    
    
}
