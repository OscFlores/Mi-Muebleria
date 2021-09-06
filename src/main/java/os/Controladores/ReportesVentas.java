/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Controladores;

import os.Config.*;
import os.Clases.*;
import os.Sevlets.*;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "ReportesVentas", urlPatterns = {"/ReportesVentas"})
public class ReportesVentas extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filename = "ReporteVentas.csv";
        String fechaInicial = request.getParameter("fechaInicialReporteVenta");
        String fechFinal = request.getParameter("fechaFinalReporteVenta");
        try {
            FileWriter fw = new FileWriter(new File(filename));
            try {
                if (!fechaInicial.isEmpty() && !fechFinal.isEmpty()) {
                    PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT a.mueble,b.precio FROM venta a JOIN mueble b ON(a.mueble=b.nombreMueble) AND a.fecha>='" + fechaInicial + "' AND a.fecha<='" + fechFinal + "';");
                    ResultSet result = consulta.executeQuery();
                    fw.append("Mueble");
                    fw.append(",");
                    fw.append("Costo");
                    fw.append("\n");
                    while (result.next()) {
                        fw.append(result.getString(1));
                        fw.append(",");
                        fw.append(result.getString(2));
                        fw.append("\n");
                    }
                    fw.flush();
                    fw.close();
                    request.getRequestDispatcher("DescargaArchivo/Download.jsp?archivo=" + filename + "&nombre=Reporte de Ventas Generado").forward(request, response);
                } else {
                    PreparedStatement consulta1 = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT a.mueble,b.precio FROM venta a JOIN mueble b ON(a.mueble=b.nombreMueble);");
                    ResultSet result1 = consulta1.executeQuery();
                    fw.append("Mueble");
                    fw.append(",");
                    fw.append("Costo");
                    fw.append("\n");
                    while (result1.next()) {
                        fw.append(result1.getString(1));
                        fw.append(",");
                        fw.append(result1.getString(2));
                        fw.append("\n");
                    }
                    fw.flush();
                    fw.close();
                    request.getRequestDispatcher("DescargaArchivo/Download.jsp?archivo=" + filename + "&nombre=Reporte de Ventas Generado").forward(request, response);
                }
            } catch (MiMuebleriaException | SQLException e) {
                response.sendRedirect("muebleria/ErrorAdmin.jsp?error=Error al Escribrir datos archivo");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            response.sendRedirect("muebleria/ErrorAdmin.jsp?error=No se encuentra el archivo!");
        }
    }

}
