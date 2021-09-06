/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.*;
import os.Clases.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class ClienteDao implements Agregados<Cliente> {
     private static final String SQL_INSERT = "INSERT INTO Cliente(Nit,Nombre_Cliente,Direccion,Municipio,Departamento) VALUES(?,?,?,?,?)";
    private static final String SQL_EXISTE = "SELECT * FROM Cliente WHERE Nit=?";

    public boolean existe(String nit) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, nit);
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
    public Cliente Encontrar(Cliente modelo) throws Excepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, modelo.getNit());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String Nombre = rs.getString("Nombre");
                String Direccion = rs.getString("Direccion");
                String Municipio = rs.getString("Municipio");
                String Departamento = rs.getString("Departamento");
                modelo.setNombre(Nombre);
                modelo.setDireccion(Direccion);
                modelo.setMunicipo(Municipio);
                modelo.setDepartamento(Departamento);
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
    public int Insertar(Cliente modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNit());
            stmt.setString(2, modelo.getNombre());
            stmt.setString(3, modelo.getDireccion());
            stmt.setString(4, modelo.getMunicipo());
            stmt.setString(5, modelo.getDepartamento());

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
    public int Eliminar(Cliente modelo) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    @Override
    public int Actualizar(Cliente modelo) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    @Override
    public List<Cliente> Listar() throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    @Override
    public List<Cliente> Listar(Cliente modelo) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
