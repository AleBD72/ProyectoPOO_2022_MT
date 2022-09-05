import Clases.UserGeneral;
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
    private JMenuItem VentasJMI;
    private JMenuItem BodegaJMI;
    private JMenuItem UsuariosJMI;
    private JMenu VtasPanel;
    private JMenu BodegaPanel;
    private JMenu AdminPanel;

    public sistema() {
        frameInit();
        ModificarCampos();
        this.setContentPane(principalPanel);
        this.setTitle("Ventana Principal Farmacia MCTJ");
        this.setSize(880,420);
        //this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //PRIVILEGIOS
        if (UserGeneral.rolUser.equals("CAJ")){
            System.out.println("en condicion CAJ");
            BodegaPanel.setVisible(false);
            AdminPanel.setVisible(false);
        }
        else if (UserGeneral.rolUser.equals("JBD")){
            System.out.println("en condicion BOD");
            VtasPanel.setVisible(false);
            AdminPanel.setVisible(false);
        }
        else {
            System.out.println(UserGeneral.rolUser.length());
            System.out.println(UserGeneral.rolUser);
        }
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

        salirDelSistemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.this.dispose();
                sistema.this.setVisible(false);
                sistema.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });

        VentasJMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.this.dispose();
                sistema.this.setVisible(false);
                Ventas ventas= new Ventas();
                ventas.setVisible(true);
            }
        });
        BodegaJMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.this.dispose();
                sistema.this.setVisible(false);
                Ventas ventas= new Ventas();
                ventas.setVisible(true);
            }
        });
        UsuariosJMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.this.dispose();
                sistema.this.setVisible(false);
                Administracion user= new Administracion();
                user.setVisible(true);
            }
        });


    }

    public void ModificarCampos (){
        codUserL.setText(UserGeneral.getCodigoUser());
        nomUserL.setText(UserGeneral.getNombreUser());
        mailUserL.setText(UserGeneral.getEmailUser());
        celUserL.setText(UserGeneral.getCellUser());
        tipoUserL.setText(UserGeneral.getRolUser());
    }

    public static void main(String[] args) {
        sistema sist= new sistema();
    }


}
