import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventas extends JFrame{
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
    private JButton eliminarBT;
    private JButton nuevoBT;
    private JTextField codProdTF;
    private JTextField nomProdTF;
    private JTextField stockProdTF;
    private JTextField precioProdTF;
    private JTable productosT;
    private JButton buscarProdBT;
    private JButton excelBT;
    private JTable clientesT;
    private JTable ventasGuardadasT;
    private JButton generarNotaDeVentaBT;
    private JLabel totalL;
    private JButton actualizarFarBT;
    private JTextField rucFarTF;
    private JTextField nomFarTF;
    private JTextField direcFarTF;
    private JTextField telfFarTF;

    public Ventas() {
        frameInit();
        this.setContentPane(ventasPanel);
        this.setTitle("Ventas Farmacia MCTJ ");
        this.setSize(990,390);
        //this.setResizable(false); //Evita el redimendisionamiento de la pantalla, al ser usada
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null); // centrar la pantalla
        volverBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventas.this.dispose();
                Ventas.this.setVisible(false);
                sistema sistema1= new sistema();
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
    }

    public static void main(String[] args) {
        Ventas ventitas = new Ventas();
    }
}
