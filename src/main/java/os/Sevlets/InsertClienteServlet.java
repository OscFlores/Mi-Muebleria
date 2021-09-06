/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Sevlets;

import os.Clases.*;
import os.Config.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yefri
 */
@WebServlet(name = "InsertClienteServlet", urlPatterns = {"/InsertClienteServlet"})
public class InsertClienteServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
        String nombreCliente = request.getParameter("nombreCliente");
        String nitCliente = request.getParameter("nitCliente");
        String direccion = request.getParameter("direccion");
        String muncipio = request.getParameter("municipio");
        String departamento = request.getParameter("departamento");
        String username=request.getParameter("user");
        if (nombreCliente != null && nitCliente != null && !nitCliente.contains("-") && direccion != null && muncipio != null && departamento != null) {
            Cliente cliente = new Cliente(nombreCliente, nitCliente, direccion, muncipio, departamento);
            try {
                PreparedStatement insertCliente = (PreparedStatement) Conexion.conexion().prepareStatement("INSERT INTO cliente(nombre,nit,direccion,municipio,departamento) VALUES(?,?,?,?,?)");
                insertCliente.setString(1, cliente.getNombre());
                insertCliente.setString(2, cliente.getNIT());
                insertCliente.setString(3, cliente.getDireccion());
                insertCliente.setString(4, cliente.getMunicipio());
                insertCliente.setString(5, cliente.getDepartamento());
                insertCliente.execute();
                response.sendRedirect("ventasJsp.jsp?user="+username);
            } catch (SQLException | MiMuebleriaException ex) {
                response.sendRedirect("muebleria/ErroresVentas.jsp?user="+username+"&error=Error al Agregar cliente");
            }
        } else if (nombreCliente != null && nitCliente != null && !nitCliente.contains("-") && direccion != null && muncipio == null && departamento == null) {
            try {
                Cliente cliente = new Cliente(nombreCliente, nitCliente, direccion);
                PreparedStatement insertCliente = (PreparedStatement) Conexion.conexion().prepareStatement("INSERT INTO cliente(nombre,nit,direccion) VALUES(?,?,?)");
                insertCliente.setString(1, cliente.getNombre());
                insertCliente.setString(2, cliente.getNIT());
                insertCliente.setString(3, cliente.getDireccion());
                insertCliente.execute();
                response.sendRedirect("ventasJsp.jsp?user="+username);
            } catch (MiMuebleriaException | SQLException ex) {
                response.sendRedirect("muebleria/ErroresVentas.jsp?user="+username+"&error=Error al agregar Cliente");
            }
        } else {
            response.sendRedirect("muebleria/ErroresVentas.jsp?user="+username+"&error=Datos Incorrectos");
        }
    }
}
