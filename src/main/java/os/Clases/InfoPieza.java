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
public class InfoPieza {
    private int idTipoPieza;
    private String Nombre;
    private int Cantidad;
    
    public InfoPieza(){}

    public InfoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public InfoPieza(int idTipoPieza, String Nombre, int Cantidad) {
        this.idTipoPieza = idTipoPieza;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
    }

    public InfoPieza(int idTipoPieza, String Nombre) {
        this.idTipoPieza = idTipoPieza;
        this.Nombre = Nombre;
    }

    public void setIdTipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public InfoPieza(String Nombre) {
        this.Nombre = Nombre;
    }
    

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getIdTipoPieza() {
        return idTipoPieza;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }
    
}
