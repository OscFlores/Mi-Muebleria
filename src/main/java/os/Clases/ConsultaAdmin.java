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
public class ConsultaAdmin {
    private String fechaVenta;
    private String nombreProducto;
    private double precioVenta;
    private int numFactura;
    private String fechaDevolucion;
    private String nombreCliente;
    private String usuarioVendedor;
    private double perdida;
    private double costo;
    private double ganancia;

    public ConsultaAdmin(String fechaVenta, String nombreProducto, double precioVenta, double costo, double ganancia) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.costo = costo;
        this.ganancia = ganancia;
    }

    public ConsultaAdmin(String fechaVenta, String nombreProducto, int numFactura, String fechaDevolucion, String nombreCliente, String usuarioVendedor, double perdida) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.numFactura = numFactura;
        this.fechaDevolucion = fechaDevolucion;
        this.nombreCliente = nombreCliente;
        this.usuarioVendedor = usuarioVendedor;
        this.perdida = perdida;
    }

    public ConsultaAdmin(String fechaVenta, String nombreProducto, double precioVenta, int numFactura, String usuarioVendedor) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.numFactura = numFactura;
        this.usuarioVendedor = usuarioVendedor;
    }

    public ConsultaAdmin(String fechaVenta, double precioVenta, int numFactura) {
        this.fechaVenta = fechaVenta;
        this.precioVenta = precioVenta;
        this.numFactura = numFactura;
    }

    public ConsultaAdmin(String fechaVenta, String nombreProducto, double precioVenta) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setUsuarioVendedor(String usuarioVendedor) {
        this.usuarioVendedor = usuarioVendedor;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getUsuarioVendedor() {
        return usuarioVendedor;
    }

    public double getPerdida() {
        return perdida;
    }

    public double getCosto() {
        return costo;
    }

    public double getGanancia() {
        return ganancia;
    }

    
}
