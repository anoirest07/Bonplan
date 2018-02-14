/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Connexion {
    private String url = "jdbc:mysql://127.0.0.1/bonplan";
    private String login = "root";
    private  String pwd ="";

     Connection conn;
    
    private static Connexion instance = null;
    
    private Connexion()
    {
        try{
            conn = DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion établie");}
        catch (SQLException ex)
                {System.out.println("Connexion non établie");
        }
        }
    
    public static Connexion getInstance()
    {
        if (instance==null)
        {instance = new Connexion();}
        return instance;
    }
     public Connection getCon() {
        return conn;
     }
}
