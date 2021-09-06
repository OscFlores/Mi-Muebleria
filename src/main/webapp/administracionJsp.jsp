
<%@page import="os.Config.Conexion"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    ResultSet result;
%>
<%
    PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM usuario WHERE estado=1");
    result = consulta.executeQuery();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css1/administracioncss.css" rel="stylesheet"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <title>Area de Administracion|Mi Muebleria</title>
    </head>
    <body>
        <header>
            Area Financiera y Administracion
        </header>
        <div class="logout">
            <ul class="cerrarSesion">
                <li><a href="CerrarSesion">Cerrar Sesion</a></li>
            </ul>
        </div>
        <div class="financiero">
            <nav class="navegacion">
                <ul class="menu">
                    <li><a href="#">Reporte del mueble más vendido</a>
                        <form  id="form" class="form-control" method="POST" action="" style="height: 200px;width: 300px;">
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicioGanancias" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalGanancias" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger" value="Generar Reporte">
                        </form>
                    </li>
                    <li><a href="#">Reporte del mueble menos vendido</a>
                        <form  id="form" class="form-control" method="POST" action="" style="height: 200px;width: 300px;">
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicioGanancias" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalGanancias" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger" value="Generar Reporte">
                        </form>
                    </li>
                    <li><a href="#">Reporte de ventas en un intervalo de Tiempo</a>
                        <form id="form" class="form-control" method="POST" action="ReportesVentas" style="height: 200px;width: 300px;">
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicialReporteVenta" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalReporteVenta" placeholder="Fecha">
                            <br>
                            <span>
                            <input type="submit" class="btn btn-danger"  value="Generar Reporte">
                        </form>
                    </li>
                    <li><a href="#">Reporte de devoluciones en un intervalo de tiempo</a>
                        <form id="form" class="form-control" method="POST" action="ReportesDevolucionServlet" style="height: 200px;width: 300px;"> 
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicioReporteDevoluciones" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalReporteDevoluciones" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger" value="Generar Reporte">
                        </form>
                    </li>
                    <li><a href="#">Reporte de ganancias en un intervalo de tiempo</a>
                        <form  id="form" class="form-control" method="POST" action="" style="height: 200px;width: 300px;">
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicioGanancias" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalGanancias" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger" value="Generar Reporte">
                        </form>
                    </li>
                    <li><a href="#">Reporte del usuario que registra más ventas en un intervalo de tiempo</a>
                        <form  id="form" class="form-control" method="POST" action="ReporteUsuarioMasVentas" style="height: 200px ;width: 300px;">
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicioMasVentas" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalMasVentas" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger" value="Generar Reporte">
                        </form>
                    </li>
                    <li><a href="#">Reporte del usuario que registra más ganancias en un intervalo de tiempo</a>
                        <form  id="form" class="form-control"  method="POST" action="" style="height: 200px;width: 300px;">
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicioGanancias" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalGanancias" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger" value="Generar Reporte">
                        </form>
                    </li>

                </ul>
            </nav>
        </div>
        <div class="container">
            <form method="POST" action="CrearMuebleServlet" class="form-control" style="margin-top:10%; width: 400px; height: 250px; position: absolute">
                <h3>Crear Mueble</h3>
                Nombre:
                <input class="form-control" type="text" name="nombreMueble" placeholder="Nombre De Mueble" required/>
                Precio de Venta
                <input class="form-control" type="number" step="0.01" name="precioMueble" placeholder="Precio de Venta" required/>  
                <br>
                <input class="btn btn-danger btn-small" type="submit" value="Crear Mueble"/>
            </form>
        </div>
        <div class="container">
            <form method="POST" action="CrearUsuarioServlet" class="form-control" style="margin-top:17%; width: 500px; height: 300px; margin-left: 40%;">
                <h3>Crear Usuario</h3>
                Username:
                <input class="form-control" type="text" name="userNew" placeholder="Username" required>
                Password:
                <input class="form-control" type="password" name="password" placeholder="Password" required/>
                Seleccion el Tipo:
                <select name="tipoUser" class="form-control">
                    <option value="1">Area de Fabrica</option>
                    <option value="2">Area de ventas</option>
                    <option value="3">Area de Administracion</option>
                </select>
                <br>
                <input type="submit" value="Crear Usuario" class="btn btn-danger btn-small">
            </form>
        </div>
        <div class="container">
            <form method="POST" action="CancelarUsuarioServlet" class="form-control" style=" margin-top:0; width: 400px; height: 200px; ">
                <h3>Cancelar Usuario</h3>
                Seleccione Usuario:
                <select class="form-control" name="usuarioCancelar">
                    <%
                        if (result != null) {
                            while (result.next()) {
                    %>
                    <option value="<%=result.getString(1)%>"><%=result.getString(1)%>, Area:<%=result.getString(3)%></option>
                    <%
                            }
                        }
                    %>
                </select>
                <br>
                <input type="submit" class="btn btn-danger btn-small" value="Cancelar Usuario">
            </form>
        </div>
        <div class="container" >
            <form class="form-control" method="POST" action="CargaArchivo_Servlet" style=" margin: auto;margin-top: 5%;width: 500px; height: 150px;" enctype="multipart/form-data">
                <input class="form-control" type="file" name="fileCarga" required>
                <br>
                <input class="btn btn-danger" type="submit" value="Enviar Archivo">
            </form>
        </div>
    </body>
</html>
