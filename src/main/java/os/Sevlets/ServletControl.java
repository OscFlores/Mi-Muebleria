package os.Sevlets;

import os.Config.*;
import os.Modelos.*;
import os.Controladores.*;
import os.Clases.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletControlador")
public class ServletControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaFabrica = request.getParameter("paginaFabrica");
        String accion = request.getParameter("accion");
        String accionFabrica = request.getParameter("accionFabrica");
        String paginaVentas = request.getParameter("paginaVenta");
        String consultaVenta = request.getParameter("consultaVenta");
        String paginasAdministracion = request.getParameter("paginaAdministracion");
        String consultasAdministracion = request.getParameter("consultasAdministracion");
        String accionAdministracion = request.getParameter("accionAdministracion");
        
        try {
            if (paginaFabrica == null && accion == null && accionFabrica == null && paginaVentas == null
                    && consultaVenta == null && paginasAdministracion == null
                    && consultasAdministracion == null && accionAdministracion == null) {
                response.sendRedirect("publicas/login.jsp");
            } else if (paginaFabrica != null) {
                ControladorFabrica controladorFabrica = new ControladorFabrica();
                controladorFabrica.fabricaPaginas(request, response);
            } else if (paginaVentas != null) {
                ControladorVentas controladorVentas = new ControladorVentas();
                controladorVentas.ventasPaginas(request, response);
            }else if (consultaVenta != null) {
                ControladorVentas controladorVentas = new ControladorVentas();
                controladorVentas.consultasGet(request, response);
            }else if (paginasAdministracion != null) {
                ControladorAdministracion controladorAdmin = new ControladorAdministracion();
                controladorAdmin.administracionPaginas(request, response);
            }else if (consultasAdministracion != null) {
                ControladorAdministracion controladorAdmin = new ControladorAdministracion();
                controladorAdmin.administracionConsultasGet(request, response);
            }else if (accionAdministracion != null) {
                ControladorAdministracion controladorAdmin = new ControladorAdministracion();
                controladorAdmin.administracionAccionesGet(request, response);
            } else if (accion != null) {
                switch (accion) {
                    case "logOut":
                        this.logOut(request, response);
                        break;
                }
            } else if (accionFabrica != null) {
                ControladorFabrica controladorFabrica = new ControladorFabrica();
                controladorFabrica.fabricaAccionesGET(request, response);
            }
        } catch (Excepciones ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/paginas/comunes/paginaErrores.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String accionFabrica = request.getParameter("accionFabrica");
        String accionVentas = request.getParameter("accionVentas");
        String accionAdministracion = request.getParameter("accionAdministracion");
        
        try {
            if (accion != null) {
                switch (accion) {
                    case "validarUsuario":
                        this.validarUsuario(request, response);

                        break;
                    default:
                        response.sendRedirect("publicas/login.jsp");
                }
            } else if (accionFabrica != null) {
                ControladorFabrica controladorFabrica = new ControladorFabrica();
                controladorFabrica.fabricaAccionesPost(request, response);
            }else if (accionVentas != null ) {
                ControladorVentas controladorVentas = new ControladorVentas();
                controladorVentas.ventasAccionesPost(request, response);
            }else if (accionAdministracion != null ) {
                ControladorAdministracion controladorAdmin = new ControladorAdministracion();
                controladorAdmin.administracionAccionesPost(request, response);
            }
        } catch (Excepciones ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/paginas/comunes/paginaErrores.jsp").forward(request, response);
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws Excepciones {
        try {
            HttpSession sesion = request.getSession(true);
            sesion.removeAttribute("nombreUsuario");
            sesion.removeAttribute("password");
            sesion.invalidate();
            response.sendRedirect("publicas/login.jsp");
        } catch (IOException ex) {
            throw new Excepciones("Ocurrio un error al intentar cerrar la sesion");
        }
    }

    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excepciones {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String passw = request.getParameter("password");
        int tipo;

        //Construccion de modelo
        Usuario usuario = new Usuario(nombreUsuario);

        //Buscamos si sus datos coinciden
        UsuarioDAO usuarioDao = new UsuarioDAO();
        if (!usuarioDao.Existe(nombreUsuario)) {
            throw new Excepciones("El usuario no existe");
        }
        
        if (usuarioDao.estaDeshabilitado(nombreUsuario)) {
            throw new Excepciones("A este usuario se le a denegado el acceso al sistema");
        }

        usuario = new UsuarioDAO().Encontrar(usuario);

        if (!usuario.getPassword().equals(passw)) {
            throw new Excepciones("Contrasena incorrecta");
        }

        tipo = usuario.getTipo();

        HttpSession sesion = request.getSession();
        sesion.setAttribute("nombreUsuario", nombreUsuario);
        sesion.setAttribute("tipo", tipo);

        try {
            switch (tipo) {
                case 3:
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/administracion.jsp").forward(request, response);
                    break;
                case 1: {
                    ControladorFabrica controladorFabrica = new ControladorFabrica();
                    controladorFabrica.fabricaPaginas(request, response);
                }
                break;
                case 2:
                    request.getRequestDispatcher("/WEB-INF/paginas/venta/ventas.jsp").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new Excepciones("Ocurrio un error al intentar mostrar la pagina");
        }

    }
}
