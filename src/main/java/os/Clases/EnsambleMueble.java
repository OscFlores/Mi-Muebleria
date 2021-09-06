/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Clases;

import java.time.LocalDate;

/**
 *
 * @author Oscar
 */
public class EnsambleMueble {

    private String mueble;
    private String usuario;
    private LocalDate fecha;
    private Double costo;

    public EnsambleMueble(String mueble, String usuario, String fecha, double costo) {
        try {
            this.mueble = mueble;
            this.usuario = usuario;
            this.fecha = convertirFecha(fecha);
            this.costo = costo;
        } catch (Exception e) {
        }
    }

    public String getMueble() {
        return mueble;
    }

    public void setMueble(String mueble) {
        this.mueble = mueble;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = convertirFecha(fecha);
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public static LocalDate convertirFecha(String fecha) {
        if (fecha.contains("/")) {
            String[] fechaDividida = fecha.split("/");
            int dia = Integer.valueOf(fechaDividida[0]);
            int mes = Integer.valueOf(fechaDividida[1]);
            int anio = Integer.valueOf(fechaDividida[2]);
            return LocalDate.of(anio, mes, dia);
        } else{
            String[] fechadiv=fecha.split("-");
            int anio= Integer.valueOf(fechadiv[0]);
            int mes = Integer.valueOf(fechadiv[1]);
            int dia = Integer.valueOf(fechadiv[2]);
            return LocalDate.of(anio, mes, dia);
         }

    }

}
