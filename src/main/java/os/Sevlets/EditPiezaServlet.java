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
@WebServlet(name = "EditPiezaServlet", urlPatterns = {"/EditPiezaServlet"})
public class EditPiezaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user=request.getParameter("user");
        String tipoPieza=request.getParameter("tipoPieza");
        String id=request.getParameter("idPieza");
        String costo=request.getParameter("costoPieza");
        if (costo!=null && id!=null && tipoPieza!=null) {
            DBPieza db=new DBPieza();
            if (db.Editar(tipoPieza, id, costo)) {
                response.sendRedirect("Piezas_Servlet?user="+user);
            } else {
                response.sendRedirect("muebleria/ErrorFabrica.jsp?error=Error al editar&user="+user);
            }
        } else {
            response.sendRedirect("muebleria/ErrorFabrica.jsp?error=Error al editar&user="+user);
        }
        
    }

}
