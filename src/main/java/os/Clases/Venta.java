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
public class Venta {

    private String mueble;
    private String nitVenta;
    public Venta(String mueble, String nitVenta) {

            this.mueble = mueble;
            this.nitVenta = nitVenta;
    }

    public String getMueble() {
        return mueble;
    }

    public void setMueble(String mueble) {
        this.mueble = mueble;
    }

    public String getNitVenta() {
        return nitVenta;
    }

    public void setNitVenta(String nitVenta) {
        this.nitVenta = nitVenta;
    }

}
