package os.Modelos;

import os.Clases.*;
import os.Config.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class DBEnsambleMueble {

    private double costoEnsambleMueble;
    private  ArrayList<ERROR> errores=new ArrayList<>();
    public void agregarEnsambleMueble(String mueble, String usuario, String fecha) throws SQLException, MiMuebleriaException {
        costoEnsambleMueble = 0;
        if (verificarUsuario(usuario) && verificarMueble(mueble)) {
            EnsambleMueble ensambleMueble = new EnsambleMueble(mueble, usuario, fecha,costoEnsambleMueble);
            try {
                PreparedStatement insert = (PreparedStatement) Conexion.conexion().prepareStatement("INSERT IGNORE INTO ensamble_mueble(mueble,usuarioEnsamble,fecha,costo) VALUES(?,?,?,?)");
                insert.setString(1, ensambleMueble.getMueble());
                insert.setString(2, ensambleMueble.getUsuario());
                insert.setDate(3,(java.sql.Date.valueOf(ensambleMueble.getFecha())));
                insert.setString(4,String.valueOf(ensambleMueble.getCosto()));
                insert.execute();
            } catch (MiMuebleriaException | SQLException e) {
                errores.add(new ERROR("FORMATO: "+mueble+","+usuario+", "+fecha,"Error al agregar ensamble a DB"));
            }
        } else {
            errores.add(new ERROR("FORMATO: "+mueble+","+usuario+", "+fecha,"Datos Incorrectos de ensamble pieza"));
        }

    }

    public boolean verificarUsuario(String usuario) throws SQLException {
        boolean usuarioExistente = false;
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM usuario WHERE usuario=?");
            consulta.setString(1, usuario);
            ResultSet result = consulta.executeQuery();
            usuarioExistente = result.next();
        } catch (MiMuebleriaException ex) {
            errores.add(new ERROR("FORMATO:"+usuario,"Usuario para  ensamble inexistente"));
        }
        return usuarioExistente;
    }

    public boolean verificarMueble(String mueble) throws MiMuebleriaException {
        boolean muebleExistente = false;
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM ensamble_pieza WHERE mueble=?");
            consulta.setString(1, mueble);
            ResultSet result = consulta.executeQuery();
            while (result.next()) {
                muebleExistente=true;
                costoEnsambleMueble += Double.valueOf(result.getString(4));
            }
        } catch (SQLException e) {
            errores.add(new ERROR("FORMATO: "+mueble,"Mueble para  ensamble inexistente"));
        } catch (NumberFormatException e) {
           errores.add(new ERROR("FORMATO: "+mueble,"Precio de ensamble no  convertido adecuadamente"));
        }
        return muebleExistente;
    }

}
