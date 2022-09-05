import Clases.Usuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Ventas extends JFrame{


    private JTabbedPane tabbedPane1;
    private JPanel ventasPanel;
    private JButton SalirSistemaBT;
    private JButton volverBT;
    private JTextField codVentaTF;
    private JTextField prodTF;
    private JTextField cantiTF;
    private JTextField precioTF;
    private JTextField stockTF;
    private JButton eliminaVentaBT;
    private JTable Ventas;
    private JTextField rucVentaTF;
    private JTextField nombreClienteTF;
    private JButton generarBT;
    private JTextField dniCliTF;
    private JTextField nomCliTF;
    private JTextField celTF;
    private JTextField mailTF;
    private JTable ventasT;
    private JButton guardarBT;
    private JButton actualizarBT;
    private JButton eliminarCliBT;
    private JButton buscarBT;
    private JTextField codProdTF;
    private JTextField nomProdTF;
    private JTextField stockProdTF;
    private JTextField precioProdTF;
    private JTable productosT;
    private JButton buscarProdBT;
    private JButton excelBT;
    private JTable clientesT;
    private JTable ventasGuardadasT;
    private JButton generarNotaDeVentaBT;
    private JLabel totalL;
    private JButton actualizarFarBT;
    private JTextField rucFarTF;
    private JTextField nomFarTF;
    private JTextField direcFarTF;
    private JTextField telfFarTF;
    private JTextField idCliTF;
    private JButton limpiarButton;

    public Ventas() {
        Usuarios user = new Usuarios();
        frameInit();
        this.setContentPane(ventasPanel);
        this.setTitle("Ventas Farmacia MCTJ ");
        this.setSize(990,390);
        //this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null); // centrar la pantalla
        //llmado a tablas
        CargarTablaProductos();
        CargarTablaClientes();
        volverBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventas.this.dispose();
                Ventas.this.setVisible(false);
                sistema sistema1 = new sistema(user.codUser, user.tipoUser, user.nombre, user.mail, user.celular);
                sistema1.setVisible(true);
            }
        });
        SalirSistemaBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventas.this.dispose();
                Ventas.this.setVisible(false);
                Ventas.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        guardarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Verificamos que los campos ingresados no estén vacios
                if(!"".equals(dniCliTF.getText()) && !"".equals(nomCliTF.getText()) && !"".equals(celTF.getText())){
                    RegistrarCli();
                } else {
                    JOptionPane.showMessageDialog(null,"Los campos están vacios");
                }
                dniCliTF.setText(null);
                nomCliTF.setText(null);
                celTF.setText(null);
                mailTF.setText(null);
            }
        });
        actualizarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(idCliTF.getText()) && !"".equals(dniCliTF.getText()) && !"".equals(nomCliTF.getText()) && !"".equals(celTF.getText())){
                    ActualizarCli();
                } else {
                    JOptionPane.showMessageDialog(null,"Los campos están vacios");
                }
                dniCliTF.setText(null);
                nomCliTF.setText(null);
                celTF.setText(null);
                mailTF.setText(null);
            }
        });
        buscarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(idCliTF.getText())){
                    BuscarCliente();
                } else {
                    JOptionPane.showMessageDialog(null,"Los campos están vacios");
                }

            }
        });
        eliminarCliBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(idCliTF.getText())){
                    BorrarCliente();
                } else {
                    JOptionPane.showMessageDialog(null,"Los campos están vacios");
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
            }
        });
    }

    public static void main(String[] args) {
        Ventas ventitas = new Ventas();
    }

    //Funciones de los botones Jtabbed Pane

    //Limpieza de campos
    public void Limpiar(){
        idCliTF.setText(null);
        dniCliTF.setText(null);
        nomCliTF.setText(null);
        celTF.setText(null);
        mailTF.setText(null);
    }

    //FUNCIONES CLIENTES
    //FUNCIÓN REGISTRAR UN CLIENTE
    public void RegistrarCli(){
        String cel, nombre, mail;
        int cedula;
        cel = celTF.getText();
        nombre = nomCliTF.getText();
        mail = mailTF.getText();
        cedula = Integer.parseInt(dniCliTF.getText());

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO clientes (CEDULACL, NOMCLI, CELCLI, MAILCLI) VALUES (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, cedula);
            pst.setString(2, nombre);
            pst.setString(3, cel);
            pst.setString(4, mail);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente agregado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cliente no agregado");
        }
    }

    public void BuscarCliente(){
        int id;
        id=Integer.parseInt(idCliTF.getText());
        String rcelu, nombre, mail;
        int cedulaCl;

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM clientes where IDCLI=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,id);
            //System.out.println(sql);

            ResultSet rs = pst.executeQuery(); //select

            if (rs.next()==true){ //no esta vacio

                cedulaCl=rs.getInt(2); // 1 es el id
                nombre=rs.getString(3);
                rcelu=rs.getString(4);
                mail=rs.getString(5);


                dniCliTF.setText(""+cedulaCl);
                nomCliTF.setText(nombre);
                celTF.setText(rcelu);
                mailTF.setText(mail);
                System.out.println(cedulaCl+nombre+rcelu+mail);
                System.out.println();

            }
            else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el cliente");
                dniCliTF.setText(null);
                nomCliTF.setText(null);
                celTF.setText(null);
                mailTF.setText(null);
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }
    public void ActualizarCli(){
        String  celu, nombre, mail;
        int id,cedulaCl;

        id= Integer.parseInt(idCliTF.getText());
        celu = celTF.getText();
        nombre = nomCliTF.getText();
        mail = mailTF.getText();
        cedulaCl = Integer.parseInt(dniCliTF.getText());



        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE clientes set CEDULACL=?, NOMCLI=?, CELCLI=?, MAILCLI=? WHERE IDCLI=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, cedulaCl);
            pst.setString(2, nombre);
            pst.setString(4, mail);
            pst.setString(3, celu);
            pst.setInt(5, id);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cliente actualizado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }

    }

    public void BorrarCliente(){
        String borrarId=idCliTF.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM clientes WHERE IDCLI=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, borrarId);
            //ResultSet resultSet = pst.executeQuery(); //select
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cliente borrado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

    public void limpiarCamposCli(){

    }


    //Cargar Tablas
    public void CargarTablaClientes(){

        String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        String USERNAME="root";
        String PASSWORD="";

        String sql = "SELECT * FROM clientes";
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("CÉDULA");
        model.addColumn("NOMBRE");
        model.addColumn("CELULAR");
        model.addColumn("MAIL");

        clientesT.setModel(model);

        String[] dato = new String[4]; //numero de columnas

        try {
            Connect();
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery(sql);

            while (result.next()){
                dato[0]=result.getString(2);
                //System.out.println(dato[0]);
                dato[1]=result.getString(3);
                //System.out.println(dato[1]);
                dato[2]=result.getString(4);
                //System.out.println(dato[2]);
                dato[3]=result.getString(5);
                //System.out.println(dato[3]);

                model.addRow(new Object[]{dato[0], dato[1], dato[2], dato[3]});
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void CargarTablaProductos(){

        String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        String USERNAME="root";
        String PASSWORD="";

        String sql = "SELECT * FROM productos";
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("COD. PRODUCTO");
        model.addColumn("NOM. PRODUCTO");
        model.addColumn("STOCK");
        model.addColumn("PRECIO");

        productosT.setModel(model);

        String[] dato = new String[4]; //numero de columnas

        try {
            Connect();
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery(sql);

            while (result.next()){
                dato[0]=result.getString(1);
                //System.out.println(dato[0]);
                dato[1]=result.getString(2);
                //System.out.println(dato[1]);
                dato[2]=result.getString(3);
                //System.out.println(dato[2]);
                dato[3]=result.getString(4);
                //System.out.println(dato[3]);

                model.addRow(new Object[]{dato[0], dato[1], dato[2], dato[3]});
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    //Conexión
    public void Connect(){
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stat = conn.createStatement();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

}
