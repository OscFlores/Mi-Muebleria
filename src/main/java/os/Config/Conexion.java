/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Config;

import os.Clases.*;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Oscar
 */
public class Conexion {

    private static final String USER = "userMuebleria";
    private static final String PASSWORD = "MiMuebleria123.";
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/Mi_Muebleria"; 
    public static Connection conexion()throws MiMuebleriaException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            out.println(e.getMessage()+"");
        } 
        return connection;
    }
}
