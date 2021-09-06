/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Clases;

/**
 *
 * @author Oscar
 */
public class EnsamblePieza {
    private int numRequisito;
    private String Nombre;
    private int idTipoPieza;
    private String Tipo;
    private int Cantidad;

    public EnsamblePieza() {
    }

    public EnsamblePieza(String Tipo) {
        this.Tipo = Tipo;
    }

    public EnsamblePieza(String Nombre, int idTipoPieza, int Cantidad) {
        this.Nombre = Nombre;
        this.idTipoPieza = idTipoPieza;
        this.Cantidad = Cantidad;
    }

    public EnsamblePieza(String Nombre, int idTipoPieza, String Tipo) {
        this.Nombre = Nombre;
        this.idTipoPieza = idTipoPieza;
        this.Tipo = Tipo;
    }

    public EnsamblePieza(int numRequisito, String Nombre, int idTipoPieza, int Cantidad) {
        this.numRequisito = numRequisito;
        this.Nombre = Nombre;
        this.idTipoPieza = idTipoPieza;
        this.Cantidad = Cantidad;
    }

    public EnsamblePieza(int idTipoPieza, String Tipo, int Cantidad) {
        this.idTipoPieza = idTipoPieza;
        this.Tipo = Tipo;
        this.Cantidad = Cantidad;
    }

    public EnsamblePieza(int numRequisito, int idTipoPieza, String Tipo, int Cantidad) {
        this.numRequisito = numRequisito;
        this.idTipoPieza = idTipoPieza;
        this.Tipo = Tipo;
        this.Cantidad = Cantidad;
    }

    public EnsamblePieza(String Nombre, int Cantidad) {
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
    }

    public void setNumRequisito(int numRequisito) {
        this.numRequisito = numRequisito;
    }

    public void setIdTipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public int getNumRequisito() {
        return numRequisito;
    }

    public int getIdTipoPieza() {
        return idTipoPieza;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public EnsamblePieza(String Nombre, String Tipo, int Cantidad) {
        this.Nombre = Nombre;
        this.Tipo = Tipo;
        this.Cantidad = Cantidad;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

}
