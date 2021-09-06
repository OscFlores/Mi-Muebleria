/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Controladores;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path=request.getParameter("path");
        try (BufferedInputStream in=new BufferedInputStream(new FileInputStream(path))) {
            int data = in.read();
            response.setContentType("text/csv");    
            response.setHeader("Content-Diposition", "attachment; filename="+path);
            while (data > -1) {
                response.getOutputStream().write(data);
                data = in.read();
            }
        } catch (Exception e) {
            response.sendRedirect("muebleria/ErroAdmin.jsp?error=Error al descargar archivo");
        }
    }
}
