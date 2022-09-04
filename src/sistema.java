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

    public sistema(int cod, String rol, String nombre, String email, String cell ) {
        frameInit();
        ModificarCampos(cod,rol, nombre, email, cell);
        this.setContentPane(principalPanel);
        this.setTitle("Ventana Principal Farmacia MCTJ");
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
                Ventas ventas= new Ventas();
                ventas.setVisible(true);
            }
        });


    }

    public void ModificarCampos (int cod, String rol, String nombre, String email, String cell){
        codUserL.setText(""+cod);
        nomUserL.setText(nombre);
        mailUserL.setText(email);
        celUserL.setText(cell);
        tipoUserL.setText(rol);
    }

    public static void main(String[] args) {
        sistema sist= new sistema(0, null, null,null,null);
    }


}
