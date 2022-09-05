import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                CrearUser();
                CargarTablaUsuariosP1();
                CargarTablaUsuariosP2();
            }
        });
        editarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarUser();
                CargarTablaUsuariosP1();
                CargarTablaUsuariosP2();
            }
        });
        eliminarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarUser();
                CargarTablaUsuariosP1();
                CargarTablaUsuariosP2();
            }
        });
        buscarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarUser();
            }
        });
        limpiarUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
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

    public void CrearUser(){
        if (isEmail(mailUserTF.getText())){
            String rol, nombre, email, celular, clave;
            rol = rolUserTF.getText();
            nombre = nomUserTF.getText();
            email = mailUserTF.getText();
            celular = celUserTF.getText();
            clave = passUserTF.getText();

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
                JOptionPane.showMessageDialog(null, "Usuario no agregado");
            }

        }else {
            JOptionPane.showMessageDialog(null,"NO ES VALIDO EL EMAIL");
        }
    }

    public void Limpiar(){
        codUserTF.setText("");
        rolUserTF.setText("");
        nomUserTF.setText("");
        mailUserTF.setText("");
        celUserTF.setText("");
        passUserTF.setText("");
    }

    public void BuscarUser(){
        String id="0";
        id=codUserTF.getText();

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
                rolUserTF.setText(rol);
                nomUserTF.setText(nombre);
                celUserTF.setText(cell);
                mailUserTF.setText(email);
                passUserTF.setText(clave);
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

    public void ActualizarUser(){
        String id, rol, nombre, celular, email, clave;

        id=codUserTF.getText();
        rol=rolUserTF.getText();
        nombre=nomUserTF.getText();
        celular= celUserTF.getText();
        email= mailUserTF.getText();


        clave= passUserTF.getText();

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
            if (isEmail(mailUserTF.getText())){
                pst.setString(3, email);
            }else {
                JOptionPane.showMessageDialog(null, "EMAIL NO VALIDO");
            }

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

    public void BorrarUser(){
        String borrarId=codUserTF.getText();

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

    public boolean isEmail (String correo){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if(mat.find()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Administracion adm= new Administracion();
    }
}
