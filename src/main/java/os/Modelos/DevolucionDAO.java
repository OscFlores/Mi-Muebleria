/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.*;
import os.Clases.Devolucion;
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
public class DevolucionDAO implements Agregados<Devolucion>{
        private static final String SQL_INSERT = "INSERT INTO devolucion(Id_Mueble_Devuelto,Num_Factura,Fecha,Perdida) VALUES(?,?,?,?)";
    private static final String SQL_OBTENER_ALL_DEVOLUCIONES = "SELECT dev.Num_Factura, c.Nombre, f.Nit_Cliente, dev.Fecha, dev.Id_Mueble_Devuelto, e.Nombre FROM Devolucion dev JOIN Factura f ON(dev.num_factura=f.num_factura) JOIN Cliente c ON(f.Nit_Cliente=c.Nit) JOIN Ensamble_Mueble e ON(dev.Id_Mueble_Devuelto=e.id_ensamble)";
    private static final String SQL_OBTENER_DEVOLUCIONES_BY_NIT_AND_FECHA = "SELECT dev.Num_Factura, c.Nombre, f.Nit_Cliente, dev.Fecha, dev.Id_Mueble_Devuelto, e.Nombre FROM Devolucion dev JOIN Factura f ON(dev.Num_Factura=f.No_Factura) JOIN Cliente c ON(f.Nit_Cliente=c.Nit) JOIN Ensamble_Mueble e ON(dev.Id_Nueble_Devuelto=e.id_ensamble) WHERE dev.fecha BETWEEN ? AND ? AND f.Nit_Cliente=? ORDER BY dev.Fecha DESC";

    public List<Devolucion> obtenerDevolucionesByNitAndFecha(String nitC, String fechaInicial, String fechaFinal) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Devolucion> devoluciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_DEVOLUCIONES_BY_NIT_AND_FECHA);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, nitC);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("dev.Num_Factura");
                String nombreCliente = rs.getString("c.Nombre");
                String nit = rs.getString("f.Nit_Cliente");
                String fecha = rs.getString("dev.fecha");
                int idMuebleDevuelto = rs.getInt("dev.id_mueble_devuelto");
                String producto = rs.getString("e.Nombre");
                Devolucion devolucion = new Devolucion(idMuebleDevuelto, numFactura, Formateo.formatearFechaEnAEs(fecha), nombreCliente, nit, producto);
                devoluciones.add(devolucion);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return devoluciones;
    }

    public List<Devolucion> obtenerAllDevoluciones() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Devolucion> devoluciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ALL_DEVOLUCIONES);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("dev.num_factura");
                String nombreCliente = rs.getString("c.nombre");
                String nit = rs.getString("f.nit_cliente");
                String fecha = rs.getString("dev.fecha");
                int idMuebleDevuelto = rs.getInt("dev.id_mueble_devuelto");
                String producto = rs.getString("e.Nombre");

                Devolucion devolucion = new Devolucion(idMuebleDevuelto, numFactura, Formateo.formatearFechaEnAEs(fecha), nombreCliente, nit, producto);
                devoluciones.add(devolucion);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return devoluciones;
    }

    @Override
    public int Insertar(Devolucion modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getIdDevolucion());
            stmt.setInt(2, modelo.getNumFactura());
            stmt.setString(3, modelo.getFecha());
            stmt.setDouble(4, modelo.getPerdida());

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
    public Devolucion Encontrar(Devolucion modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devolucion> Listar(Devolucion t) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devolucion> Listar() throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Eliminar(Devolucion modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Actualizar(Devolucion modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
