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
public class Ensamble_Pieza {

    private String pieza;
    private int cantidad;
    private String mueble;
    private double costo;

    public Ensamble_Pieza(String mueble, String pieza, String cantidad, Double costo) {
        try {
            this.pieza = pieza;
            this.cantidad = Integer.valueOf(cantidad);
            this.mueble = mueble;
            this.costo = costo;
        } catch (NumberFormatException e) {
        }
    }

    public String getPieza() {
        return pieza;
    }

    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        try {
            this.cantidad = Integer.valueOf(cantidad);
        } catch (NumberFormatException e) {
        }
    }

    public String getMueble() {
        return mueble;
    }

    public void setMueble(String mueble) {
        this.mueble = mueble;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
