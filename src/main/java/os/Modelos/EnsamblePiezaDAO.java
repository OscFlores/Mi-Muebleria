/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.*;
import os.Clases.EnsamblePieza;
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
public class EnsamblePiezaDAO implements Agregados<EnsamblePieza> {
    private static final String SQL_INSERT = "INSERT INTO Ensamble_Pieza(Nombre,id_TipoPieza,Cantidad) VALUES(?,?,?)";
    private static final String SQL_SELEC_BY_TIPO_MUEBLE = "SELECT * FROM Ensamble_Pieza WHERE Nombre = ?";
    private static final String SQL_LISTAR_SEGUN_TIPO_MUEBLE = "SELECT ip.Nombre, ep.Cantidad FROM Ensamble_Pieza ep JOIN Nombre ip ON(ep.id_TipoPieza=Ip.id_TipoPieza) WHERE ep.Nombre=?";
    private static final String SQL_BUSCAR_ENSAMBLE = "SELECT ep.Nombre, ep.Cantidad, ep.Nombre FROM Ensamble_Pieza ep JOIN Nombre ip ON(ep.Nombre=ip.id_tipo_pieza) WHERE ep.Nombre=? AND ip.Nombre=?";
    private static final String SQL_SOBREESCRIBIR_ENSAMBLE = "UPDATE Ensamble_Pieza ep JOIN Nombre ip SET ep.Cantidad=? WHERE ep.Nombre=? AND ip.Nombre=?";

    public int sobrescribirCantidadRequerimiento(int nuevaCantidad, String mueble, String pieza) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SOBREESCRIBIR_ENSAMBLE);
            stmt.setInt(1, nuevaCantidad);
            stmt.setString(2, mueble);
            stmt.setString(3, pieza);
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public boolean existe(String mueble, String pieza) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BUSCAR_ENSAMBLE);
            stmt.setString(1, mueble);
            stmt.setString(2, pieza);
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
    public EnsamblePieza Encontrar(EnsamblePieza t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EnsamblePieza> Listar(EnsamblePieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EnsamblePieza ensamblePieza = null;
        List<EnsamblePieza> requerimientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELEC_BY_TIPO_MUEBLE);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int numRequesito = rs.getInt("num_requisito");
                String tipoMueble = rs.getString("Nombre");
                int idTipoPieza = rs.getInt("id_TipoPieza");
                int cantidadNecesaria = rs.getInt("Cantidad");
                ensamblePieza = new EnsamblePieza(numRequesito, tipoMueble, idTipoPieza, cantidadNecesaria);
                requerimientos.add(ensamblePieza);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return requerimientos;
    }

    public List<EnsamblePieza> listarSegunTipoMueble(String tipoMueble) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EnsamblePieza> requerimientos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR_SEGUN_TIPO_MUEBLE);
            stmt.setString(1, tipoMueble.toUpperCase());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombrePieza = rs.getString("ip.Nombre");
                int cantidadNecesaria = rs.getInt("ep.Cantidad");
                EnsamblePieza ensamblePieza = new EnsamblePieza(nombrePieza, cantidadNecesaria);
                requerimientos.add(ensamblePieza);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return requerimientos;
    }

    @Override
    public int Insertar(EnsamblePieza modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            stmt.setDouble(2, modelo.getIdTipoPieza());
            stmt.setInt(3, modelo.getCantidad());

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
    public int Eliminar(EnsamblePieza modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Actualizar(EnsamblePieza modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EnsamblePieza> Listar() throws Excepciones{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
