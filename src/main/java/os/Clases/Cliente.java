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
public class Cliente {
    private String Nit; 
    private String Nombre;
    private String Direccion;
    private String Municipo;
    private String Departamento;
    
    public Cliente(){}

    public Cliente(String Nit) {
        this.Nit = Nit;
    }

    public Cliente(String Nit, String Nombre, String Direccion) {
        this.Nit = Nit;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
    }

    public Cliente(String Nit, String Nombre, String Direccion, String Municipo, String Departamento) {
        this.Nit = Nit;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Municipo = Municipo;
        this.Departamento = Departamento;
    }

    public String getNit() {
        return Nit;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getMunicipo() {
        return Municipo;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setMunicipo(String Municipo) {
        this.Municipo = Municipo;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }
    
    
}
