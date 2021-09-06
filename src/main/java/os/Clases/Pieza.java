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
public class Pieza {
    private int idTipoPieza;
    private int idPieza;
    private String Tipo;
    private double Costo;
    private int Usado;
    
    public Pieza(){}

    public Pieza(String Tipo, double Costo) {
        this.Tipo = Tipo;
        this.Costo = Costo;
    }

    public Pieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public Pieza(int idTipoPieza, double Costo) {
        this.idTipoPieza = idTipoPieza;
        this.Costo = Costo;
    }

    public Pieza(int idTipoPieza, int idPieza, String Tipo, double Costo, int Usado) {
        this.idTipoPieza = idTipoPieza;
        this.idPieza = idPieza;
        this.Tipo = Tipo;
        this.Costo = Costo;
        this.Usado = Usado;
    }

    public Pieza(int idPieza, String Tipo, double Costo) {
        this.idPieza = idPieza;
        this.Tipo = Tipo;
        this.Costo = Costo;
    }

    public int getIdTipoPieza() {
        return idTipoPieza;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public int getUsado() {
        return Usado;
    }

    public String getTipo() {
        return Tipo;
    }

    public double getCosto() {
        return Costo;
    }

    public void setIdTipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public void setUsado(int Usado) {
        this.Usado = Usado;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }
    
}
