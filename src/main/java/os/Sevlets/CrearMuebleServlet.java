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

/**
 *
 * @author Oscar
 */
@WebServlet(name = "CrearMuebleServlet", urlPatterns = {"/CrearMuebleServlet"})
public class CrearMuebleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreMueble = request.getParameter("nombreMueble");
        String precioVenta = request.getParameter("precioMueble");
        if (!precioVenta.isEmpty() && !nombreMueble.isEmpty()) {
            try {
                Mueble mueble = new Mueble(nombreMueble, precioVenta);
                DBMueble.agregarMueble(mueble);
                response.sendRedirect("administracionJsp.jsp");
            } catch (MiMuebleriaException | SQLException ex) {
                response.sendRedirect("muebleria/ErrorAdmin.jsp?error=Error al crear Mueble");
            }
        } else {
            response.sendRedirect("muebleria/ErrorAdmin.jsp?error=Campos vacios");
        }
    }

}
