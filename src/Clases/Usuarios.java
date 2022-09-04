//Clase Usuario
//Almacenará todos los datos obtenidos sobre el usuario.
package Clases;

public class Usuarios {
    public String nombre;
    public String mail;
    public String celular;
    public String clave;
    public int codUser;
    public String tipoUser;

    public int getCodUser() {
        return codUser;
    }

    public void setCodUser(int codUser) {
        this.codUser = codUser;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

}
