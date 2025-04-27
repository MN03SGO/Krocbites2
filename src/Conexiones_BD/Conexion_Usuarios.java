
package Conexiones_BD;

import Clase_Conexiones_BD.Clase_Conexion_Usuarios;
import Clase_Conexiones_BD.Encriptador;
import Conexiones_BD.Conexion_BD;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
                String contraBD = Rest.getString("contra"); // Contraseña en hash guardada en la base
                String contraIngresada = new String(contra); // char[] a String
                String contraIngresadaHash = Encriptador.hashSHA256(contraIngresada); // Hash de la ingresada

                if (contraIngresadaHash.equals(contraBD)) { // Comparas los hashes
                    Conec_Usu = new Clase_Conexion_Usuarios();
                    Conec_Usu.setId_Usuarios(Rest.getInt("Id_Usuarios"));
                    Conec_Usu.setNombre(Rest.getString("nombre"));
                    Conec_Usu.setApellido(Rest.getString("apellido"));
                    Conec_Usu.setDocumento(Rest.getString("documento"));
                    Conec_Usu.setTelefono(Rest.getString("telefono"));
                    Conec_Usu.setCorreo(Rest.getString("correo"));
                    Conec_Usu.setTipo_usuario(Rest.getString("tipo_usuario"));
                    Conec_Usu.setUsuario(Rest.getString("usuario"));
                    Conec_Usu.setContra(contraBD); // Guardas el hash (opcional si quieres)
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                }

                java.util.Arrays.fill(contra, '0'); // Limpias la contraseña ingresada
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el login: " + e.getMessage());
        }
        return Conec_Usu;
    }
}