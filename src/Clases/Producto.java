package Clases;

public class Producto {
    public static String cod;
    public static String nombre;
    public static int stock;
    public static double precio;

    public static String getCod() {
        return cod;
    }

    public static void setCod(String cod) {
        Producto.cod = cod;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Producto.nombre = nombre;
    }

    public static int getStock() {
        return stock;
    }

    public static void setStock(int stock) {
        Producto.stock = stock;
    }

    public static double getPrecio() {
        return precio;
    }

    public static void setPrecio(double precio) {
        Producto.precio = precio;
    }
}
