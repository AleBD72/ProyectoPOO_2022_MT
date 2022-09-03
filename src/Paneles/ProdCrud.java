package Paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

public class ProdCrud extends JDialog{
    private JPanel MainPanelProd;
    private JTextField textNombre;
    private JTextField textCantidad;
    private JTextField textPrecio;
    private JButton createBtn;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JTextField textId;

    private JButton limpiarBtn;
    private JButton buscarBtn;
    public ProdCrud() {
        Connect();

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Create();
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete();
            }
        });
        limpiarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
            }
        });
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar();
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });
        MostrarVentana();
    }

    public void MostrarVentana(){
        setTitle("Administrador Productos");
        setContentPane(MainPanelProd);
        setMinimumSize(new Dimension(640,480));
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
    }

    public void Connect(){
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stat = conn.createStatement();
            //System.out.println("Conexion exitosa");
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

    public void Create(){
        String cod, nombre, cantidad, precio;
        cod = textId.getText();
        nombre = textNombre.getText();
        precio = textPrecio.getText();
        cantidad = textCantidad.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();

            // COMPROBARCIÓN SI YA EXISTE REGISTRO
            String sql = "SELECT * FROM productos where CODPROD=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,cod);
            //System.out.println(sql);

            ResultSet rs = pst.executeQuery(); //select

            if (rs.next()==true){//no esta vacio NO SE INSERTA
                JOptionPane.showMessageDialog(null, "Producto ya en existencia");

            } else{//esta vacio SE INSERTA
                String sql1 = "INSERT INTO productos(NOMPROD, STOCKPROD, PRECIOPROD)VALUES(?,?,?)";
                PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst1.setString(1, nombre);
                pst1.setString(2, cantidad);
                pst1.setString(3, precio);
                //ResultSet resultSet = pst.executeQuery(); //select
                pst1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Producto agregado");
            }
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Producto no agregado");
        }
    }

    public void Limpiar(){
        textNombre.setText("");
        textPrecio.setText("");
        textId.setText("");
        textCantidad.setText("");
    }

    public void Buscar(){
        String cod="0";
        cod=textId.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM productos where CODPROD=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,cod);
            //System.out.println(sql);

            ResultSet rs = pst.executeQuery(); //select

            if (rs.next()==true){ //no esta vacio
                String nombre, cantidad, precio;
                nombre=rs.getString(2); // 1 es el id
                cantidad=rs.getString(3);
                precio=rs.getString(4);

                //System.out.println();
                textNombre.setText(nombre);
                textPrecio.setText(precio);
                textCantidad.setText(cantidad);
            }
            else {
                //textMensaje.setText("No se ha encontrado el producto");
                JOptionPane.showMessageDialog(null, "No se ha encontrado el producto");
                Limpiar();
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

    public void Update(){
        String cod, nombre, precio, cantidad;

        cod=textId.getText();
        nombre=textNombre.getText();
        precio=textPrecio.getText();
        cantidad=textCantidad.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE productos set NOMPROD=?, STOCKPROD=?, PRECIOPROD=? WHERE CODPROD=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, cantidad);
            pst.setString(3, precio);
            pst.setString(4, cod);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro actualizado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }

    }

    public void Delete(){
        String borrarId=textId.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM productos WHERE CODPROD=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, borrarId);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro borrado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

    public static void main(String[] args) {
        ProdCrud crudProd = new ProdCrud();
    }

}
