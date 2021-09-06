/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Config;

/**
 *
 * @author Oscar
 */
public class Excepciones extends Exception {
    public static final long SERIAL_VERSION = 500L;

    public Excepciones(String mensaje){
        super(mensaje);
    }
}
