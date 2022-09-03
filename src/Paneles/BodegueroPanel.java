package Paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BodegueroPanel extends JDialog{
    private JTable tablaProd;
    private JPanel bodegaP;
    private JLabel Titulo;
    private JLabel subtitle;


    public BodegueroPanel(){
        Connect();
        CargarDatosTabla();
        MostrarVentana();
    }

    public void MostrarVentana(){
        setTitle("BODEGA");
        setContentPane(bodegaP);
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
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

    public void CargarDatosTabla(){

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        String sql = "SELECT * FROM productos";
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("CODPROD");
        model.addColumn("NOMBRE");
        model.addColumn("STOCK");
        model.addColumn("PRECIO");

        tablaProd.setModel(model);

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

    public static void main(String[] args) {
        BodegueroPanel bodega = new BodegueroPanel();
    }

}
