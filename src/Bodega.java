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
                CrearProd();
                CargarProductos();
                CargarProductosP();
            }
        });
        editarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarProd();
                CargarProductosP();
                CargarProductos();
            }
        });
        eliminarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarProd();
                CargarProductos();
                CargarProductosP();
            }
        });
        buscarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarProd();
            }
        });
        limpiarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
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

    public void CrearProd(){
        if (Double.valueOf(stockProdTf.getText())>= 0 && Double.valueOf(precioProdTF.getText()) >= 0){
            String cod, nombre, cantidad, precio;
            cod = codProdTF.getText();
            nombre = nomProdTF.getText();
            precio = precioProdTF.getText();
            cantidad = stockProdTf.getText();

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
        } else{
            JOptionPane.showMessageDialog(null, "El precio y cantidad deben ser mayores o iguales a 0");
        }
    }

    public void Limpiar(){
        nomProdTF.setText("");
        precioProdTF.setText("");
        codProdTF.setText("");
        stockProdTf.setText("");
    }

    public void BuscarProd(){
        String cod="0";
        cod=codProdTF.getText();

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
                nomProdTF.setText(nombre);
                precioProdTF.setText(precio);
                stockProdTf.setText(cantidad);
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

    public void ActualizarProd(){
        String cod, nombre, precio, cantidad;

        cod=codProdTF.getText();
        nombre=nomProdTF.getText();
        precio=precioProdTF.getText();
        cantidad=stockProdTf.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE productos set NOMPROD=?, STOCKPROD=?, PRECIOPROD=? WHERE CODPROD=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);

            if (Double.valueOf(stockProdTf.getText())>= 0 && Double.valueOf(precioProdTF.getText()) >= 0){
                pst.setString(2, cantidad);
                pst.setString(3, precio);
            }
            else {
                JOptionPane.showMessageDialog(null, "El precio y cantidad deben ser mayores o iguales a 0");
            }

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

    public void BorrarProd(){
        String borrarId=codProdTF.getText();

        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
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

    /*public static void main(String[] args) {
        Bodega bdg= new Bodega();
    }*/
}
