/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DBEnsamblePieza {

    private double costoEnsamblePieza;
    ArrayList<ERROR> errores = new ArrayList<>();

    ArrayList<Ensamble_Pieza> ensamblesPiezas = new ArrayList<>();

    public void agregarEnsamblePieza(String mueble, String pieza, String cantidad) throws SQLException, MiMuebleriaException {
        costoEnsamblePieza = 0;
        if (comprobarMueble(mueble) && comprobarPieza(pieza, cantidad) && costoEnsamblePieza > 0) {
            Ensamble_Pieza ensamblePieza = new Ensamble_Pieza(mueble, pieza, cantidad, costoEnsamblePieza);
            ensamblesPiezas.add(ensamblePieza);
            PreparedStatement insert = (PreparedStatement) Conexion.conexion().prepareStatement("INSERT IGNORE INTO ensamble_pieza(mueble,tipo,cantidad,costo) VALUES(?,?,?,?)");
            insert.setString(1, ensamblePieza.getMueble());
            insert.setString(2, ensamblePieza.getPieza());
            insert.setString(3, String.valueOf(ensamblePieza.getCantidad()));
            insert.setString(4, String.valueOf(ensamblePieza.getCosto()));
            insert.execute();
        } else {
            errores.add(new ERROR("FORMATO: " + mueble + ", " + pieza + ", " + cantidad, "Datos incorrectos para  ensamble de  pieza"));
        }

    }

    public boolean comprobarMueble(String mueble) throws SQLException, MiMuebleriaException {
        boolean muebleExistente = false;
        PreparedStatement consultaMueble = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM mueble WHERE nombreMueble=?");
        consultaMueble.setString(1, mueble);
        ResultSet result = consultaMueble.executeQuery();
        muebleExistente = result.next();

        return muebleExistente;
    }

    public boolean comprobarPieza(String pieza, String numero) throws MiMuebleriaException, SQLException {
        int num = Integer.valueOf(numero);
        boolean piezaExistente = false;
        int count = 0;
        
            PreparedStatement consultaCantidadPieza = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT COUNT(*) FROM pieza WHERE tipo=?");
            consultaCantidadPieza.setString(1, pieza);
            ResultSet result = consultaCantidadPieza.executeQuery();
            result.next();
            count = result.getInt(1);
            if (count >= num) {
                if (costoPieza(pieza, num)) {
                    piezaExistente = true;
                } else {
                    piezaExistente = false;
                }
            } else {
                errores.add(new ERROR("FORMATO: " + pieza + ": " + num, "No existe el numero de piezas solicitadas"));
                piezaExistente = false;
            }
        
        return piezaExistente;
    }

    public boolean costoPieza(String pieza, int num) throws SQLException, MiMuebleriaException {
        boolean PiezasExistente = false;
        
            PreparedStatement consultaPieza = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT costo FROM pieza WHERE tipo=?");
            consultaPieza.setString(1, pieza);
            ResultSet result1 = consultaPieza.executeQuery();
            if (result1.next()) {
                costoEnsamblePieza = Double.valueOf(result1.getString(1)) * num;
                PiezasExistente = true;
            } else {
                errores.add(new ERROR("FORMATO: " + pieza, "no se pudo comprobar el costo de pieza"));
            }
        
        return PiezasExistente;
    }

}
