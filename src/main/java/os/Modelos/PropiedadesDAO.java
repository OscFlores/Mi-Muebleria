/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Clases.Propiedades;
import os.Config.*;
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
public class PropiedadesDAO implements Agregados<Propiedades> {
     private static final String SQL_INSERT = "INSERT INTO Propiedades(Num_Propiedad,Fecha_Factura,Fecha_Ensamble,Precio) VALUES(?,?,?,?)";
    private static final String SQL_EXISTE_COMPRA = "SELECT * FROM Propiedades WHERE Num_Factura=? AND id_Ensamble=?";
    private static final String SQL_OBTENER_DETALLES = "SELECT p.num_Propiedad, p.id_Ensamble, p.precio, e.Nombre FROM Propiedad p JOIN Ensamble_Mueble e ON(p.id_Ensamble=e.id_Ensamble) WHERE num_factura = ? ORDER BY p.Num_ Propiedad ASC";
    private static final String SQL_VENTAS_DIARIAS = "SELECT f.Fecha_Factura, f.num_Factura, d.id_Ensamble, e.Nombre, d.Precio FROM Propiedades p JOIN Factura f ON(d.num_factura=f.No_Factura) JOIN Ensamble_Mueble e ON(p.id_ensamble=e.id_ensamble) WHERE f.Fecha_Factura=?";
    private static final String SQL_COMPRA_ALL_CLIENTES = "SELECT f.Fecha_Factura, c.Nombre, f.No_Factura, p.id_ensamble, e.Nombre, d.precio FROM Propiedades p JOIN Factura f ON(d.num_factura=f.No_Factura) JOIN Ensamble_Mueble e ON(p.id_ensamble=e.id_ensamble) JOIN Cliente c ON(f.Nit_Cliente=c.Nit) ORDER BY f.Fecha_Factira DESC";
    
    
    public List<Propiedades> obtenerComprasAllClientes() throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Propiedades> prop = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_COMPRA_ALL_CLIENTES);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String fechaF = rs.getString("f.Fecha_Factura");
                int numFactura = rs.getInt("f.No_Factura");
                int idEnsamble = rs.getInt("p.id_ensamble");
                String tipoMueble = rs.getString("e.Nombre");
                double precio = rs.getDouble("p.precio");
               Propiedades p = new Propiedades(numFactura, idEnsamble, precio, tipoMueble, Formateo.formatearFechaEnAEs(fechaF));
               prop.add(p);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return prop;
    }
    
    public List<Propiedades> obtenerVentasDia(String fecha) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Propiedades> Propiedades = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_VENTAS_DIARIAS);
            stmt.setString(1, fecha);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String fechaF = rs.getString("f.Fecha_Factura");
                int numFactura = rs.getInt("f.No_Factura");
                int idEnsamble = rs.getInt("p.id_ensamble");
                String tipoMueble = rs.getString("e.Nombre");
                double precio = rs.getDouble("p.precio");
                Propiedades pro = new Propiedades(numFactura, idEnsamble, precio, tipoMueble, fechaF);
                Propiedades.add(pro);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return Propiedades;
    }

    public List<Propiedades> obtenerPropiedadFactura(int numFactura) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Propiedades> propiedades = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_DETALLES);
            stmt.setInt(1, numFactura);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int numPropiedad = rs.getInt("p.num_propiedad");
                int idEnsamble = rs.getInt("p.id_ensamble");
                double precio = rs.getDouble("p.precio");
                String producto = rs.getString("e.Nombre");
                Propiedades pro = new Propiedades(numPropiedad, numFactura, idEnsamble, precio, producto);
                propiedades.add(pro);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return propiedades;
    }

    public boolean existeCompra(int numFactura, int idMueble) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE_COMPRA);
            stmt.setInt(1, numFactura);
            stmt.setInt(2, idMueble);
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
    public int Insertar(Propiedades modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getNumPropiedad());
            stmt.setString(2, modelo.getFecha_Compra());
            stmt.setInt(3, modelo.getIdEnsamble());
            stmt.setDouble(4, modelo.getPrecio());

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
    public Propiedades Encontrar(Propiedades modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Propiedades> Listar(Propiedades O) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Propiedades> Listar() throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Eliminar(Propiedades modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Actualizar(Propiedades modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
