/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Sevlets;

import os.Clases.*;
import os.Config.*;
import os.Modelos.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "EnsambleServlet", urlPatterns = {"/EnsambleServlet"})
public class EnsambleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoEnsamble = request.getParameter("tipoEnsamble");
        String user = request.getParameter("user");
        String tipoMueblePieza = request.getParameter("tipoMueblePieza");
        String tipoMuebleEnsamble = request.getParameter("tipoMuebleEnsamble");
        String tipoPieza = request.getParameter("tipoPieza");
        String cantPieza = request.getParameter("cantPiezas");
        String fecha = request.getParameter("fechaEnsamble");
        if (tipoEnsamble.equals("piezas") && tipoMueblePieza != null && tipoPieza != null && cantPieza != null) {
            try {
                DBEnsamblePieza dbpieza = new DBEnsamblePieza();
                dbpieza.agregarEnsamblePieza(tipoMueblePieza, tipoPieza, cantPieza);
                response.sendRedirect("fabricajsp.jsp?user=" + user);
            } catch (SQLException | MiMuebleriaException ex) {
                response.sendRedirect("muebleria/ErrorFabrica.jsp?user" + user + "&error=Error al crear ensable de piezas");
            }
        } else if (tipoEnsamble.equals("mueble") && tipoMuebleEnsamble != null && user != null && fecha != null) {

            try {
                DBEnsambleMueble dbmueble = new DBEnsambleMueble();
                dbmueble.agregarEnsambleMueble(tipoMuebleEnsamble, user, fecha);
                response.sendRedirect("fabricajsp.jsp?user=" + user);
                JOptionPane.showMessageDialog(null, "ensamble de mueble hecho");
            } catch (SQLException | MiMuebleriaException ex) {
                response.sendRedirect("muebleria/ErrorFabrica.jsp?user" + user + "&error=Error al crear ensable de mueble");
            }
        } else {
            response.sendRedirect("muebleria/ErrorFabrica.jsp?user" + user + "&error=Error al crear ensamble de mueble");
        }

    }
}
