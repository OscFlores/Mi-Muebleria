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
import static java.lang.System.out;
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
@WebServlet(name = "Usuario_Servlet", urlPatterns = {"/usuario-servlet"})
public class Usuario_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DBUsuario dbUsuario = new DBUsuario();
        try {
            Usuario usuario = dbUsuario.Validar(username, password);
            if (usuario != null) {
                int num = ValidacionUsuario.validar(usuario);
                if (num == 1) {
                    response.sendRedirect("fabricajsp.jsp?user=" + username);
                } else if (num == 2) {
                    response.sendRedirect("ventasJsp.jsp?user="+username);
                } else if (num == 3) {
                    response.sendRedirect("administracionJsp.jsp?user="+username);
                } else {
                    response.sendRedirect("muebleria/ErrorLogin.jsp?error=Username o Password Incorrectos");
                }
            } else {
                response.sendRedirect("muebleria/ErrorLogin.jsp?error=Username o Password Incorrectos");
            }
        } catch (MiMuebleriaException | SQLException ex) {
            response.sendRedirect("muebleria/ErrorLogin.jsp?erro=Username o Password Incorrectos");
        }
    }
}
