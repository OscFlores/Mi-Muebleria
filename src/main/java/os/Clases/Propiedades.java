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
public class Propiedades {
    private int numPropiedad;
     private int numFactura;
    private int idEnsamble;
    private double precio;
    private String nombreProducto;
    private String Fecha_Compra;

    public Propiedades(int numPropiedad, int numFactura, int idEnsamble, double precio, String nombreProducto) {
        this.numPropiedad = numPropiedad;
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
    }

    public Propiedades(int numPropiedad, int numFactura, int idEnsamble, double precio) {
        this.numPropiedad = numPropiedad;
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
    }
    

    public Propiedades(int numFactura, int idEnsamble, double precio, String nombreProducto, String Fecha_Compra) {
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
        this.Fecha_Compra = Fecha_Compra;
    }

    public int getNumPropiedad() {
        return numPropiedad;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getFecha_Compra() {
        return Fecha_Compra;
    }

    public void setNumPropiedad(int numPropiedad) {
        this.numPropiedad = numPropiedad;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setFecha_Compra(String Fecha_Compra) {
        this.Fecha_Compra = Fecha_Compra;
    }

   
    
}
