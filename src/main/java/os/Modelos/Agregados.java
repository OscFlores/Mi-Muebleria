package os.Modelos;

import os.Config.Excepciones;
import java.util.List;

public interface Agregados<O> {
    public O Encontrar(O modelo) throws Excepciones;
    public List<O> Listar() throws Excepciones;
    public int Insertar(O modelo) throws Excepciones;
    public int Eliminar(O modelo) throws Excepciones;
    public int Actualizar(O modelo) throws Excepciones;
    public List<O> Listar(O modelo) throws Excepciones;

}
