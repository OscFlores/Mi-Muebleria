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
public class Factura {
    private int No_Factura;
    private String Fecha_Factura;
    private String NitCliente;
    private String NombreCliente;
    private String Usuario_Vendedor;
    private List<Propiedades> Propiedades;
    private double Total;

    public Factura(String NitCliente, String Fecha_Factura, String Usuario_Vendedor) {
        this.NitCliente = NitCliente;
        this.Fecha_Factura = Fecha_Factura;
        this.Usuario_Vendedor = Usuario_Vendedor;
    }

    public Factura(int No_Factura, String Fecha_Factura, String NitCliente, String NombreCliente, String Usuario_Vendedor, double Total) {
        this.No_Factura = No_Factura;
        this.Fecha_Factura = Fecha_Factura;
        this.NitCliente = NitCliente;
        this.NombreCliente = NombreCliente;
        this.Usuario_Vendedor = Usuario_Vendedor;
        this.Total = Total;
    }

    public void setPropiedades(List<Propiedades> Propiedades) {
        this.Propiedades = Propiedades;
    }

    public List<Propiedades> getPropiedades() {
        return Propiedades;
    }

    public int getNo_Factura() {
        return No_Factura;
    }

    public String getFecha_Factura() {
        return Fecha_Factura;
    }

    public String getNitCliente() {
        return NitCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public String getUsuario_Vendedor() {
        return Usuario_Vendedor;
    }

    public double getTotal() {
        return Total;
    }

    public void setNo_Factura(int No_Factura) {
        this.No_Factura = No_Factura;
    }

    public void setFecha_Factura(String Fecha_Factura) {
        this.Fecha_Factura = Fecha_Factura;
    }

    public void setNitCliente(String NitCliente) {
        this.NitCliente = NitCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public void setUsuario_Vendedor(String Usuario_Vendedor) {
        this.Usuario_Vendedor = Usuario_Vendedor;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }


    
}
