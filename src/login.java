import Clases.Usuarios;
import Clases.conexionCombo;
import Clases.Roles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class login extends JFrame{
    private JButton salirBT;
    private JComboBox usuarioTipoCB;
    private JTextField mailTF;
    private JButton ingresarLBT;
    private JButton cancelarLBT;
    private JPanel loginPanel;
    private JPasswordField passwordPF;

    public static final String BDD="farmacia";
    public static final String username="root";
    public static final String password="";
    conexionCombo conect;
    ArrayList mListaUsuarios;
    public Usuarios user;

    public login(){
        frameInit();
        conect = new conexionCombo(BDD,username,password);
        mListaUsuarios= new ArrayList<>();
        comboBox();


        this.setContentPane(loginPanel);
        this.setTitle("Iniciar Sesión");
        this.setSize(650,420);
        this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null); // centrar la pantalla

        salirBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.this.dispose();
                login.this.setVisible(false);
                login.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        cancelarLBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.this.dispose();
                login.this.setVisible(false);
                login.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        ingresarLBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email= mailTF.getText();
                String password = String.valueOf(passwordPF.getPassword());
                String rol = String.valueOf(usuarioTipoCB.getSelectedItem());

                user = getAuthenticationUser(email, password, rol);

                if(user != null){
                    sistema ventana2 = new sistema();
                    setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(
                            login.this,"Intente nuevamente \nEmail, password o rol incorrectos (Parámetros mal ingresados)",
                            "Intente nuevamente",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    public void comboBox(){
        if(conect.conectar()){
            usuarioTipoCB.removeAllItems();
            mListaUsuarios=conect.getListaUsuarios();
            Iterator iterador = mListaUsuarios.iterator();
            while(iterador.hasNext()){
                Roles mRoles = (Roles) iterador.next();
                usuarioTipoCB.addItem(mRoles);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    //Elemento de autenticación
    private Usuarios getAuthenticationUser(String email, String password, String rol){
        Usuarios user = null;

        final String DB_URL="jdbc:mysql://localhost/farmacia?serverTimezon=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{

            Connection conn = DriverManager.getConnection(DB_URL,USERNAME, PASSWORD); //conector MYSQL
            Statement stnt = conn.createStatement();
            String sql="SELECT * FROM usuarios WHERE EMAILUSER=? AND CLAVEUSER=? AND CODROL=(SELECT CODROL FROM roles WHERE NOMROL=? )";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, rol);
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
    }


    public static void main(String[] args) {
        login farmacialog = new login();
    }

}
