/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Clases;

import java.util.List;

/**
 *
 * @author Oscar
 */
public class Mueble {
    private String Nombre; 
    private double Precio;
    private List<EnsamblePieza> Requerimientos;

    public Mueble(String Nombre) {
        this.Nombre = Nombre;
    }

    public Mueble(String Nombre, double Precio) {
        this.Nombre = Nombre;
        this.Precio = Precio;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public void setRequerimientos(List<EnsamblePieza> Requerimientos) {
        this.Requerimientos = Requerimientos;
    }

    public String getNombre() {
        return Nombre;
    }

    public double getPrecio() {
        return Precio;
    }

    public List<EnsamblePieza> getRequerimientos() {
        return Requerimientos;
    }
    
  
    
    
}
