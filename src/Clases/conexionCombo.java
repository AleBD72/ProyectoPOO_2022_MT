package Clases;

import java.sql.*;
import java.util.ArrayList;

public class conexionCombo {
    private static Connection Conection;
    private static Statement Sentencia;
    private static ResultSet Resultado;
    private final String bd;
    private final String user;
    private final String password;

    public conexionCombo(String bd, String user, String password) {
        Conection = null;
        Sentencia = null;
        Resultado = null;
        this.bd = bd;
        this.user = user;
        this.password = password;
    }

    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Conection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + bd, user, password);
            return Conection != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void desconectar() {
        try {
            this.Conection.close();
        } catch (Exception e) {
        }
    }
    public ArrayList getListaUsuarios() {
        ArrayList tipoUsuario = new ArrayList();
        Roles mRoles = null;
        Statement consulta;
        ResultSet resultado;
        try {
            consulta = Conection.createStatement();
            resultado = consulta.executeQuery("select * from roles");

            while (resultado.next()) {
                mRoles = new Roles();
                mRoles.setCodRol(resultado.getString("CODROL"));
                mRoles.setRol(resultado.getString("NOMROL"));
                tipoUsuario.add(mRoles);
            }
        } catch (SQLException e) {
        }
        return tipoUsuario;
    }


}
