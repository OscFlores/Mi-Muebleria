package os.Clases;

/**
 *
 * @author Oscar
 */
public class Mueble {

    private String tipoMueble;
    private double precioVenta;

    public Mueble(String tipoMueble, String precioVenta) {
        try {
            this.tipoMueble = tipoMueble;
            this.precioVenta = Double.valueOf(precioVenta);
        } catch (NumberFormatException e) {
        }
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        try {
            this.precioVenta = Double.valueOf(precioVenta);
        } catch (NumberFormatException e){
            
        }
    }

}
