/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Sevlets;

import os.Clases.*;
import os.Modelos.*;;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yefri
 */
@WebServlet(name = "Piezas_Servlet", urlPatterns = {"/Piezas_Servlet"})
public class Piezas_Servlet extends HttpServlet {
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
        String user=request.getParameter("user");
        DBPieza dbPieza=new DBPieza();
        List<Pieza> list=new ArrayList<>();
        list= dbPieza.Listar();
        request.setAttribute("piezalist", list);
        request.getRequestDispatcher("muebleria/piezasJsp.jsp?user="+user).forward(request, response);
    }
    

}
