
package clases;

public class usuarios {
    int idUser;
    String nombre;
     String usuario;
      String password;

    public usuarios() {
       
    }

    public usuarios(int idUser, String nombre, String usuario, String password) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      
}
