package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

public class UserCrud extends JDialog{
    private JPanel MainPanelUser;
    private JTextField textNombre;
    private JTextField textEmail;
    private JTextField textCelular;
    private JButton createBtn;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JTextField textId;
    private JTextField textClave;
    private JButton limpiarBtn;
    private JButton buscarBtn;
    private JTextField textRol;

    public UserCrud() {
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
        setTitle("Administrador de Usuarios");
        setContentPane(MainPanelUser);
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
        String rol, nombre, email, celular, clave;
        rol = textRol.getText();
        nombre = textNombre.getText();
        email = textEmail.getText();
        celular = textCelular.getText();
        clave = textClave.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO usuarios(CODROL, NOMUSER, EMAILUSER, CELLUSER, CLAVEUSER)VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, rol);
            pst.setString(2, nombre);
            pst.setString(3, email);
            pst.setString(4, celular);
            pst.setString(5, clave);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario agregado");
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
        textEmail.setText("");
        textCelular.setText("");
        textId.setText("");
        textClave.setText("");
        textRol.setText("");
    }

    public void Buscar(){
        String id="0";
        id=textId.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM usuarios where CODUSER=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,id);
            //System.out.println(sql);

            ResultSet rs = pst.executeQuery(); //select

            if (rs.next()==true){ //no esta vacio
                String rol, nombre, email, cell,clave;
                rol=rs.getString(2); // 1 es el id
                nombre=rs.getString(3);
                email=rs.getString(4);
                cell=rs.getString(5);
                clave=rs.getString(6);

                System.out.println();
                textRol.setText(rol);
                textNombre.setText(nombre);
                textCelular.setText(cell);
                textEmail.setText(email);
                textClave.setText(clave);
            }
            else {
                //textMensaje.setText("No se ha encontrado el producto");
                JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario");
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
        String id, rol, nombre, celular, email, clave;

        id=textId.getText();
        rol=textRol.getText();
        nombre=textNombre.getText();
        celular= textCelular.getText();
        email= textEmail.getText();
        clave= textClave.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE usuarios set CODROL=?, NOMUSER=?, EMAILUSER=?, CELLUSER=?, CLAVEUSER=? WHERE CODUSER=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, rol);
            pst.setString(2, nombre);
            pst.setString(3, email);
            pst.setString(4, celular);
            pst.setString(5, clave);
            pst.setString(6, id);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuario actualizado");
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

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM usuarios WHERE CODUSER=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, borrarId);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuario borrado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

    public static void main(String[] args) {
        UserCrud crudUser = new UserCrud();
    }
}
