
package clases;

import java.util.Date;

/**
 *
 * @author anoni
 */
public class pago {
    int id;
    int idempleado;
    double total;
    Date fecha1;
    Date fecha2; 

    public pago() {
    }

    public pago(int id, int idempleado, double total, Date fecha1, Date fecha2) {
        this.id = id;
        this.idempleado = idempleado;
        this.total = total;
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }
    
    
    
}
