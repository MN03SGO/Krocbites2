package Conexiones_BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

//import Conexiones_BD.Conexion_BD;
import Clase_Conexiones_BD.Clase_Conexion_Usuarios;
import java.awt.HeadlessException;
/**
 *
 * @author anoni
 */
public class Conexion_Usuarios {

    public Clase_Conexion_Usuarios login(String usuario, char[] contra) throws SQLException {
        Clase_Conexion_Usuarios Conec_Usu = null;
        String sql = "SELECT * FROM usuarios WHERE usuario=?";
        try {
            Connection con = new Conexion_BD().conectar();
            PreparedStatement PreD = con.prepareStatement(sql);
            PreD.setString(1, usuario);
            ResultSet Rest = PreD.executeQuery();

            if (Rest.next()) {
                String contraBD = Rest.getString("contra");     // Conversion de String a char
                char[] contraBDChar = contraBD.toCharArray(); 

                if (java.util.Arrays.equals(contra, contraBDChar)) {
                    Conec_Usu = new Clase_Conexion_Usuarios();
                    Conec_Usu.setId_Usuarios(Rest.getInt("Id_Usuarios"));
                    Conec_Usu.setNombre(Rest.getString("nombre"));
                    Conec_Usu.setApellido(Rest.getString("apellido"));
                    Conec_Usu.setDocumento(Rest.getString("documento"));
                    Conec_Usu.setTelefono(Rest.getString("telefono"));
                    Conec_Usu.setCorreo(Rest.getString("correo"));
                    Conec_Usu.setTipo_usuario(Rest.getString("tipo_usuario"));
                    Conec_Usu.setUsuario(Rest.getString("usuario"));
                    Conec_Usu.setContra(contraBD);
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                }

                java.util.Arrays.fill(contraBDChar, '0');
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el login: " + e.getMessage());
        }
        return Conec_Usu;
    }
}
