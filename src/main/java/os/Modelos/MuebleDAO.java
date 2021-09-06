/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.Conexion;
import os.Clases.Mueble;
import os.Config.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class MuebleDAO implements Agregados<Mueble> {
    private static final String SQL_INSERT = "INSERT INTO mueble(Nombre, Precio) VALUES(?,?)";
    private static final String SQL_SELECT_BY_NOMBRE = "SELECT * FROM Mueble WHERE Nombre=?";
    private static final String SQL_SELECT = "SELECT * FROM Mueble";
    private static final String SQL_UPDATE_PRECIO = "UPDATE Mueble SET Precio=? WHERE Nombre=?";
    

    @Override
    public Mueble Encontrar(Mueble modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            rs = stmt.executeQuery();

            while (rs.next()) {
                double precio = rs.getDouble("Precio");
                modelo.setPrecio(precio);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return modelo;
    }

    public boolean Existe(String nombre) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, nombre.toUpperCase());
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return existe;
    }

    @Override
    public List<Mueble> Listar(Mueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Insertar(Mueble modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            stmt.setDouble(2, modelo.getPrecio());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;

    }

    @Override
    public int Eliminar(Mueble modelo) {
        return 0;
    }

    @Override
    public int Actualizar(Mueble modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, modelo.getPrecio());
            stmt.setString(2, modelo.getNombre());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    @Override
    public List<Mueble> Listar() throws Excepciones{
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mueble> muebles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("tipo_mueble");
                double precio = rs.getDouble("precio");
                Mueble mueble = new Mueble(nombre, precio);
                muebles.add(mueble);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return muebles;
    }
}
