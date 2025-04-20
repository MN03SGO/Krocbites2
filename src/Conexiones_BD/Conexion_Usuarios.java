package Conexiones_BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import Conexiones_BD.Conexion_BD;
import Clase_Conexiones_BD.Clase_Conexion_Usuarios;

/**
 *
 * @author anoni
 */
public class Conexion_Usuarios {
    
    public Clase_Conexion_Usuarios login(String usuario, String pass) {
        Clase_Conexion_Usuarios us = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contra = ?";

        try {
            Connection con = new Conexion_BD().conectar(); 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                us = new Clase_Conexion_Usuarios();
                us.setId_Usuarios(rs.getInt("id_Usuarios"));
                us.setNombre(rs.getString("nombre"));
                us.setApellido(rs.getString("apellido"));
                us.setDocumento(rs.getString("documento"));
                us.setDireccion(rs.getString("direccion"));
                us.setTelefono(rs.getString("telefono"));
                us.setCorreo(rs.getString("correo"));
                us.setTipo_usuario(rs.getString("tipo_usuario"));                
                us.setUsuario(rs.getString("usuario"));
                us.setContra(rs.getString("contra"));
               
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en login: " + e.getMessage());
        }

        return us;
    }
}
