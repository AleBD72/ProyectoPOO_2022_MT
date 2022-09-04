package Clases;

public class Roles {

    private String codRol;
    private String rol;

    public String getCodRol() {
        return codRol;
    }
    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return getRol();
    }
}
