/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.*;
import os.Clases.EnsambleMueble;
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
public class EnsambleMuebleDAO implements Agregados<EnsambleMueble>{
    private static final String SQL_INSERT = "INSERT INTO Ensamble_Mueble(Fecha, Nombre_Usuario,Nombre_Mueble,Costo,Cant_Devolucion,Cant_Venta) VALUES(?,?,?,?,0,0)";
    private static final String SQL_SELECCIONAR_ULTIMO = "SELECT id_ensamble FROM Ensamble ORDER BY id_ensamble DESC LIMIT 1";
    private static final String SQL_SELECT = "SELECT * FROM Ensamble";
    private static final String SQL_SELECT_DISPONIBLES = "SELECT e.id_ensamble, e.Nombre_Mueble, m.Precio FROM Ensamble e JOIN Mueble m ON(e.Nombre_Mueble=m.Nombre) WHERE Cant_Venta=0";
    private static final String SQL_EXISTE = "SELECT * FROM Ensamble WHERE id_ensamble =? AND Cant_Venta=0";
    private static final String SQL_VENDER = "UPDATE Ensamble SET Cant_Venta=1 WHERE id_ensamble=?";
    private static final String SQL_DEVOLVER = "UPDATE Ensamble SET Cant_Devolucion=1 WHERE id_ensamble=?";
    private static final String SQL_OBTENER_PRECIO = "SELECT m.Precio FROM Ensamble e JOIN Mueble m ON(e.Nombre_Mueble=m.Nombre)  WHERE id_ensamble=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Ensamble WHERE id_ensamble=?";

    public void devolver(int idEnsamble) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DEVOLVER);
            stmt.setInt(1, idEnsamble);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    @Override
    public EnsambleMueble Encontrar(EnsambleMueble modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EnsambleMueble ensamblarMueble = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, modelo.getIdEnsamble());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaEnsamble = rs.getString("Fecha");
                String usuarioEnsamblador = rs.getString("Nombre_Usuario");
                String tipoMueble = rs.getString("Nombre_Mueble");
                double costo = rs.getDouble("Costo");
                int vendido = rs.getInt("Cant_Venta");
                int devuelto = rs.getInt("Cant_Devolucion");
                ensamblarMueble = new EnsambleMueble(modelo.getIdEnsamble(), fechaEnsamble, usuarioEnsamblador,tipoMueble, costo, vendido, devuelto);
            }

        } catch (SQLException ex) { 
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return ensamblarMueble;
    }

    public double obtenerPrecio(int idEnsamble) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double precio = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_PRECIO);
            stmt.setInt(1, idEnsamble);
            rs = stmt.executeQuery();
            while (rs.next()) {
                precio = rs.getDouble("m.Precio");
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return precio;
    }

    public void venderMueble(int idEnsamble) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_VENDER);
            stmt.setInt(1, idEnsamble);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public boolean estaEnVenta(int idEnsamble) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setInt(1, idEnsamble);
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

    public List<EnsambleMueble> listarDisponibles() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EnsambleMueble> ensamblesMueble = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DISPONIBLES);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEnsamble = rs.getInt("e.id_ensamble");
                String tipoMueble = rs.getString("e.Nombre_Mueble");
                double precioVenta = rs.getDouble("m.Precio");
                EnsambleMueble ensambleMueble = new EnsambleMueble(idEnsamble, tipoMueble, precioVenta);
                ensamblesMueble.add(ensambleMueble);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return ensamblesMueble;
    }

    @Override
    public List<EnsambleMueble> Listar(EnsambleMueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int obtenerIdUltimoEnviado() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idEnsamble = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECCIONAR_ULTIMO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                idEnsamble = rs.getInt("id_ensamble");
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return idEnsamble;
    }

    @Override
    public int Insertar(EnsambleMueble modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getFecha());
            stmt.setString(2, modelo.getNombre_Usuario());
            stmt.setString(3, modelo.getNombre());
            stmt.setDouble(4, modelo.getCosto());

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
    public int Eliminar(EnsambleMueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Actualizar(EnsambleMueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<EnsambleMueble> Listar(boolean descendente) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EnsambleMueble> ensamblesMueble = new ArrayList<>();
        try {
            String orden = descendente ? "ORDER BY fecha_ensamble DESC" : "ORDER BY fecha_ensamble ASC";
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT + " " + orden);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEnsamble = rs.getInt("id_ensamble");
                String fechaEnsamble = Formateo.formatearFechaEnAEs(rs.getString("fecha_ensamble"));
                String usuarioEnsamblador = rs.getString("usuario_ensamblador");
                String tipoMueble = rs.getString("tipo_mueble");
                double costo = rs.getDouble("costo");
                int vendido = rs.getInt("vendido");
                EnsambleMueble ensambleMueble = new EnsambleMueble(idEnsamble, fechaEnsamble, usuarioEnsamblador, tipoMueble, costo, vendido);
                ensamblesMueble.add(ensambleMueble);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return ensamblesMueble;
    }

    @Override
    public List<EnsambleMueble> Listar() throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
