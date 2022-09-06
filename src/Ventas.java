import Clases.*;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.mysql.cj.xdevapi.Table;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

public class Ventas extends JFrame{

    int item;
    int result;
    double TotalPagar=0.0;
    Venta vent = new Venta();
    Detalle det = new Detalle();
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
    private JTable clientesT;
    private JLabel totalL;
    private JTextField idCliTF;
    private JButton limpiarButton;
    private JPanel GVtaPanel;
    private JPanel CliPanel;
    private JPanel ProdPanel;
    private JButton limpiarPdBT;

    Producto pro= new Producto();




    public Ventas() {
        //Usuarios user = new Usuarios();
        frameInit();
        this.setContentPane(ventasPanel);
        this.setTitle("Ventas Farmacia MCTJ ");
        this.setSize(1030,410);
        //this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null); // centrar la pantalla

        //llamado a tablas
        CargarTablaProductos();
        CargarTablaClientes();
        volverBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventas.this.dispose();
                Ventas.this.setVisible(false);
                sistema sistema1 = new sistema();
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
                    CargarTablaClientes();
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
                    CargarTablaClientes();
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
                    CargarTablaClientes();
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
        buscarProdBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(codProdTF.getText())){
                    BuscarProd();
                } else {
                    JOptionPane.showMessageDialog(null,"Los campos están vacios");
                }
            }
        });
        limpiarPdBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposProd();
            }
        });
        codVentaTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()== KeyEvent.VK_ENTER){
                    if (!"".equals(codVentaTF.getText())){
                        String cod=codVentaTF.getText();
                        Producto.setNombre(null);
                        BuscarPro(cod);
                        if ( Producto.getNombre() != null){

                            prodTF.setText(""+Producto.getNombre());
                            precioTF.setText(""+Producto.getPrecio());
                            stockTF.setText(""+Producto.getStock());
                            cantiTF.requestFocus();
                            System.out.println(Producto.getNombre()+Producto.getStock());
                        }else {
                            LimpiarVenta();
                            codVentaTF.requestFocus();
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Ingrese el código del producto");
                        codVentaTF.requestFocus();
                    }
                }
            }
        });
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CODIGO");
        model.addColumn("NOMBRE PROD.");
        model.addColumn("CANTIDA");
        model.addColumn("PRECIO");
        model.addColumn("TOTAL");
        cantiTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                //Tabla de venta prod

                ventasT.setModel(model);

                if(e.getKeyCode()== KeyEvent.VK_ENTER){
                    if(!"".equals(cantiTF.getText())){
                        String cod= codVentaTF.getText();
                        String nombre = prodTF.getText();
                        int cantidad=Integer.parseInt(cantiTF.getText());
                        double precio=Double.parseDouble(precioTF.getText());
                        Double total= cantidad*precio;
                        int stock= Integer.parseInt(stockTF.getText());
                        if(stock >= cantidad) {
                            item = item + 1;
                            //model = (DefaultTableModel) ventasT.getModel();
                            for (int i=0; i<ventasT.getRowCount();i++){
                                if(ventasT.getValueAt(i,1).equals(prodTF.getText())){
                                    JOptionPane.showMessageDialog(null, "El producto ya ha sido registrado");
                                    return;
                                }
                            }
                            ArrayList lista = new ArrayList<>();
                            lista.add(item);
                            lista.add(cod);
                            lista.add(nombre);
                            lista.add(cantidad);
                            lista.add(precio);
                            lista.add(total);

                            Object[] O = new Object[5];
                            O[0] = lista.get(1);
                            O[1] = lista.get(2);
                            O[2] = lista.get(3);
                            O[3] = lista.get(4);
                            O[4] = lista.get(5);

                            model.addRow(O);
                            //System.out.println(cantidad);
                            TotalPagar();
                            LimpiarVenta();
                            codVentaTF.requestFocus();

                        } else{
                            JOptionPane.showMessageDialog(null, "Stock no disponible (Cantidad supera el Stock)");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ingrese cantidad");
                    }

                }

            }
        });
        eliminaVentaBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeRow(ventasT.getSelectedRow());
                TotalPagar();
                codVentaTF.requestFocus();
                LimpiarVenta();
            }
        });
        rucVentaTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()== KeyEvent.VK_ENTER){
                    if(!"".equals(rucVentaTF.getText())){
                        int cedula = Integer.parseInt(rucVentaTF.getText());
                        cliente.setNomCli(null);
                        nombreClienteTF.setText("");
                        BuscarCli(cedula);
                        if(cliente.getNomCli() != null){
                            nombreClienteTF.setText(""+cliente.getNomCli());
                        } else {
                            rucVentaTF.setText("");
                            JOptionPane.showMessageDialog(null, "El cliente no existe");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ingrese la cedula del cliente");
                    }
                }
            }
        });
        generarBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarVenta();
                RegistrarDet();
                ActualizarStock();
                PDF();
            }
        });
    }

    private void LimpiarVenta() {
        codVentaTF.setText("");
        prodTF.setText("");
        cantiTF.setText("");
        precioTF.setText("");
        stockTF.setText("");
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
    public void limpiarCamposProd(){
        codProdTF.setText(null);
        nomProdTF.setText(null);
        precioProdTF.setText(null);
        stockProdTF.setText(null);
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
                stockProdTF.setText(cantidad);
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

    //Método actualizar Stock
    public void ActualizarStock(String cod, int cantidad){
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE productos set STOCKPROD=? WHERE CODPROD=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            if (cantidad >= 0){
                pst.setString(1, String.valueOf(cantidad));
                pst.setString(2, cod);
                //ResultSet resultSet = pst.executeQuery(); //select
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Registro actualizado");
                stmt.close();
                conn.close();
            }
            else {
                JOptionPane.showMessageDialog(null, "No se ha realizado la modificacion");
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }
    }

    private void TotalPagar(){
        TotalPagar=0.00;
        int numFila= ventasT.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double calcu=Double.parseDouble(String.valueOf(ventasT.getModel().getValueAt(i,4)));
            TotalPagar= TotalPagar+ calcu;
        }
        totalL.setText(String.format("%.2f",TotalPagar));
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

    public Producto BuscarPro(String cod){
        Producto producto= new Producto();
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";
        String sql="SELECT * FROM productos WHERE CODPROD=?";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,cod);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                Producto.setCod(rs.getString("CODPROD"));
                Producto.setNombre(rs.getString("NOMPROD"));
                Producto.setPrecio(rs.getDouble("PRECIOPROD"));
                Producto.setStock(rs.getInt("STOCKPROD"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }

        return producto;
    }

    public cliente BuscarCli(int cedula){
        cliente cli = new cliente();
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";
        String sql="SELECT * FROM clientes WHERE CEDULACL=?";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,String.valueOf(cedula));
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                cliente.setNomCli(rs.getString("NOMCLI"));
                cliente.setCelular(rs.getString("CELCLI"));
                cliente.setMailCli(rs.getString("MAILCLI"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }

        return cli;
    }

    public void RegistrarVenta(){
        Venta vent = new Venta();
        String cliente= nombreClienteTF.getText();
        int codUser = Integer.parseInt(UserGeneral.getCodigoUser());
        double monto = Double.parseDouble(totalL.getText());

        vent.setCliente(cliente);
        vent.setUsuario(codUser);
        vent.setTotal(monto);
        RegistrarVenta(vent);
    }

    public int RegistrarVenta (Venta v){
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql="INSERT INTO cab__nvtas (CODUSER, NOMCLIENTE, TOTALNV) VALUES (?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,UserGeneral.getCodigoUser());
            pst.setString(2,Venta.getCliente());
            pst.setString(3,String.valueOf(Venta.getTotal()));
            pst.executeUpdate();
            //pst.executeQuery();
            JOptionPane.showMessageDialog(null, "Venta registrada con éxito");
            stmt.close();
            conn.close();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(e);
        }
        return result;
    }

    public void RegistrarDet(){
        for (int i = 0; i < ventasT.getRowCount(); i++) {
            int codP= Integer.parseInt(ventasT.getValueAt(i,0).toString());
            int cantP= Integer.parseInt(ventasT.getValueAt(i,2).toString());
            double totalCom= Double.parseDouble(ventasT.getValueAt(i,4).toString());
            int CodDet=IdVenta();
            det.setNomProd(codP);
            det.setCantidad(cantP);
            det.setPrecio(totalCom);
            det.setCodVenta(CodDet);
            RegistrarDet(det);
        }
    }
    public int IdVenta(){
        int id=0;
        String sql = "SELECT MAX(CODNV) FROM cab__nvtas";
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            if (rs.next()){
                id=rs.getInt(1);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(e);
        }
        return id;

    }
    public int RegistrarDet(Detalle DV){
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";
        String sql = "INSERT INTO det_nvtas (CODNV, CODPROD, CANTPROD,TOTALNV) VALUES (?,?,?,?)";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            //pst.setInt(1,Detalle.getCodDet());
            pst.setInt(1,Detalle.getCodVenta());
            pst.setInt(2,Detalle.getNomProd());
            pst.setInt(3,Detalle.getCantidad());
            pst.setDouble(4,Detalle.getPrecio());
            pst.executeUpdate();
            stmt.close();
            conn.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error de Detalle");
            System.out.println(e);
        }
        return result;
    }

    public void PDF(){
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            System.out.println(ruta);
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Documents/Reporte.pdf"));
            documento.open();

            Paragraph paragraphHello = new Paragraph();
            paragraphHello.add("Farmacia MCTJ" +
                    "\n" +
                    "Encabezado Nota de Venta" +
                    "\n" + "Gracias por su Compra \n\n\n\n");
            paragraphHello.setAlignment(Element.ALIGN_CENTER);
            documento.add(paragraphHello);

            PdfPTable tabla = new PdfPTable(5); //numeros de las columnas
            tabla.addCell("Código Nota de Venta");
            tabla.addCell("Código de Usuario");
            tabla.addCell("Nombre del Cliente");
            tabla.addCell("Total Nota de Venta");
            tabla.addCell("Fecha");

            PdfPTable tabla1 = new PdfPTable(3); //numeros de las columnas
            tabla1.addCell("Código Producto");
            tabla1.addCell("Cantidad");
            tabla1.addCell("Total Nota de Venta");

            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/farmacia?serverTimezone=UTC", "root", "");
                PreparedStatement pst = cn.prepareStatement("select * from cab__nvtas WHERE CODNV = (SELECT MAX(CODNV) FROM det_nvtas)");

                ResultSet rs = pst.executeQuery();

                if(rs.next()){

                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }

                PreparedStatement pst1 = cn.prepareStatement("select * from det_nvtas where CODNV = (SELECT MAX(CODNV) FROM cab__nvtas)");

                ResultSet rs1 = pst1.executeQuery();

                if(rs1.next()){

                    do {
                        tabla1.addCell(rs1.getString(3));
                        tabla1.addCell(rs1.getString(4));
                        tabla1.addCell(rs1.getString(5));
                    } while (rs1.next());

                    documento.add(tabla1);
                }
            } catch (DocumentException | SQLException e) {
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado.");
        } catch (DocumentException | HeadlessException | FileNotFoundException e) {
        }
    }

    public void ActualizarStock(){
        for (int i = 0; i < ventasT.getRowCount(); i++) {
            String cod= ventasT.getValueAt(i,0).toString();
            int cant=Integer.parseInt(ventasT.getValueAt(i,2).toString());
            BuscarPro(cod);
            int StockAct = pro.getStock()-cant;
            StockActualiz(StockAct,cod);
        }
    }
    public boolean StockActualiz(int cant, String cod){
        final String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC"; // Error Nombre DATABASE
        final String USERNAME="root";
        final String PASSWORD="";
        String sql = "UPDATE productos SET STOCKPROD=? WHERE CODPROD=?";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            //pst.setInt(1,Detalle.getCodDet());
            pst.setInt(1,cant);
            pst.setString(2,cod);
            pst.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(e);
            return false;
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
