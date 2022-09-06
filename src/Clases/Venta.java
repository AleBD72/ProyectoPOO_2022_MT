package Clases;

public class Venta {
    public static int clCed;
    public static int Usuario;

    public static String cliente;
    public static Double Total;

    public static int getUsuario() {
        return Usuario;
    }

    public static void setUsuario(int usuario) {
        Usuario = usuario;
    }

    public static int getClCed() {
        return clCed;
    }

    public static void setClCed(int clCed) {
        Venta.clCed = clCed;
    }

    public static String getCliente() {
        return cliente;
    }

    public static void setCliente(String cliente) {
        Venta.cliente = cliente;
    }

    public static Double getTotal() {
        return Total;
    }

    public static void setTotal(Double total) {
        Total = total;
    }
}
