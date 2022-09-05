package Clases;

public class UserGeneral {
    public static String codigoUser;
    public static String nombreUser;
    public static String emailUser;
    public static String cellUser;
    public static String rolUser;

    public static String getCodigoUser() {
        return codigoUser;
    }

    public static void setCodigoUser(String codigoUser) {
        UserGeneral.codigoUser = codigoUser;
    }

    public static String getNombreUser() {
        return nombreUser;
    }

    public static void setNombreUser(String nombreUser) {
        UserGeneral.nombreUser = nombreUser;
    }

    public static String getEmailUser() {
        return emailUser;
    }

    public static void setEmailUser(String emailUser) {
        UserGeneral.emailUser = emailUser;
    }

    public static String getCellUser() {
        return cellUser;
    }

    public static void setCellUser(String cellUser) {
        UserGeneral.cellUser = cellUser;
    }

    public static String getRolUser() {
        return rolUser;
    }

    public static void setRolUser(String rolUser) {
        UserGeneral.rolUser = rolUser;
    }
}
