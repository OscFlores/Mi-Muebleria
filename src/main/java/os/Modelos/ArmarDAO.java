/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;
import os.Config.*;
import os.Clases.Armar;
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
public class ArmarDAO implements Agregados<Armar> {
    private static final String SQL_INSERT = "INSERT INTO Armar(Id_pieza_use,Id_Ensamble) VALUES(?,?)";
    private static final String SQL_SELECT = "SELECT t.nombre, p.precio, a.id_pieza_usada FROM Armar a JOIN Pieza p ON(a.id_pieza_usada = p.id_pieza) JOIN tipo_pieza tp ON(p.id_tipo_pieza = tp.id_tipo_pieza) WHERE a.id_ensamble=?";
    
    public List<Armar> listarSegunIdEnsamble(int idEnsamble) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Armar> armados = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idEnsamble);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombrePieza = rs.getString("tp.nombre");
                double precio = rs.getDouble("p.precio");
                int idPiezaUsada = rs.getInt("a.id_pieza_usada");
                Armar armado = new Armar(nombrePieza, precio);
                armado.setIdPiezaUsada(idPiezaUsada);
                armados.add(armado);
            }
            
        } catch (SQLException ex) {
            throw new Excepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return armados;
    }
    
    @Override
    public int Insertar(Armar modelo) throws Excepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getIdPiezaUsada());
            stmt.setInt(2, modelo.getIdEnsamble());
            
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
    public int Eliminar(Armar modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int Actualizar(Armar modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public Armar Encontrar(Armar modelo) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Armar> Listar(Armar O) throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Armar> Listar() throws Excepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
