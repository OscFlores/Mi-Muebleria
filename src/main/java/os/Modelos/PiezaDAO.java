/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import os.Clases.Pieza;
import os.Config.*;
/**
 *
 * @author Oscar
 */
public class PiezaDAO implements Agregados<Pieza>{
    
    private static final String SQL_INSERT = "INSERT INTO Pieza(Id_TipoPieza,Costo,Usado) VALUES(?,?,0)";
    private static final String SQL_SELECT_BY_TIPO_PIEZA = "SELECT * FROM Pieza WHERE Id_Tipopieza=? AND Usado=0";
    private static final String SQL_USAR_PIEZA = "UPDATE Pieza SET Usado = 1 WHERE Id_pieza = ?";
    private static final String SQL_SELECT = "SELECT p.id_pieza, ip.Nombre, p.Costo FROM Pieza p JOIN InfoPieza ip ON (p.id_TipoPieza = Ip.Id_TipoPieza) WHERE p.Usado = 0";
    private static final String SQL_DELETE = "DELETE FROM Pieza WHERE Id_Pieza =?";
    private static final String SQL_SELECT_BY_ID_PIEZA = "SELECT p.Id_TipoPieza, p.Costo, p.Usado, ip.Nombre FROM Pieza p JOIN InfoPieza ip ON (p.id_TipoPieza = ip.Id_TipoPieza)  WHERE Id_Pieza=? ";
    private static final String SQL_DELETE_BY_ID_TIPO_PIEZA = "DELETE FROM Pieza WHERE Id_TipoPieza=? && Usado=0";
    private static final String SQL_UPDATE_PRECIO = "UPDATE Pieza SET  Costo=? WHERE Id_Pieza=?";
    private static final String SQL_UPDATE_REHUSAR = "UPDATE Pieza SET Costo=Costo*0.6666 , Usado=0 WHERE Id_Pieza=?";

    
    public void rehusarPieza(int idPieza) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_REHUSAR);
            stmt.setInt(1, idPieza);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    @Override
    public Pieza Encontrar(Pieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_PIEZA);
            stmt.setInt(1, modelo.getIdPieza());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idTipoPieza = rs.getInt("Id_TipoPieza");
                double Costo = rs.getDouble("Costo");
                int Usado = rs.getInt("Usado");
                String TipoPieza = rs.getString("ip.Nombre");
                modelo.setIdTipoPieza(idTipoPieza);
                modelo.setCosto(Costo);
                modelo.setUsado(Usado);
                modelo.setTipo(TipoPieza);
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
    public int Insertar(Pieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getIdTipoPieza());
            stmt.setDouble(2, modelo.getCosto());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public void usarPieza(int idPieza) throws Excepciones {
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

    public Pieza encotrarNoUsadosByIdTipoPieza(Pieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_TIPO_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());
            rs = stmt.executeQuery();
            boolean primero = false;
            while (!primero) {
                rs.next();
                int idPieza = rs.getInt("Id_Pieza");
                double Costo = rs.getDouble("Costo");
                modelo.setIdPieza(idPieza);
                modelo.setCosto(Costo);
                primero = true;
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
    public int Eliminar(Pieza modelo) throws Excepciones {
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setDouble(1, modelo.getIdPieza());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public void eleminarSegunTipoPieza(int idTipoPieza) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_ID_TIPO_PIEZA);
            stmt.setDouble(1, idTipoPieza);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    @Override
    public int Actualizar(Pieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, modelo.getCosto());
            stmt.setInt(2, modelo.getIdPieza());

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
    public List<Pieza> Listar(Pieza t) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Pieza> Listar() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pieza> piezas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPieza = rs.getInt("p.Id_Pieza");
                String nombreTipoPieza = rs.getString("ip.Nombre");
                double Costo = rs.getDouble("p.Costo");
                Pieza pieza = new Pieza(idPieza, nombreTipoPieza, Costo);
                piezas.add(pieza);
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

}
