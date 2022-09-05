import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bodega extends JFrame {
    private JTabbedPane productosP;
    private JPanel BodegaPanel;
    private JButton volverBT;
    private JButton SalirSistemaBT;
    private JTable listaProd;
    private JTextField codProdTF;
    private JTextField nomProdTF;
    private JTextField stockProdTf;
    private JTextField precioProdTF;
    private JTable productosT;
    private JButton agregarBT;
    private JButton editarBT;
    private JButton eliminarBT;
    private JButton buscarBT;
    private JButton limpiarBT;

    public Bodega() {
        frameInit();
        setContentPane(BodegaPanel);
        this.setTitle("Ventas Farmacia MCTJ ");
        this.setSize(820,440);
        //this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        CargarProductosP();
        CargarProductos();

        volverBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bodega.this.dispose();
                Bodega.this.setVisible(false);
                sistema sistema2 = new sistema();
                sistema2.setVisible(true);
            }
        });
        SalirSistemaBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bodega.this.dispose();
                Bodega.this.setVisible(false);
                Bodega.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        agregarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        eliminarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buscarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        limpiarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void CargarProductos(){

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

    public void CargarProductosP(){

        String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        String USERNAME="root";
        String PASSWORD="";

        String sql = "SELECT * FROM productos";
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("COD. PRODUCTO");
        model.addColumn("NOM. PRODUCTO");
        model.addColumn("STOCK");
        model.addColumn("PRECIO");

        listaProd.setModel(model);

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
        Bodega bdg= new Bodega();
    }
}
