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
public class EnsambleMueble {
    private int idEnsamble;
    private String Fecha;
    private String Nombre_Usuario;
    private String Nombre;
    private double Costo;
    private int Cant_Devolucion;
    private int Cant_Venta;
    private double precio_Venta;
    private List<Armar> piezasUsadas;

    public EnsambleMueble(String Fecha, String Nombre_Usuario, String Nombre, double Costo) {
        this.Fecha = Fecha;
        this.Nombre_Usuario = Nombre_Usuario;
        this.Nombre = Nombre;
        this.Costo = Costo;
    }

    public EnsambleMueble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public EnsambleMueble(int idEnsamble, String Nombre, double precio_Venta) {
        this.idEnsamble = idEnsamble;
        this.Nombre = Nombre;
        this.precio_Venta = precio_Venta;
    }

    public EnsambleMueble(int idEnsamble, String Fecha, String Nombre_Usuario, String Nombre, double Costo, double precio_Venta,int Cant_Devolucion) {
        this.idEnsamble = idEnsamble;
        this.Fecha = Fecha;
        this.Nombre_Usuario = Nombre_Usuario;
        this.Nombre = Nombre;
        this.Costo = Costo;
        this.precio_Venta = precio_Venta;
        this.Cant_Devolucion = Cant_Devolucion;
    }

    public EnsambleMueble(int idEnsamble, String Fecha, String Nombre_Usuario, String Nombre, double precio_Venta,int Cant_Venta) {
        this.idEnsamble = idEnsamble;
        this.Fecha = Fecha;
        this.Nombre_Usuario = Nombre_Usuario;
        this.Nombre = Nombre;
        this.precio_Venta = precio_Venta;
        this.Cant_Venta = Cant_Venta;
    }

    public EnsambleMueble(String Fecha, String Nombre_Usuario, String Nombre) {
        this.Fecha = Fecha;
        this.Nombre_Usuario = Nombre_Usuario;
        this.Nombre = Nombre;
    }

    public EnsambleMueble(String Fecha, String Nombre_Usuario, String Nombre, double Costo, int Cant_Devolucion, int Cant_Venta, double precio_Venta) {
        this.Fecha = Fecha;
        this.Nombre_Usuario = Nombre_Usuario;
        this.Nombre = Nombre;
        this.Costo = Costo;
        this.Cant_Devolucion = Cant_Devolucion;
        this.Cant_Venta = Cant_Venta;
        this.precio_Venta = precio_Venta;
    }

    public EnsambleMueble(String Fecha, String Nombre_Usuario, double precio_Venta) {
        this.Fecha = Fecha;
        this.Nombre_Usuario = Nombre_Usuario;
        this.precio_Venta = precio_Venta;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public void setPiezasUsadas(List<Armar> piezasUsadas) {
        this.piezasUsadas = piezasUsadas;
    }

    public List<Armar> getPiezasUsadas() {
        return piezasUsadas;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setNombre_Usuario(String Nombre_Usuario) {
        this.Nombre_Usuario = Nombre_Usuario;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public void setCant_Devolucion(int Cant_Devolucion) {
        this.Cant_Devolucion = Cant_Devolucion;
    }

    public void setCant_Venta(int Cant_Venta) {
        this.Cant_Venta = Cant_Venta;
    }

    public void setPrecio_Venta(double precio_Venta) {
        this.precio_Venta = precio_Venta;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getNombre_Usuario() {
        return Nombre_Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public double getCosto() {
        return Costo;
    }

    public int getCant_Devolucion() {
        return Cant_Devolucion;
    }

    public int getCant_Venta() {
        return Cant_Venta;
    }

    public double getPrecio_Venta() {
        return precio_Venta;
    }
    
}
