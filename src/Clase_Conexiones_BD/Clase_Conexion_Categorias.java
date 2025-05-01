/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clase_Conexiones_BD;

/**
 *
 * @author anoni
 */
public class Clase_Conexion_Categorias {
    int  id_Categoria;
    String categoria;

    public Clase_Conexion_Categorias() {
    }

    public Clase_Conexion_Categorias(int id_Categoria, String categoria) {
        this.id_Categoria = id_Categoria;
        this.categoria = categoria;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    
    
}
