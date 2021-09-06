/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Controladores;
import os.Config.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import os.Clases.MiMuebleriaException;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "ReporteUsuarioMasVentas", urlPatterns = {"/ReporteUsuarioMasVentas"})
public class ReporteUsuarioMasVentas extends HttpServlet {

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
        String filename = "ReporteUserMasVentas.csv";
        String fechaIncial = request.getParameter("fechaInicioMasVentas");
        String fechaFinal = request.getParameter("fechaFinalMasVentas");
        try {
            FileWriter fw = new FileWriter(new File(filename));
            if (!fechaIncial.isEmpty() && !fechaFinal.isEmpty()) {
                PreparedStatement consultausuario = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT user,COUNT(user) FROM venta GROUP BY user ORDER BY user ASC LIMIT 1;");
                ResultSet result = consultausuario.executeQuery();
                if (result.next()) {
                    String usuario = result.getString(1);
                    PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM venta WHERE user='" + usuario + "' AND fecha>='" + fechaIncial + "' AND fecha <='" + fechaFinal + "';");
                    ResultSet result2 = consulta.executeQuery();
                    fw.append("Mueble");
                    fw.append(",");
                    fw.append("Nit de cliente");
                    fw.append(",");
                    fw.append("Fecha");
                    fw.append(",");
                    fw.append("Usuario de Venta");
                    fw.append("\n");
                    while (result2.next()) {
                        fw.append(result2.getString(1));
                        fw.append(",");
                        fw.append(result2.getString(2));
                        fw.append(",");
                        fw.append(result2.getString(3));
                        fw.append(",");
                        fw.append(result2.getString(4));
                        fw.append("\n");
                    }
                    fw.flush();
                    fw.close();
                    request.getRequestDispatcher("DescargaArchivo/Download.jsp?archivo=" + filename + "&nombre=Reporte de Usuario con mas ventas Generado").forward(request, response);
                } else {
                    response.sendRedirect("muebleria/ErrorAdmin.jsp?error=Error al obtener datos");
                }
            } else {
                PreparedStatement consultausuario = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT user,COUNT(user) FROM venta GROUP BY user ORDER BY user ASC LIMIT 1;");
                ResultSet result = consultausuario.executeQuery();
                if (result.next()) {
                    String usuario = result.getString(1);
                    PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM venta WHERE user='" + usuario + "';");
                    ResultSet result2 = consulta.executeQuery();
                    fw.append("Mueble");
                    fw.append(",");
                    fw.append("Nit de cliente");
                    fw.append(",");
                    fw.append("Fecha");
                    fw.append(",");
                    fw.append("Usuario de Venta");
                    fw.append("\n");
                    while (result2.next()) {
                        fw.append(result2.getString(1));
                        fw.append(",");
                        fw.append(result2.getString(2));
                        fw.append(",");
                        fw.append(result2.getString(3));
                        fw.append(",");
                        fw.append(result2.getString(4));
                        fw.append("\n");
                    }
                    fw.flush();
                    fw.close();
                    request.getRequestDispatcher("DescargaArchivo/Download.jsp?archivo=" + filename + "&nombre=Reporte de Usuario con mas ventas Generado").forward(request, response);
                } else {
                    response.sendRedirect("muebleria/ErrorAdmin.jsp?error=Error al obtener datos");
                }

            }

        } catch (MiMuebleriaException | IOException | SQLException | ServletException e) {
            response.sendRedirect("muebleria/ErrorAdmin.jsp?error=Error AL Generar reporte ");
        }

    }
}
