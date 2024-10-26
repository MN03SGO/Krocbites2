/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import javax.swing.ImageIcon;
/**
 *
 * @author anoni
 */
public class datos {
    int id;
    String nombre;
    String RUC;
    String RasonS;
    String Telefono;
    String Direccion;
    String correo;
    byte[] imagen;

    public datos() {
    }

    public datos(int id, String nombre, String RUC, String RasonS, String Telefono, String Direccion, String correo, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.RUC = RUC;
        this.RasonS = RasonS;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.correo = correo;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRasonS() {
        return RasonS;
    }

    public void setRasonS(String RasonS) {
        this.RasonS = RasonS;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    
    
    
}
