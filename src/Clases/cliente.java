package Clases;

public class cliente {
    public int id;
    public int cedula;
    public String nomCli;
    public String mailCli;
    public String fechaCli;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getMailCli() {
        return mailCli;
    }

    public void setMailCli(String mailCli) {
        this.mailCli = mailCli;
    }

    public String getFechaCli() {
        return fechaCli;
    }

    public void setFechaCli(String fechaCli) {
        this.fechaCli = fechaCli;
    }
}
