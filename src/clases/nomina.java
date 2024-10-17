
package clases;

import java.util.Date;

/**
 *
 * @author anoni
 */
public class nomina {
    int id;
    Date fecha;
    int ctrabajo;
    int idempleado;
    int idcargo;

    public nomina() {
    }

    public nomina(int id, Date fecha, int ctrabajo, int idempleado, int idcargo) {
        this.id = id;
        this.fecha = fecha;
        this.ctrabajo = ctrabajo;
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
