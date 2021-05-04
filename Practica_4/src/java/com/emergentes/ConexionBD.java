package com.emergentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    static String url = "jdbc:mysql://localhost:3306/bd_proyectos";
    static String usuario = "root";
    static String password = "";

    protected Connection conn  = null;

    public ConexionBD() {
       
        try {
            //Especificacion del driver
            Class.forName("com.mysql.jdbc.Driver");
            
            conn=DriverManager.getConnection(url, usuario,password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Falta driver "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error en sql "+ex.getMessage());
        }
        
    }
    
    public Connection conectar(){
        return conn;
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar "+ex.getMessage());
        }
    }
}
