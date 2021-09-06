package os.Controladores;

import os.Clases.*;
import os.Config.*;
import os.Modelos.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorAdministracion {

    public void administracionAccionesGet(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String accionAdministracion = request.getParameter("accionAdministracion");

        switch (accionAdministracion) {
            case "eliminarUsuario":
                this.eliminarUsuario(request, response);
                break;
            case "agregarUsuario":
                this.mostrarAgregarUsuario(request, response);
                break;
            case "editarMueble":
                this.mostrarEditarMueble(request, response);
                break;
            case "agregarMueble":
                this.mostrarAgregarMueble(request, response);
                break;
            case "agregarRequistoMueble":
                this.mostrarAgregarRequisitoMueble(request, response);
                break;
        }
    }

    private void mostrarEditarMueble(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String nombreMueble = request.getParameter("nombreMueble");

        Mueble mueble = new MuebleDAO().Encontrar(new Mueble(nombreMueble));
        request.setAttribute("mueble", mueble);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/editar-mueble.jsp").forward(request, response);
    }

    private void mostrarAgregarMueble(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/agregar-mueble.jsp").forward(request, response);
    }

    private void mostrarAgregarRequisitoMueble(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        List<Mueble> muebles = new MuebleDAO().Listar();
        List<InfoPieza> tipoPiezas = new InfoPiezaDAO().Listar();

        request.setAttribute("muebles", muebles);
        request.setAttribute("tipoPiezas", tipoPiezas);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/agregar-requerimiento-mueble.jsp").forward(request, response);
    }

    private void mostrarAgregarUsuario(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/agregar-usuario.jsp").forward(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        new UsuarioDAO().deshabilitar(nombreUsuario);
        this.mostrarUsuarios(request, response);
    }

    public void administracionPaginas(HttpServletRequest request, HttpServletResponse response) throws Excepciones {
        String paginasAdministracion = request.getParameter("paginaAdministracion");

        if (paginasAdministracion == null) {
            paginasAdministracion = "inicio";
        }

        try {
            switch (paginasAdministracion) {
                case "inicio":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/administracion.jsp").forward(request, response);
                    break;
                case "creacionMuebles":
                    this.mostrarMuebles(request, response);
                    break;
                case "manejoUsuarios":
                    this.mostrarUsuarios(request, response);
                    break;
                case "cargarDatos":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/cargar-datos.jsp").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new Excepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    private void mostrarMuebles(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        List<Mueble> muebles = new MuebleDAO().Listar();
        request.setAttribute("muebles", muebles);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/creacion-muebles.jsp").forward(request, response);
    }

    private void mostrarUsuarios(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        List<Usuario> usuarios = new UsuarioDAO().Listar();

        for (Usuario u : usuarios) {
            int Tipo = u.getTipo();
            u.setTipo(Tipo);
        }

        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/manejo-usuarios.jsp").forward(request, response);
    }

    public void administracionAccionesPost(HttpServletRequest request, HttpServletResponse response) throws Excepciones {
        String accionAdministracion = request.getParameter("accionAdministracion");
        try {
            switch (accionAdministracion) {
                case "reporteVentas":
                    this.reporteVentas(request, response);
                    break;
                case "reporteDevoluciones":
                    this.reporteDevoluciones(request, response);
                    break;
                case "reporteGanancias":
                    this.reporteGanancias(request, response);
                    break;
                case "reporteUsuarioMasVentas":
                    this.reporteUsuarioMasVentas(request, response);
                    break;
                case "reporteUsuarioMasGanancias":
                    this.reporteUsuarioMasGanancias(request, response);
                    break;
                case "reporteMuebleMasVendido":
                    this.reporteMuebleMasVendido(request, response);
                    break;
                case "reporteMuebleMenosVendido":
                    this.reporteMuebleMenosVendido(request, response);
                    break;
                case "editarMueble":
                    this.editarMueble(request, response);
                case "agregarMueble":
                    this.agregarMueble(request, response);
                case "agregarRequerimiento":
                    this.agregarRequerimiento(request, response);
            }
        } catch (ServletException | IOException ex) {
            ex.printStackTrace(System.out);
            throw new Excepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    private void agregarRequerimiento(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String mueble = request.getParameter("mueble");
        String tipoPieza = request.getParameter("tipoPieza");
        int cantidad = 0;

        try {
            cantidad = Integer.valueOf(request.getParameter("cantidad"));
        } catch (NumberFormatException ex) {
            throw new Excepciones("Ingresa una cantidad valida");
        }

        if (cantidad < 1) {
            throw new Excepciones("Ingresa una cantidad valida");
        }

        EnsamblePiezaDAO ensamblePiezadao = new EnsamblePiezaDAO();
        if (ensamblePiezadao.existe(mueble, tipoPieza)) {
            ensamblePiezadao.sobrescribirCantidadRequerimiento(cantidad, mueble, tipoPieza);
        } else {
            InfoPieza tPieza = new InfoPiezaDAO().Encontrar(new InfoPieza(tipoPieza));
            int idPieza = tPieza.getIdTipoPieza();
            new EnsamblePiezaDAO().Insertar(new EnsamblePieza(mueble, idPieza, cantidad));
        }

        this.mostrarMuebles(request, response);
    }

    private void agregarMueble(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String nombreNuevoMueble = request.getParameter("nombreNuevoMueble");
        double precio = Double.valueOf(request.getParameter("precio"));

        if (nombreNuevoMueble.isBlank()) {
            throw new Excepciones("Por favor ingresa un nombre valido");
        }

        if (new MuebleDAO().Existe(nombreNuevoMueble)) {
            throw new Excepciones("Este mueble ya existe");
        }

        if (precio < 0) {
            throw new Excepciones("Por favor ingresa un precio valido");
        }

        new MuebleDAO().Insertar(new Mueble(nombreNuevoMueble, precio));

        this.mostrarMuebles(request, response);
    }

    private void editarMueble(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String nombreMueble = request.getParameter("nombreMueble");
        double precio = Double.valueOf(request.getParameter("precio"));

        if (precio < 0) {
            throw new Excepciones("Por favor ingresa un precio valido");
        }

        new MuebleDAO().Actualizar(new Mueble(nombreMueble, precio));

        this.mostrarMuebles(request, response);
    }

    public void administracionConsultasGet(HttpServletRequest request, HttpServletResponse response) throws Excepciones {
        String consultasAdministracion = request.getParameter("consultasAdministracion");

        try {
            switch (consultasAdministracion) {
                case "reporteVentas":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ventas.jsp").forward(request, response);
                    break;
                case "reporteDevoluciones":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-devoluciones.jsp").forward(request, response);
                    break;
                case "reporteGanancias":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ganancias.jsp").forward(request, response);
                    break;
                case "reporteUsuarioMasVentas":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ventas.jsp").forward(request, response);
                    break;
                case "reporteUsuarioMasGanancias":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ganancias.jsp").forward(request, response);
                    break;
                case "reporteMuebleMasVendido":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-mas-vendido.jsp").forward(request, response);
                    break;
                case "reporteMuebleMenosVendido":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-menos-vendido.jsp").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new Excepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    private void reporteVentas(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultaAdministracion> registros = new ArrayList<>();
        registros = new ConsultaAdministracionDAO().obtenerReporteVentas(fechaInicial, fechaFinal);

        double totalVendido = 0;
        for (ConsultaAdministracion r : registros) {
            totalVendido += r.getPrecioVenta();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.setAttribute("cantidadVentas", registros.size());
        request.setAttribute("cantidadTotalVentas", df.format(totalVendido));
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ventas.jsp").forward(request, response);
    }

    private void reporteDevoluciones(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultaAdministracion> registros = new ArrayList<>();
        registros = new ConsultaAdministracionDAO().obtenerReporteDevoluciones(fechaInicial, fechaFinal);

        int numDevoluciones = registros.size();

        double totalPerdida = 0;
        for (ConsultaAdministracion r : registros) {
            totalPerdida += r.getPerdida();
        }
        
        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.setAttribute("numDevoluciones", numDevoluciones);
        request.setAttribute("totalPerdida", df.format(totalPerdida));
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-devoluciones.jsp").forward(request, response);
    }

    private void reporteGanancias(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultaAdministracion> registros = new ArrayList<>();
        registros = new ConsultaAdministracionDAO().obtenerReporteGanancias(fechaInicial, fechaFinal);

        double gananciaTotal = 0;
        for (ConsultaAdministracion r : registros) {
            gananciaTotal += r.getGanancia();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("cantidadVentas", registros.size());
        request.setAttribute("gananciaTotal", df.format(gananciaTotal));
        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ganancias.jsp").forward(request, response);
    }

    private void reporteUsuarioMasVentas(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultaAdministracion> registros = new ArrayList<>();
        String vendedor = new ConsultaAdministracionDAO().obtenerUsarioConMasVentas(fechaInicial, fechaFinal);
        if (vendedor == null) {
            throw new Excepciones("Aun no se han registrado ventas entre este intervalo");
        }
        registros = new ConsultaAdministracionDAO().obtenerReporteUsarioConMasVentas(fechaInicial, fechaFinal, vendedor);

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("vendedor", vendedor);
        request.setAttribute("totalVentas", registros.size());
        request.setAttribute("registros", registros);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ventas.jsp").forward(request, response);
    }

    private void reporteUsuarioMasGanancias(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultaAdministracion> registros = new ArrayList<>();
        String vendedor = new ConsultaAdministracionDAO().obtenerUsuarioConMasGanancias(fechaInicial, fechaFinal);

        if (vendedor == null) {
            throw new Excepciones("Aun no se han registrado ventas entre este intervalo");
        }

        registros = new ConsultaAdministracionDAO().obtenerReporteUsarioConMasGanancias(fechaInicial, fechaFinal, vendedor);

        double totalGanancias = 0;
        for (ConsultaAdministracion g : registros) {
            totalGanancias += g.getGanancia();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("vendedor", vendedor);
        request.setAttribute("totalGanancias", df.format(totalGanancias));
        request.setAttribute("registros", registros);

        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ganancias.jsp").forward(request, response);
    }

    private void reporteMuebleMasVendido(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultaAdministracion> registros = new ArrayList<>();
        String nombreMueble = new ConsultaAdministracionDAO().obtenerMuebleMasVendido(fechaInicial, fechaFinal);

        if (nombreMueble == null) {
            throw new Excepciones("Aun no se han registrado ventas entre este intervalo");
        }

        registros = new ConsultaAdministracionDAO().reporteMuebleMasVendido(fechaInicial, fechaFinal, nombreMueble);

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("nombreMueble", nombreMueble);
        request.setAttribute("unidadesVendidas", registros.size());
        request.setAttribute("registros", registros);

        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-mas-vendido.jsp").forward(request, response);
    }

    private void reporteMuebleMenosVendido(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultaAdministracion> registros = new ArrayList<>();
        String nombreMueble = new ConsultaAdministracionDAO().obtenerMuebleMenosVendido(fechaInicial, fechaFinal);

        if (nombreMueble == null) {
            throw new Excepciones("Aun no se han registrado ventas entre este intervalo");
        }

        registros = new ConsultaAdministracionDAO().reporteMuebleMenosVendido(fechaInicial, fechaFinal, nombreMueble);

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("nombreMueble", nombreMueble);
        request.setAttribute("unidadesVendidas", registros.size());
        request.setAttribute("registros", registros);

        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-menos-vendido.jsp").forward(request, response);
    }

}
