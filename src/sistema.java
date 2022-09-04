import Clases.Usuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class sistema extends JFrame {
    private JButton salirButton;
    private JLabel codUserL;
    private JLabel nomUserL;
    private JLabel mailUserL;
    private JLabel celUserL;
    private JLabel tipoUserL;
    private JMenuBar menuBar;
    private JPanel principalPanel;
    private JButton salirDelSistemaButton;

    Usuarios PrincipalUser;

    public sistema() {
        frameInit();
        this.setContentPane(principalPanel);
        this.setTitle("Ventana principal del sistema ");
        this.setSize(880,420);
        //this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null); // centrar la pantalla

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.this.dispose();
                sistema.this.setVisible(false);
                login login1= new login();
                login1.setVisible(true);
            }
        });

        //Mostrar datos del Usuario que inicio sesión
        /*codUserL.setText(String.valueOf(PrincipalUser.getCodUser()));
        nomUserL.setText(PrincipalUser.getNombre());
        mailUserL.setText(PrincipalUser.getMail());
        celUserL.setText(PrincipalUser.getCelular());
        tipoUserL.setText(PrincipalUser.getTipoUser());*/
        salirDelSistemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.this.dispose();
                sistema.this.setVisible(false);
                sistema.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }

   /* private Usuarios obtenerDatos(String email, String password, String rol){
        String nombre, mail, celular, rolUser;
        int cod;
        Usuarios user = null;

        final String DB_URL="jdbc:mysql://localhost/farmacia?serverTimezon=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{

            Connection conn = DriverManager.getConnection(DB_URL,USERNAME, PASSWORD); //conector MYSQL
            Statement stnt = conn.createStatement();
            String sql="SELECT * FROM usuarios WHERE EMAILUSER=? AND CLAVEUSER=? AND CODROL=(SELECT CODROL FROM roles WHERE NOMROL=? )";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //System.out.println("conexion ok");
            ResultSet resultSet= preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new Usuarios();
                user.nombre = resultSet.getString("NOMUSER");
                user.mail=resultSet.getString("EMAILUSER");
                user.celular=resultSet.getString("CELLUSER");
                user.codUser=resultSet.getInt("CODUSER");
                user.clave=resultSet.getString("CLAVEUSER");
                user.tipoUser=resultSet.getString("CODROL");
            }
            stnt.close();
            conn.close();

        } catch (Exception e){
            System.out.println("error de ...");
            e.printStackTrace();
        }

        return user;
    }*/

    public static void main(String[] args) {
        sistema sist= new sistema();
    }
}
