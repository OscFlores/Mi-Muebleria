/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Config;

import os.Clases.*;
import java.util.List;

/**
 *
 * @author Oscar
 */
public interface CRUD{
    public List Listar();
    public Pieza List(int id);
    public boolean Editar(String pieza,String id,String costo);
    public boolean Eliminar(int id);
}
