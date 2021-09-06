/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Clases.Usuario;
import os.Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import os.Config.Excepciones;
import os.Clases.Usuario;
/**
 *
 * @author Oscar
 */
public class UsuarioDAO implements Agregados<Usuario> {
    private static final String SQL_INSERT = "INSERT INTO Usuario VALUES(?,?,?,0)";
    private static final String SQL_SELECT_EXISTE_USUARIO = "SELECT * FROM Usuario WHERE Nombre_Usuario=?";
    private static final String SQL_SELECT_BY_NU = "SELECT a.Nombre_Usuario, u.Password, u.Tipo FROM Usuario u WHERE u.nombre_usuario=?";
    private static final String SQL_UPDATE = "UPDATE Usuario WHERE Nombre_Usuario=?";
    private static final String SQL_SELECT2 = "SELECT Nombre_Usuario, Tipo FROM Usuario";
    private static final String SQL_DESHABILITAR = "UPDATE usuario SET deshabilitado=1 WHERE nombre_usuario=?";
    private static final String SQL_ESTA_HABILITADO = "SELECT deshabilitado FROM usuario WHERE nombre_usuario=?";
    private static final String SQL_SELECT_EXISTE_TIPO = "SELECT * FROM Tipo WHERE Tipo=?";
     
    public boolean Existe(String nombreUsuario) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_EXISTE_USUARIO);
            stmt.setString(1, nombreUsuario);
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
    public Usuario Encontrar(Usuario Usuario) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NU);
            stmt.setString(1, Usuario.getNombre_Usuario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String passw = rs.getString("u.Password");
                int tipo = rs.getInt("u.Tipo");
                Usuario.setPassword(passw);
                Usuario.setTipo(tipo);
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return Usuario;
    }

    @Override
    public int Insertar(Usuario Usuario) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, Usuario.getNombre_Usuario());
            stmt.setString(2, Usuario.getPassword());
            stmt.setInt(3, Usuario.getTipo());
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
    public List<Usuario> Listar(Usuario modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int Eliminar(Usuario modelo) throws Excepciones {
        return 0;
    }

    @Override
    public int Actualizar(Usuario modelo) throws Excepciones {
        return 0;
    }

    public int Actualizar(int idArea, String usuario) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1,idArea);
            stmt.setString(2, usuario);

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    @Override
    public List<Usuario> Listar() throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT2);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nombre = rs.getString("nombre_usuario");
                int Tipo = rs.getInt("Tipo");
                Usuario usuario = new Usuario(Nombre, Tipo);
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }
    public boolean estaDeshabilitado(String nombreUsuario) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean deshabilitado = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ESTA_HABILITADO);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("deshabilitado") == 1) {
                    deshabilitado = true;
                }
            }
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return deshabilitado;
    }
    
    public int deshabilitar(String nombreUsuario) throws Excepciones {
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DESHABILITAR);
            stmt.setString(1, nombreUsuario);
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }
    public boolean existe(int idArea) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_EXISTE_TIPO);
            stmt.setInt(1, idArea);
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
}
