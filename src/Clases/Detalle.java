package Clases;

public class Detalle {
    public static int codDet;
    public static int CodVenta;
    public static int nomProd;
    public static int cantidad;
    public static double precio;

    public static int getCodDet() {
        return codDet;
    }

    public static void setCodDet(int codDet) {
        Detalle.codDet = codDet;
    }

    public static int getCodVenta() {
        return CodVenta;
    }

    public static void setCodVenta(int codVenta) {
        CodVenta = codVenta;
    }

    public static void setNomProd(int nomProd) {
        Detalle.nomProd = nomProd;
    }

    public static int getNomProd() {
        return nomProd;
    }

    public static int getCantidad() {
        return cantidad;
    }

    public static void setCantidad(int cantidad) {
        Detalle.cantidad = cantidad;
    }

    public static double getPrecio() {
        return precio;
    }

    public static void setPrecio(double precio) {
        Detalle.precio = precio;
    }
}
