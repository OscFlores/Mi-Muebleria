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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "ControladorPiezaServlet", urlPatterns = {"/ControladorPiezaServlet"})
public class ControladorPiezaServlet extends HttpServlet {

    private ArrayList<ERROR> errores = new ArrayList<>();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoPieza = request.getParameter("nombreCrearPieza");
        String user=request.getParameter("user");
        try {
            String costo = request.getParameter("costoCrearPieza");
            String id = String.valueOf(LectorArchivoTexto.getCantidadPieza());
            if (tipoPieza != null && costo != null) {
                Pieza pieza = new Pieza(tipoPieza, id, costo);
                DBPieza.agregarPieza(pieza);
                response.sendRedirect("Piezas_Servlet");
            } else {
                errores.add(new ERROR("CREACION: ", "Campos vacios" ));
            }

        } catch (MiMuebleriaException | SQLException ex) {
            errores.add(new ERROR("CREACION: ", "Error al crear la pieza" + tipoPieza));
        }
    }

}
