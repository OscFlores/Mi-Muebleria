/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Sevlets;


import os.Modelos.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String id = request.getParameter("id");
        DBPieza db = new DBPieza();
        if (id != null) {
            if (db.Eliminar(Integer.valueOf(id))) {
                response.sendRedirect("Piezas_Servlet?user=" + user);
            } else {
                response.sendRedirect("muebleria/ErrorFabrica.jsp?user=" + user + "&error=Error a eliminar pieza");
            }
        } else {
            response.sendRedirect("muebleria/ErrorFabrica.jsp?user=" + user + "&error=Error a eliminar pieza");
        }

    }

}
