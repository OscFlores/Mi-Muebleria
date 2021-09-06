/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Clases.*;
import os.Config.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar
 */
public class DBMueble {

    private static ArrayList<ERROR> errores = new ArrayList<>();

    public static void agregarMueble(Mueble mueble) throws SQLException, MiMuebleriaException {

        PreparedStatement insert = (PreparedStatement) (Conexion.conexion()).prepareStatement("INSERT IGNORE INTO mueble(nombreMueble,precio) VALUES(?,?)");
        insert.setString(1, mueble.getTipoMueble());
        insert.setString(2, String.valueOf(mueble.getPrecioVenta()));
        insert.execute();

    }

}
