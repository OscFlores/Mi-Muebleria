/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Modelos;

import os.Clases.*;
import os.Config.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class DBCliente {
    private static ArrayList<ERROR> errores=new ArrayList<>();
    public static void AgregarCliente(Cliente cliente) throws MiMuebleriaException{
        if (cliente.getMunicipio()!=null) {
            try {
                PreparedStatement insertCliente=(PreparedStatement)Conexion.conexion().prepareStatement("INSERT IGNORE INTO cliente(nombre,nit,direccion,municipio,departamento) VALUES(?,?,?,?,?)");
                insertCliente.setString(1, cliente.getNombre());
                insertCliente.setString(2, cliente.getNIT());
                insertCliente.setString(3, cliente.getDireccion());
                insertCliente.setString(4, cliente.getMunicipio());
                insertCliente.setString(5, cliente.getDepartamento());
                insertCliente.execute();
            } catch (MiMuebleriaException | SQLException ex) {
                 errores.add(new ERROR("FORMATO: "+cliente.toString(),"Datos Incorrectos de cliente"));
            }
        } else if(cliente.getMunicipio()==null){
            try {
                PreparedStatement insertCliente=(PreparedStatement)Conexion.conexion().prepareStatement("INSERT IGNORE INTO cliente(nombre,nit,direccion) VALUES(?,?,?)");
                insertCliente.setString(1, cliente.getNombre());
                insertCliente.setString(2, cliente.getNIT());
                insertCliente.setString(3, cliente.getDireccion());
                insertCliente.execute();
            } catch (MiMuebleriaException | SQLException ex) {
                errores.add(new ERROR("FORMATO: "+cliente.toString(),"Datos Incorrectos de cliente"));
            }
        } else{
            errores.add(new ERROR("FORMATO: "+cliente.toString(),"Datos Incorrectos de cliente"));
        }
    }
}
