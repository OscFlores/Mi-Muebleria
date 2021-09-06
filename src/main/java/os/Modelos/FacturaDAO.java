/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.*;
import os.Clases.Factura;
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
public class FacturaDAO implements Agregados<Factura>{
    private static final String SQL_INSERT = "INSERT INTO Factura(Nit_Cliente,Fecha_Factura,Usuario_Vendedor) VALUES(?,?,?)";
    private static final String SQL_SELECCIONAR_ULTIMO = "SELECT No_Factura FROM Factura ORDER BY No_Factura DESC LIMIT 1";
    private static final String SQL_ENCONTRAR_BY_NUM_FACTURA = "SELECT * FROM Factura WHERE No_Factura=?";
    private static final String SQL_INFO_ALL_FACTURA = "SELECT f.No_Factura, c.Nombre, f.Nit_Cliente, f.Fecha_Factura, f.Usuario_Vendedor, f.Total FROM Factura f JOIN Cliente c ON(f.Nit_Cliente=c.Nit) ";
    private static final String SQL_SELECT_BY_NUM_FACTURA = "SELECT f.No_Factura, c.Nombre, f.Nit_Cliente, f.Fecha_Factura, f.Usuario_Vendedor, f.Total FROM Factura f JOIN Cliente c ON(f.Nit_Cliente=c.Nit) WHERE f.No_Factura=?";
    private static final String SQL_SELECT_BY_NIT_CLIENTE = "SELECT f.No_Factura, c.Nombre, f.Nit_Cliente, f.Fecha_Factura, f.Usuario_Vendedor, f.Total FROM Factura f JOIN Cliente c ON(f.Nit_Cliente=c.Nit) WHERE f.Nit_Cliente=?";
    private static final String SQL_AGREGAR_TOTAL = "UPDATE Factura SET Total=? WHERE No_Factura = ?";
    private static final String SQL_SELECT_BY_NIT_CLIENTES_AND_FECHAS = "SELECT f.No_Factura, c.Nombre, f.Nit_Cliente, f.Fecha_Factura, f.Usuario_Vendedor, f.Total FROM Factura f JOIN Cliente c ON(f.Nit_Cliente=c.Nit) WHERE f.Fecha_Factura BETWEEN ? AND ? AND f.Nit_Cliente=? ORDER BY f.Fecha_Factura DESC";

    public List<Factura> listarByIntervaloFechas(String fechaInicial, String fechaFinal, String nitCliente) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NIT_CLIENTES_AND_FECHAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, nitCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int noFactura = rs.getInt("f.No_Factura");
                String nombreCliente = rs.getString("c.Nombre");
                String nit = rs.getString("f.Nit_Cliente");
                String fecha = rs.getString("f.Fecha_Factura");
                String vendedor = rs.getString("f.Usuario_Vendedor");
                double total = rs.getDouble("f.Total");
                Factura factura = new Factura(noFactura, nit, nombreCliente, Formateo.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return facturas;
    }

    public List<Factura> listarByNumFactura(int numFactura) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NUM_FACTURA);
            stmt.setInt(1, numFactura);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreCliente = rs.getString("c.Nombre");
                String nit = rs.getString("f.Nit_Cliente");
                String fecha = rs.getString("f.Fecha_Factura");
                String vendedor = rs.getString("f.Usuario_Vendedor");
                double total = rs.getDouble("f.Total");
                Factura factura = new Factura(numFactura, nit, nombreCliente, Formateo.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return facturas;
    }

    public List<Factura> listarByNitCliente(String nitCliente) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NIT_CLIENTE);
            stmt.setString(1, nitCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int noFactura = rs.getInt("f.No_Factura");
                String nombreCliente = rs.getString("c.Nombre");
                String nit = rs.getString("f.Nit_Cliente");
                String fecha = rs.getString("f.Fecha_Factura");
                String vendedor = rs.getString("f.vendedor");
                double total = rs.getDouble("f.total");
                Factura factura = new Factura(noFactura, nit, nombreCliente, Formateo.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return facturas;
    }

    public void agregarTotal(double total, int noFactura) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_AGREGAR_TOTAL);
            stmt.setDouble(1, total);
            stmt.setInt(2, noFactura);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public int obtenerNumFactura() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numFactura = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECCIONAR_ULTIMO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                numFactura = rs.getInt("No_Factura");
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numFactura;
    }

    @Override
    public Factura Encontrar(Factura modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Factura Encontrar(int numFactura) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Factura factura = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_BY_NUM_FACTURA);
            stmt.setInt(1, numFactura);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nitCliente = rs.getString("Nit_Cliente");
                String fecha = rs.getString("Fecha_Factura");
                String vendedor = rs.getString("Usuario_Vendedor");
                factura = new Factura(nitCliente, fecha, vendedor);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return factura;
    }

    @Override
    public List<Factura> Listar(Factura t) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Factura> Listar() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INFO_ALL_FACTURA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int noFactura = rs.getInt("f.No_Factura");
                String nombreCliente = rs.getString("c.Nombre");
                String nit = rs.getString("f.Nit_Cliente");
                String fecha = rs.getString("f.Fecha_Factura");
                String vendedor = rs.getString("f.Usuario_Vendedor");
                double total = rs.getDouble("f.Total");
                Factura factura = new Factura(noFactura, nit, nombreCliente,Formateo.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return facturas;
    }

    @Override
    public int Insertar(Factura modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNitCliente());
            stmt.setString(2, modelo.getFecha_Factura());
            stmt.setString(3, modelo.getUsuario_Vendedor());

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
    public int Eliminar(Factura modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Actualizar(Factura modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
