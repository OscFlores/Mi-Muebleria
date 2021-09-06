/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.*;
import os.Clases.InfoPieza;
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
public class InfoPiezaDAO implements Agregados<InfoPieza> {
    private static final String SQL_SELECT_BY_NOMBRE = "SELECT * FROM InfoPieza WHERE Nombre=?";
    private static final String SQL_INSERT = "INSERT INTO InfoPieza(Nombre, Cantidad, Eliminado) VALUES(?, 0,0)";
    private static final String SQL_UPDATE = "UPDATE InfoPieza SET Nombre = ?, Cantidad = ? WHERE Id_InfoPieza = ?";
    private static final String SQL_AGREGAR_PIEZA = "UPDATE InfoPieza SET Cantidad=Cantidad+1 WHERE Id_InfoPieza=?";
    private static final String SQL_QUITAR_PIEZA = "UPDATE InfoPieza SET Cantidad=Cantidad-1 WHERE Id_InfoPieza=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM InfoPieza WHERE Id_InfoPieza = ?";
    private static final String SQL_USAR_PIEZA = "UPDATE InfoPieza SET Cantidad = Cantidad - 1 WHERE Id_InfoPieza = ?";
    private static final String SQL_PIEZAS_POR_AGOTAR = "SELECT * FROM InfoPieza WHERE Cantidad<20 AND Eliminado = 0";
    private static final String SQL_SELECT = "SELECT * FROM InfoPieza WHERE Eliminado=0";
    private static final String SQL_UPDATE_NOMBRE = "UPDATE InfoPieza SET Nombre = ? WHERE Id_InfoPieza = ?";
    private static final String SQL_DESHABILITAR = "UPDATE InfoPieza SET Eliminado=1 WHERE Id_InfoPieza=?";
    private static final String SQL_HABILITAR = "UPDATE InfoPieza SET Eliminado=0 WHERE Nombre=?";

    public List<InfoPieza> listarPiezasPorAgotar() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<InfoPieza> Piezas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_PIEZAS_POR_AGOTAR);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int IdInfoPieza = rs.getInt("Id_InfoPieza");
                String Nombre = rs.getString("Nombre");
                int Cantidad = rs.getInt("Cantidad");
                InfoPieza tipoPieza = new InfoPieza(IdInfoPieza, Nombre, Cantidad);
                Piezas.add(tipoPieza);
            }
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return Piezas;
    }
    public void usarPieza(int idPieza) throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_USAR_PIEZA);
            stmt.setInt(1, idPieza);
            int numMod = stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    public InfoPieza EncontrarporId(InfoPieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, modelo.getIdTipoPieza());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nombre = rs.getString("Nombre");
                int Cantidad = rs.getInt("Cantidad");
                int IdInfoPieza = rs.getInt("Id_InfoPieza");
                modelo.setNombre(Nombre);
                modelo.setCantidad(Cantidad);
                modelo.setIdTipoPieza(IdInfoPieza);
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
    public boolean existe(String nombrePieza) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, nombrePieza.toUpperCase());
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
    public InfoPieza Encontrar(InfoPieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Cantidad = rs.getInt("Cantidad");
                int IdInfoPieza = rs.getInt("Id_InfoPieza");
                modelo.setCantidad(Cantidad);
                modelo.setIdTipoPieza(IdInfoPieza);
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
    @Override
    public int Insertar(InfoPieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }
    public int Deshabilitar(InfoPieza modelo) throws Excepciones{
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DESHABILITAR);
            stmt.setInt(1, modelo.getIdTipoPieza());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public int Habilitar(String nombre) throws Excepciones {
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_HABILITAR);
            stmt.setString(1, nombre);
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
    public int Actualizar(InfoPieza modelo) throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, modelo.getNombre());
            stmt.setDouble(2, modelo.getCantidad());
            stmt.setInt(3, modelo.getIdTipoPieza());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public int ActualizarNombre(InfoPieza modelo) throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_NOMBRE);
            stmt.setString(1, modelo.getNombre());
            stmt.setInt(2, modelo.getIdTipoPieza());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }
    public void agregarPieza(InfoPieza modelo) throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_AGREGAR_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());
            stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public void quitarPieza(InfoPieza modelo) throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUITAR_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    public List<InfoPieza> Listar(boolean descendente) throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<InfoPieza> piezas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            String orden = descendente ? "ORDER BY Cantidad DESC" : "ORDER BY Cantidad ASC";
            stmt = conn.prepareStatement(SQL_SELECT +" "+ orden);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idInfoPieza = rs.getInt("id_InfoPieza");
                String Nombre = rs.getString("Nombre");
                int Cantidad = rs.getInt("Cantidad");
                InfoPieza tipoPieza = new InfoPieza(idInfoPieza, Nombre, Cantidad);
                piezas.add(tipoPieza);
            }
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return piezas;
    }
    @Override
    public List<InfoPieza> Listar() throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<InfoPieza> piezas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_TipoPieza");
                String Nombre = rs.getString("Nombre");
                int Cantidad = rs.getInt("Cantidad");
                InfoPieza tipoPieza = new InfoPieza(idTipoPieza, Nombre, Cantidad);
                piezas.add(tipoPieza);
            }
        } catch (SQLException ex) {
             throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return piezas;
    }
    @Override
    public List<InfoPieza> Listar(InfoPieza modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int Eliminar(InfoPieza modelo) throws Excepciones{
        return 0;
    }
}
