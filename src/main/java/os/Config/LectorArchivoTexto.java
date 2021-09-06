package os.Config;

import os.Clases.*;
import os.Modelos.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class LectorArchivoTexto {

    private static final String INICIO_USUARIO = "USUARIO(";
    private static final String INICIO_PIEZA = "PIEZA(";
    private static final String INICIO_MUEBLE = "MUEBLE(";
    private static final String INICIO_ENSAMBLE_PIEZA = "ENSAMBLE_PIEZAS(";
    private static final String INICIO_ENSAMBLE_MUEBLE = "ENSAMBLAR_MUEBLE(";
    private static final String INICIO_CLIENTE = "CLIENTE(";
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Pieza> piezas = new ArrayList<>();
    private ArrayList<Mueble> muebles = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<ERROR> errores = new ArrayList<>();
    private static int cantidadPieza;

    public LectorArchivoTexto() throws MiMuebleriaException, SQLException {
        PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT MAX(id) from pieza");
        ResultSet result = consulta.executeQuery();
        result.next();
        cantidadPieza = result.getInt(1);
    }

    public void leerArchivo(InputStream inputStream) throws FileNotFoundException, IOException, SQLException, MiMuebleriaException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String linea;
        while ((linea = br.readLine()) != null) {
            separarCamposUsuario(linea);
        }
    }

    public void leerArchivo1(InputStream inputStream) throws UnsupportedEncodingException, IOException {
        String linea1;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        while ((linea1 = br.readLine()) != null) {
            separarCamposMueble(linea1);
        }
    }

    public void leerArchivo2(InputStream inputStream) throws UnsupportedEncodingException, IOException, SQLException, MiMuebleriaException {
        String linea;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        while ((linea = br.readLine()) != null) {
            separarCamposPieza(linea);
        }
    }

    public void leerArchivo3(InputStream inputStream) throws UnsupportedEncodingException, IOException, SQLException, MiMuebleriaException {
        String linea;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        while ((linea = br.readLine()) != null) {
            separarCamposEnsamblePiezas(linea);
        }
    }

    public void leerArchivo4(InputStream inputStream) throws UnsupportedEncodingException, IOException, SQLException, MiMuebleriaException {
        String linea;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        while ((linea = br.readLine()) != null) {
            separarCamposEnsambleMueble(linea);
        }
    }

    public void leerArchivo5(InputStream inputStream) throws IOException, MiMuebleriaException {
        String linea;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        while ((linea = br.readLine()) != null) {
            separarCamposCliente(linea);
        }
    }

    public void separarCamposUsuario(String linea) throws MiMuebleriaException, SQLException {
        if (linea.startsWith(INICIO_USUARIO)) {
            String lineaDeCampos = linea.substring(INICIO_USUARIO.length(), linea.length() - 1);
            String[] campos = lineaDeCampos.split(",");
            Usuario usuario = new Usuario(campos[0].replaceAll("\"", ""), campos[1].replaceAll("\"", ""), campos[2].replaceAll("\"", ""), "1");
            usuarios.add(usuario);
            DBUsuario.agregarUsuario(usuario);
        }
    }

    public void separarCamposPieza(String linea) throws SQLException, MiMuebleriaException {
        if (linea.startsWith(INICIO_PIEZA)) {
            String lineaDeCampos = linea.substring(INICIO_PIEZA.length(), linea.length() - 1);
            String[] campos = lineaDeCampos.split(",");
            Pieza pieza = new Pieza(campos[0].replaceAll("\"", ""), String.valueOf(LectorArchivoTexto.getCantidadPieza()).replaceAll("\"", ""), campos[1].replaceAll("\"", ""));
            piezas.add(pieza);
            DBPieza.agregarPieza(pieza);
        }
    }

    public void separarCamposMueble(String linea) {
        try {
            if (linea.startsWith(INICIO_MUEBLE)) {
                String lineaDeCampos = linea.substring(INICIO_MUEBLE.length(), linea.length() - 1);
                String[] campos = lineaDeCampos.split(",");
                Mueble mueble = new Mueble(campos[0].replaceAll("\"", ""), campos[1].replaceAll("\"", ""));
                muebles.add(mueble);
                DBMueble.agregarMueble(mueble);
            }
        } catch (Exception e) {
        }

    }

    public void separarCamposEnsamblePiezas(String linea) throws SQLException, MiMuebleriaException {
        if (linea.startsWith(INICIO_ENSAMBLE_PIEZA)) {
            String lineaDeCampos = linea.substring(INICIO_ENSAMBLE_PIEZA.length(), linea.length() - 1);
            String[] campos = lineaDeCampos.split(",");
            DBEnsamblePieza dbEnsamblePieza = new DBEnsamblePieza();
            dbEnsamblePieza.agregarEnsamblePieza(campos[0].replaceAll("\"", ""), campos[1].replaceAll("\"", ""), campos[2]);
        }
    }

    public void separarCamposEnsambleMueble(String linea) throws SQLException, MiMuebleriaException {
        if (linea.startsWith(INICIO_ENSAMBLE_MUEBLE)) {
            String lineaDeCampos = linea.substring(INICIO_ENSAMBLE_MUEBLE.length(), linea.length() - 1);
            String[] campos = lineaDeCampos.split(",");
            DBEnsambleMueble dbEnsambleMueble = new DBEnsambleMueble();
            dbEnsambleMueble.agregarEnsambleMueble(campos[0].replaceAll("\"", ""), campos[1].replaceAll("\"", ""), campos[2].replaceAll("\"", ""));
        }
    }

    public void separarCamposCliente(String linea) throws MiMuebleriaException {
        if (linea.startsWith(INICIO_CLIENTE)) {
            String lineaDeCampos = linea.substring(INICIO_CLIENTE.length(), linea.length() - 1);
            String[] campos = lineaDeCampos.split(",");
            if (campos.length > 3) {
                if (!campos[1].contains("-")) {
                    Cliente cliente = new Cliente(campos[0].replaceAll("\"", ""), campos[1].replaceAll("\"", ""), campos[2].replaceAll("\"", ""), campos[3].replaceAll("\"", ""), campos[4].replaceAll("\"", ""));
                    clientes.add(cliente);
                    DBCliente.AgregarCliente(cliente);
                } else {
                    errores.add(new ERROR("FORMATO: " + linea, "CONTIENE - "));
                }
            } else if (campos.length <= 3) {
                if (!campos[1].contains("-")) {
                    Cliente cliente = new Cliente(campos[0].replaceAll("\"", ""), campos[1].replaceAll("\"", ""), campos[2].replaceAll("\"", ""));
                    clientes.add(cliente);
                    DBCliente.AgregarCliente(cliente);
                } else {
                    errores.add(new ERROR("FORMATO: " + linea, "CONTIENE - "));
                }
            } else {
                errores.add(new ERROR("FORMATO: " + linea, "No cumple con los datos necesarios"));
            }
        }
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Pieza> piezas) {
        this.piezas = piezas;
    }

    public ArrayList<Mueble> getMuebles() {
        return muebles;
    }

    public void setMuebles(ArrayList<Mueble> muebles) {
        this.muebles = muebles;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public static int getCantidadPieza() {
        return cantidadPieza;
    }

    public static void setCantidadPieza(int cantidadPieza) {
        LectorArchivoTexto.cantidadPieza = cantidadPieza;
    }
}
