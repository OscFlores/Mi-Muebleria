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
public class Armar {
     private int idPiezaUsada;
    private int idEnsamble;
    private String nombrePieza;
    private double precioPieza;

    public Armar(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public Armar(int idPiezaUsada, int idEnsamble) {
        this.idPiezaUsada = idPiezaUsada;
        this.idEnsamble = idEnsamble;
    }

    public Armar(String nombrePieza, double precioPieza) {
        this.nombrePieza = nombrePieza;
        this.precioPieza = precioPieza;
    }

    public void setIdPiezaUsada(int idPiezaUsada) {
        this.idPiezaUsada = idPiezaUsada;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public void setPrecioPieza(double precioPieza) {
        this.precioPieza = precioPieza;
    }

    public int getIdPiezaUsada() {
        return idPiezaUsada;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public double getPrecioPieza() {
        return precioPieza;
    }
    
    
}
