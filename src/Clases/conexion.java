package Clases;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    Connection con;
    public Connection getConnection(){
        try{
            String DB_URL= "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
            String USERNAME="root";
            String PASSWORD="";
            con= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            return con;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("SQL incorrecto");
        }
        return null;
    }
}
