/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Config;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Oscar
 */
public class Formateo {
    public static String formatearFechaEsAEn(String fechaString) throws DateTimeException {
        DateTimeFormatter Recibir = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter Devolver = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, Recibir);
        String fechaFormateada = fecha.format(Devolver);
        return fechaFormateada;
    }

    public static String formatearFechaEnAEs(String fechaString) throws DateTimeException {
        DateTimeFormatter Recibir = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter Devolver = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, Recibir);
        String fechaFormateada = fecha.format(Devolver);
        return fechaFormateada;
    }
}
