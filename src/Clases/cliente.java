package Clases;

public class cliente {
    public static int id;
    public static int cedula;
    public static String nomCli;
    public static String mailCli;
    public static String celular;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        cliente.id = id;
    }

    public static int getCedula() {
        return cedula;
    }

    public static void setCedula(int cedula) {
        cliente.cedula = cedula;
    }

    public static String getNomCli() {
        return nomCli;
    }

    public static void setNomCli(String nomCli) {
        cliente.nomCli = nomCli;
    }

    public static String getMailCli() {
        return mailCli;
    }

    public static void setMailCli(String mailCli) {
        cliente.mailCli = mailCli;
    }

    public static String getCelular() {
        return celular;
    }

    public static void setCelular(String celular) {
        cliente.celular = celular;
    }
}
