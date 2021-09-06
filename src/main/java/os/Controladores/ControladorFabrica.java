package os.Controladores;

import os.Clases.*;
import os.Modelos.*;
import os.Config.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorFabrica {
    
    public void fabricaAccionesGET(HttpServletRequest request, HttpServletResponse response) throws Excepciones {
        String accionFabrica = request.getParameter("accionFabrica");
        
        try {
            switch (accionFabrica) {
                case "editarTipoPieza":
                    editarTipoPieza(request, response);
                    break;
                case "eliminarTipoPieza":
                    eliminarTipoPieza(request, response);
                    break;
                case "agregarTipoPieza":
                    request.getRequestDispatcher("/WEB-INF/paginas/fabrica/agregarTipoPieza.jsp").forward(request, response);
                    break;
                case "inventario":
                    this.mostrarTodasLasPiezas(request, response);
                    break;
                case "editarPieza":
                    editarPieza(request, response);
                    break;
                case "eliminarPieza":
                    eliminarPieza(request, response);
                    break;
                case "agregarPieza":
                    redireccionarParaAgregarPieza(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new Excepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }
    
    public void ensamblarMueble(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String tipoMueble = request.getParameter("idMuebleEnsamblar");
        String usuarioEnsamblador = request.getParameter("usuario");
        String fechaEnsamble = request.getParameter("fechaEnsamble");
        
        EnsamblePiezaDAO ensamblePiezaDao = new EnsamblePiezaDAO();
        EnsamblePieza modeloEnsamblePieza = new EnsamblePieza(tipoMueble);
        ArrayList<EnsamblePieza> requerimientos = new ArrayList<>(ensamblePiezaDao.Listar(modeloEnsamblePieza));
        
        InfoPiezaDAO tipoPiezaDao = new InfoPiezaDAO();
        
        if (requerimientos.isEmpty()) {
            throw new Excepciones("No es posible ensamblar el mueble, ya que no se han definido sus piezas");
        }
        
        for (EnsamblePieza ep : requerimientos) {
            InfoPieza modeloTipoPieza = new InfoPieza(ep.getIdTipoPieza());
            modeloTipoPieza = tipoPiezaDao.EncontrarporId(modeloTipoPieza);
            if (ep.getCantidad() > modeloTipoPieza.getCantidad()) {
                throw new Excepciones("No tienes suficientes piezas de " + modeloTipoPieza.getNombre() + " para ensamblar este mueble");
            }
        }
        
        PiezaDAO piezaDao = new PiezaDAO();
        ArrayList<Integer> idPiezasUsadas = new ArrayList<>();
        double costo = 0;
        for (int i = 0; i < requerimientos.size(); i++) {
            for (int j = 0; j < requerimientos.get(i).getCantidad(); j++) {
                Pieza modelo = new Pieza();
                modelo.setIdTipoPieza(requerimientos.get(i).getIdTipoPieza());
                modelo = piezaDao.encotrarNoUsadosByIdTipoPieza(modelo);
                int idPieza = modelo.getIdPieza();
                idPiezasUsadas.add(idPieza);
                piezaDao.usarPieza(idPieza);
                tipoPiezaDao.usarPieza(modelo.getIdTipoPieza());
                costo += modelo.getCosto();
            }
        }
        
        EnsambleMuebleDAO ensambleMuebleDao = new EnsambleMuebleDAO();
        EnsambleMueble ensambleMueble = new EnsambleMueble(tipoMueble, usuarioEnsamblador, fechaEnsamble, costo);
        ensambleMuebleDao.Insertar(ensambleMueble);   
        int idEnsamble = ensambleMuebleDao.obtenerIdUltimoEnviado();
        ArmarDAO armadoDao = new ArmarDAO();
        for (int i = 0; i < idPiezasUsadas.size(); i++) {
            armadoDao.Insertar(new Armar(idPiezasUsadas.get(i), idEnsamble));
        }
        this.produccion(request, response);
    }
    
    public void fabricaAccionesPost(HttpServletRequest request, HttpServletResponse response) throws Excepciones {
        String accionesFabrica = request.getParameter("accionFabrica");
        try {
            switch (accionesFabrica) {
                case "actualizarTipoPieza":
                    this.modificarTipoPieza(request, response);
                    break;
                case "agregarTipoPieza":
                    this.agregarTipoPieza(request, response);
                    break;
                case "actualizarPieza":
                    this.modificarPieza(request, response);
                    break;
                case "agregarPieza":
                    this.agregarPieza(request, response);
                case "ensamblarMueble":
                    ensamblarMueble(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new Excepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }
    
    public void fabricaPaginas(HttpServletRequest request, HttpServletResponse response) throws Excepciones {
        String paginaFabrica = request.getParameter("paginaFabrica");   
        if (paginaFabrica == null) {
            paginaFabrica = "inicio";
        }
        try {
            switch (paginaFabrica) {
                case "inicio":
                    inicio(request, response);
                    break;
                case "materiaPrima":
                    materiaPrima(request, response);
                    break;
                case "produccion":
                    produccion(request, response);
                    break;
                case "ensamble":
                    ensamble(request, response);
                    break;
                case "AgregarTipoPieza":
                    agregarTipoPieza(request, response);
                    break;
                default:
                
            }
        } catch (ServletException | IOException ex) {
            throw new Excepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }
    
    private void redireccionarParaAgregarPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        List<InfoPieza> tipoPiezas = new InfoPiezaDAO().Listar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("tipoPiezas", tipoPiezas);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/agregarPieza.jsp").forward(request, response);
    }
    
    private void agregarPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String tipoPieza = request.getParameter("tipoPieza");
        double precio = Double.valueOf(request.getParameter("precioPieza"));
        
        InfoPiezaDAO tipoPiezaDao = new InfoPiezaDAO();
        InfoPieza modelotipoPieza = tipoPiezaDao.Encontrar(new InfoPieza(tipoPieza));
        new PiezaDAO().Insertar(new Pieza(modelotipoPieza.getIdTipoPieza(), precio));
        tipoPiezaDao.agregarPieza(modelotipoPieza);
        this.mostrarTodasLasPiezas(request, response);
    }
    
    private void modificarPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        int idPieza = Integer.valueOf(request.getParameter("idPieza"));
        Double precioPieza = Double.valueOf(request.getParameter("precioPieza"));
        
        Pieza modeloPieza = new Pieza();
        modeloPieza.setIdPieza(idPieza);
        modeloPieza.setCosto(precioPieza);
        new PiezaDAO().Actualizar(modeloPieza);
        this.mostrarTodasLasPiezas(request, response);
    }
    
    private void agregarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        String nombrenuevoTipoPieza = request.getParameter("nombreNuevoTipoPieza");
        
        InfoPiezaDAO tipoPiezaDao = new InfoPiezaDAO();
        
        if (tipoPiezaDao.existe(nombrenuevoTipoPieza)) {
            throw new Excepciones("Esta pieza ya existe");
        }
        
        tipoPiezaDao.Insertar(new InfoPieza(nombrenuevoTipoPieza));
        this.materiaPrima(request, response);
    }
    
    private void modificarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        int idTipoPieza = Integer.valueOf(request.getParameter("idTipoPieza"));
        String nombreTipoPieza = request.getParameter("nombreTipoPieza");
        
        InfoPiezaDAO tipoPiezaDao = new InfoPiezaDAO();
        
        if (tipoPiezaDao.existe(nombreTipoPieza)) {
            throw new Excepciones("Esta pieza ya existe");
        }
        
        InfoPieza modeloTipoPieza = new InfoPieza(idTipoPieza, nombreTipoPieza);
        
        tipoPiezaDao.ActualizarNombre(modeloTipoPieza);
        this.materiaPrima(request, response);
    }
    
    private void mostrarTodasLasPiezas(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        
        List<Pieza> piezas = new PiezaDAO().Listar();
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("todasLasPiezas", piezas);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/infoPiezas.jsp").forward(request, response);
    }
    
    private void eliminarPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        int idPieza = Integer.valueOf(request.getParameter("idPieza"));
        Pieza modeloPieza = new Pieza(idPieza);
        PiezaDAO piezaDao = new PiezaDAO();
        modeloPieza = piezaDao.Encontrar(modeloPieza);
        piezaDao.Eliminar(modeloPieza);
        new InfoPiezaDAO().quitarPieza(new InfoPieza(modeloPieza.getIdTipoPieza()));
        mostrarTodasLasPiezas(request, response);
    }
    
    private void editarPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        int idPieza = Integer.valueOf(request.getParameter("idPieza"));
        Pieza modeloPieza = new PiezaDAO().Encontrar(new Pieza(idPieza));
        request.setAttribute("modeloPieza", modeloPieza);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/editarPieza.jsp").forward(request, response);
    }
    
    private void editarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        int idTipoPieza = Integer.valueOf(request.getParameter("idTipoPieza"));
        InfoPieza modeloTipoPieza = new InfoPiezaDAO().EncontrarporId(new InfoPieza(idTipoPieza));
        request.setAttribute("modeloTipoPieza", modeloTipoPieza);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/editarTipoPieza.jsp").forward(request, response);
    }
    
    private void eliminarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws Excepciones, ServletException, IOException {
        int idTipoPieza = Integer.valueOf(request.getParameter("idTipoPieza"));
        InfoPiezaDAO tipoPiezaDao = new InfoPiezaDAO();
        InfoPieza tipoPieza = new InfoPieza(idTipoPieza);
        PiezaDAO piezaDao = new PiezaDAO();
        piezaDao.eleminarSegunTipoPieza(idTipoPieza);
        tipoPiezaDao.Deshabilitar(tipoPieza);
        this.materiaPrima(request, response);
    }
    
    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, Excepciones, IOException {
        List<InfoPieza> piezasPorAgotar = new InfoPiezaDAO().listarPiezasPorAgotar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("piezasPorAgotar", piezasPorAgotar);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/fabrica.jsp").forward(request, response);
    }
    
    private void materiaPrima(HttpServletRequest request, HttpServletResponse response) throws ServletException, Excepciones, IOException {
        String orden = request.getParameter("orden");
        
        List<InfoPieza> tipoPiezas;
        if (orden != null) {
            if (orden.equals("desc")) {
                tipoPiezas = new InfoPiezaDAO().Listar(true);
            } else {
                tipoPiezas = new InfoPiezaDAO().Listar(false);
            }
        } else {
            tipoPiezas = new InfoPiezaDAO().Listar(false); 
        }
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("tipoPiezasDisponibles", tipoPiezas);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/materiaPrima.jsp").forward(request, response);
        
    }
    
    private void produccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, Excepciones, IOException {
        String orden = request.getParameter("orden");
        
        List<EnsambleMueble> ensamblesMueble;
        if (orden != null) {
            if (orden.equals("desc")) {
                ensamblesMueble = new EnsambleMuebleDAO().Listar(true);
            } else {
                ensamblesMueble = new EnsambleMuebleDAO().Listar(false);
            }
        } else {
            ensamblesMueble = new EnsambleMuebleDAO().Listar(true); 
        }
        for (int i = 0; i < ensamblesMueble.size(); i++) {
            int idEnsamble = ensamblesMueble.get(i).getIdEnsamble();
            List<Armar> armados = new ArmarDAO().listarSegunIdEnsamble(idEnsamble);
            ensamblesMueble.get(i).setPiezasUsadas(armados);
        }
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("ensamblesMueble", ensamblesMueble);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/produccion.jsp").forward(request, response);
    }
    
    private void ensamble(HttpServletRequest request, HttpServletResponse response) throws ServletException, Excepciones, IOException {
        List<Mueble> muebles = new MuebleDAO().Listar();
        
        for (int i = 0; i < muebles.size(); i++) {
            String nombreMueble = muebles.get(i).getNombre();
            List<EnsamblePieza> requerimientos = new EnsamblePiezaDAO().listarSegunTipoMueble(nombreMueble);
            muebles.get(i).setRequerimientos(requerimientos);
        }
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("recetas", muebles);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/ensamble.jsp").forward(request, response);
    }
    
}
