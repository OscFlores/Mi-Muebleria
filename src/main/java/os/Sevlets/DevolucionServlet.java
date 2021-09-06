/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Sevlets;

import os.Clases.*;
import os.Config.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "DevolucionServlet", urlPatterns = {"/DevolucionServlet"})
public class DevolucionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String muebleDev = request.getParameter("tipoMuebleDevolucion");
        String nitDev = request.getParameter("nitClienteDevolucion");
        String username = request.getParameter("user");
        try {
            if (muebleDev != null && nitDev != null && !nitDev.contains("-")) {
                PreparedStatement consutltaDevolucion = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM venta WHERE mueble='" + muebleDev + "' AND fecha>=date_add(NOW(), INTERVAL -7 DAY)");
                ResultSet result = consutltaDevolucion.executeQuery();
                if (result.next()) {
                    PreparedStatement consultaPerdida = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM ensamble_mueble WHERE mueble='" + muebleDev + "';");
                    ResultSet result1 = consultaPerdida.executeQuery();
                    if (result1.next()) {
                        Double perdida = Double.valueOf(result1.getString(4)) / 3;
                        PreparedStatement insertDevolucion = (PreparedStatement) Conexion.conexion().prepareStatement("INSERT INTO devolucion(mueble,nitVenta,fecha,perdida) VALUES(?,?,?,?)");
                        insertDevolucion.setString(1, muebleDev);
                        insertDevolucion.setString(2, nitDev);
                        insertDevolucion.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                        insertDevolucion.setString(4, String.valueOf(perdida));
                        insertDevolucion.execute();
                        PreparedStatement deleteVenta = (PreparedStatement) Conexion.conexion().prepareStatement("DELETE FROM venta WHERE mueble='" + muebleDev + "' AND nitVenta='" + nitDev + "'");
                        deleteVenta.execute();
                        response.sendRedirect("ventasJsp.jsp?user=" + username);
                    } else {
                        response.sendRedirect("muebleria/ErroresVentas.jsp?user=" + username + "&error=mueble no encontrado");
                    }
                } else {
                response.sendRedirect("muebleria/ErroresVentas.jsp?user=" + username + "&error=mueble no encontrado");
                }
            } else {
                response.sendRedirect("muebleria/ErroresVentas.jsp?user=" + username + "&error=Nit Incorrecto");
            }

        } catch (MiMuebleriaException | SQLException ex) {
            response.sendRedirect("muebleria/ErroresVentas.jsp?user=" + username + "&error=Datos incorrectos");
        }
    }

}
