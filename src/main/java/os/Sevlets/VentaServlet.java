/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Sevlets;

import os.Clases.*;
import os.Config.*;
import os.Controladores.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "VentasFormServlet", urlPatterns = {"/VentaServlet"})
public class VentaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoMueble = request.getParameter("tipoMuebleVenta");
        String nitVenta = request.getParameter("nitClienteVenta");
        String username=request.getParameter("user");
        try {
            if (tipoMueble != null && nitVenta != null && !nitVenta.contains("-")) {
                if (nitExistente(nitVenta)) {
                    if (muebleExistente(tipoMueble)) {
                        Venta venta = new Venta(tipoMueble, nitVenta);
                        PreparedStatement insertVenta = (PreparedStatement) Conexion.conexion().prepareStatement("INSERT INTO venta(mueble,nitVenta,fecha,user) VALUES(?,?,?,?)");
                        insertVenta.setString(1, venta.getMueble());
                        insertVenta.setString(2, venta.getNitVenta());
                        insertVenta.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                        insertVenta.setString(4, username);
                        insertVenta.execute();
                        response.sendRedirect("ventasJsp.jsp?user="+username);
                    } else {
                        response.sendRedirect("muebleria/ErroresVentas.jsp?user="+username+"&error=Mueble No existe");
                    }
                } else {
                    response.sendRedirect("muebleria/ErroresVentas.jsp?user="+username+"&error=Nit no existente");
                }
            } else {
                response.sendRedirect("muebleria/ErroresVentas.jsp?user="+username+"&error=Nit contiene -");
            }
        } catch (SQLException | MiMuebleriaException ex) {
            response.sendRedirect("muebleria/ErroresVentas.jsp?user="+username+"&error=Erro al procesar Venta");
        }
    }

    public boolean nitExistente(String nit) {
        boolean nitExist = false;
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM cliente WHERE nit='" + nit + "'");
            ResultSet set = consulta.executeQuery();
            nitExist = set.next();
        } catch (MiMuebleriaException | SQLException ex) {
            Logger.getLogger(VentaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nitExist;
    }

    public boolean muebleExistente(String mueble) {
        boolean muebleExist = false;
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT  mueble FROM ensamble_mueble a WHERE NOT EXISTS (SELECT NULL FROM venta b WHERE a.mueble=b.mueble)");
            ResultSet result = consulta.executeQuery();
            while (result.next()) {
                if (result.getString(1).equals(mueble)) {
                    muebleExist = true;
                }
            }
        } catch (MiMuebleriaException | SQLException e) {
        }
        return muebleExist;
    }
}
