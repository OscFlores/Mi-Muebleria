
<%@page import="os.Config.Conexion"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    ResultSet resultMueble = null;
    ResultSet resultPieza = null;
    ResultSet resultEnsamblePieza=null;
    String username;
%>
<%
    username = request.getParameter("user");
    PreparedStatement consultaMueble = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT nombreMueble FROM mueble WHERE NOT EXISTS (SELECT NULL FROM ensamble_mueble b WHERE nombreMueble=b.mueble)");
    resultMueble = consultaMueble.executeQuery();
    PreparedStatement consultaPieza = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT DISTINCT tipo,costo FROM pieza");
    resultPieza = consultaPieza.executeQuery();
    PreparedStatement consultaEnsambleMueble = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT DISTINCT a.mueble FROM ensamble_pieza a WHERE NOT EXISTS(SELECT NULL FROM ensamble_mueble b WHERE a.mueble=b.mueble)");
    resultEnsamblePieza = consultaEnsambleMueble.executeQuery();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css1/fabricaCss.css" rel="stylesheet"/>
        <title>Area de Fabrica | Mi Muebleria</title>
    </head>
    <body>  
        <header>Area de Fabrica</header>
        <div class="logout">
            <ul class="cerrarSesion">
                <li><a href="CerrarSesion">Cerrar Sesion</a></li>
            </ul>
        </div>
        <div class="fabrica">
            <nav class="navegacion">
                <ul class="menu">   
                    <li id="cons"><a href="#">Consultar cantidad de Piezas</a>
                        <ul class="itemsConsulta">
                            <li><a href="Muebleria/consulta.jsp?consulta=mayorMenor&user=<%=username%>&tipoConsulta=Piezas Mayor A menor">Mayor a menor</a></li>
                            <li><a href="Muebleria/consulta.jsp?consulta=menorMayor&user=<%=username%>&tipoConsulta=Piezas Menor a Mayor">Menor a Mayor</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="Piezas_Servlet?user=<%=username%>">Piezas</a>
                    </li>
                    <li id="cons"><a href="#">Consultar Informacion de los muebles Creados</a>
                        <ul class="itemsConsulta">
                            <li><a href="Muebleria/consultaMueble.jsp?consulta=MueblemayorMenor&user=<%=username%>&tipoConsulta=Muebles Mayor a Menor">Mayor Fecha de Ensamble</a></li>
                            <li><a href="Muebleria/consultaMueble.jsp?consulta=MueblemenorMayor&user=<%=username%>&tipoConsulta=Muebles Menor a Mayor">Menor Fecha de Ensamble</a></li>
                        </ul>
                    </li>


                </ul>
            </nav>
        </div>

        <div class="container">
            <form id="ensamblar"class="form-control" method="POST"  action="EnsambleServlet?tipoEnsamble=piezas&user=<%=username%>" style="width: 500px; height: 280px;margin: auto;margin-top:13%">
                <h3>Ensamblar Piezas</h3>
                Tipo Mueble:
                <select class="form-control" name="tipoMueblePieza" >
                    <%
                        if (resultMueble != null) {
                            while (resultMueble.next()) {
                    %>
                    <option value="<%=resultMueble.getString(1)%>"  ><%=resultMueble.getString(1)%></option>
                    <%
                            }
                        }
                    %>
                </select>
                Tipo de Pieza
                <select class="form-control" name="tipoPieza">
                    <%
                        if (resultPieza != null) {
                            while (resultPieza.next()) {

                    %>
                    <option><%=resultPieza.getString(1)%></option>
                    <%}
                            }%>
                </select>
                Cantidad de Piezas:
                <input type="number" class="form-control" name="cantPiezas" placeholder="Cantidad de Piezas" required/>
                <br>
                <input type="submit" class="btn btn-danger btn-sm"value="Ensamblar Mueble">
            </form>

        </div>
        <div class="container">
            <form class="form-control" method="POST" action="EnsambleServlet?tipoEnsamble=mueble&user=<%=username%>" style="width: 500px; height: 300px;margin: auto;margin-top:2%">
                <h3>Ensamblar Mueble</h3>
                Tipo de Mueble (Ensamble de mueble requiere uno o mas ensambles de piezas):
                <select class="form-control" name="tipoMuebleEnsamble" >
                    <%
                        if (resultEnsamblePieza!= null) {
                            while (resultEnsamblePieza.next()) {
                    %>
                    <option value="<%=resultEnsamblePieza.getString(1)%>"><%=resultEnsamblePieza.getString(1)%></option>
                    <%
                            }
                        }
                    %>
                </select>
                Nombre de Usuario:
                <input type="text" class="form-control" readonly="" name="nombreUsuario" Value="<%=username%>" required>
                Fecha:
                <input type="date" class="form-control" name="fechaEnsamble" placeholder="Fecha de Ensamble" required>
                <br>
                <input type="submit" class="btn btn-danger btn-sm" value=" Ensamblar Mueble"/>
            </form>
        </div>

    </body>
</html>
