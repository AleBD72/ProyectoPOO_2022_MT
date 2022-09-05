import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Administracion extends JFrame{
    private JPanel usuariosPanel;
    private JTabbedPane AdminUserPane;
    private JButton volverBT;
    private JButton SalirSistemaBT;
    private JTable usersReg;
    private JTextField codUserTF;
    private JTextField rolUserTF;
    private JTextField nomUserTF;
    private JTextField mailUserTF;
    private JTextField celUserTF;
    private JTextField passUserTF;
    private JTable userAd;
    private JButton crearUButton;
    private JButton editarUButton;
    private JButton eliminarUButton;
    private JButton buscarUButton;
    private JButton limpiarUButton;

    public Administracion(){
        frameInit();
        setContentPane(usuariosPanel);
        this.setTitle("Ventas Farmacia MCTJ ");
        this.setSize(820,440);
        //this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        CargarTablaUsuariosP1();
        CargarTablaUsuariosP2();
        volverBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administracion.this.dispose();
                Administracion.this.setVisible(false);
                sistema sistema2 = new sistema();
                sistema2.setVisible(true);
            }
        });
        SalirSistemaBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administracion.this.dispose();
                Administracion.this.setVisible(false);
                Administracion.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        crearUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        eliminarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buscarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        limpiarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void CargarTablaUsuariosP1(){

        String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        String USERNAME="root";
        String PASSWORD="";

        String sql = "SELECT * FROM usuarios";
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("CÓDIGO");
        model.addColumn("CÓDIGO DE ROL");
        model.addColumn("NOMBRE");
        model.addColumn("EMAIL");
        model.addColumn("CELULAR");

        usersReg.setModel(model);

        String[] dato = new String[5]; //numero de columnas

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
                dato[4]=result.getString(5);

                model.addRow(new Object[]{dato[0], dato[1], dato[2], dato[3],dato[4]});
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void CargarTablaUsuariosP2(){

        String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        String USERNAME="root";
        String PASSWORD="";

        String sql = "SELECT * FROM usuarios";
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("CÓDIGO");
        model.addColumn("CÓDIGO DE ROL");
        model.addColumn("NOMBRE");
        model.addColumn("EMAIL");
        model.addColumn("CELULAR");
        model.addColumn("CLAVE");

        userAd.setModel(model);

        String[] dato = new String[6]; //numero de columnas

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
                dato[4]=result.getString(5);
                dato[5]=result.getString(6);

                model.addRow(new Object[]{dato[0], dato[1], dato[2], dato[3],dato[4],dato[5]});
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        Administracion adm= new Administracion();
    }
}
