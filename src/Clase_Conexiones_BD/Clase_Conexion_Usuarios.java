/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clase_Conexiones_BD;

/**
 *
 * @author anoni
 */
public class Clase_Conexion_Usuarios {
    int id_Usuarios;
    String nombre;
    String apellido;
    String documento;
    String direccion;
    String telefono;
    String correo;
    String tipo_usuario;
    String usuario;
    String contra;

    public Clase_Conexion_Usuarios() {
    }

    public Clase_Conexion_Usuarios(int id_Usuarios, String nombre, String apellido, String documento, String direccion, String telefono, String correo, String tipo_usuario, String usuario, String contra) {
        this.id_Usuarios = id_Usuarios;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo_usuario = tipo_usuario;
        this.usuario = usuario;
        this.contra = contra;
    }

    public int getId_Usuarios() {
        return id_Usuarios;
    }

    public void setId_Usuarios(int id_Usuarios) {
        this.id_Usuarios = id_Usuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
    
}
