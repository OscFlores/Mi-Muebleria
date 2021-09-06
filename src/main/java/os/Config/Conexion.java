/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Oscar
 */
public class Conexion {
     private static final String JDBC_URL = "jdbc:mysql://localhost:8080/mimuebleria";
    private static final String JDBC_USER = "TomcatAdmin";
    private static final String JDBC_PASSWORD = "secpa55wd";

    private static BasicDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {

            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);
        }

        return dataSource;
    }

    public static Connection getConnection() throws Excepciones {
        try {
            return getDataSource().getConnection();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal con la base de datos, vuelve a intentarlo");
        }
    }

    public static void close(ResultSet rs) throws Excepciones {
        try {
            rs.close();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al cerrar un recurso");
        }
    }

    public static void close(PreparedStatement stmt) throws Excepciones {
        try {
            stmt.close();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al cerrar un recurso");
        }
    }

    public static void close(Connection conn) throws Excepciones {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al cerrar la conexion");
        }
    }

}
