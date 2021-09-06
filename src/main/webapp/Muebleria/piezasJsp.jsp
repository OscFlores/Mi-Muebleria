
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="os.Clases.Pieza"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
String user;
%>

<%
    user=request.getParameter("user");
    List<Pieza> list = (ArrayList<Pieza>) request.getAttribute("piezalist");
%>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/historicalPIezascss.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Piezas</title>
    </head>
    <body>
        <header>Piezas</header>
    <li id="back">
        <a href="fabricajsp.jsp?user=<%=user%>"> Regresar</a>
    </li>
    <div class="crear">
        <li><a href="#">Crear Pieza</a>
            <form class="form" method="POST" action="ControladorPiezaServlet?user=<%=user%>">
                <input type="text" name="nombreCrearPieza" placeholder="Nombre de la pieza" required>
                <hr>
                <input type="" name="costoCrearPieza" step="0.01" placeholder="Costo de la pieza" required>
                <hr>
                <input type="submit" value="Crear Pieza" name="crearPieza">
            </form> 
        </li>

    </div>
    <hr>
    <div  id="table">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">Id</th>
                    <th class="text-center">Nombre</th>
                    <th class="text-center">Costo</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead
            <tbody>
                <c:forEach items="${piezalist}" var="pieza">
                    <tr>
                        <td class="text-center">${pieza.id}</td>
                        <td class="text-center">${pieza.tipoPieza}</td>
                        <td class="text-center">${pieza.costoPieza}</td>
                        <td class="text-center"><a class="btn btn-warnign btn-small" href="muebleria/Editar.jsp?user=<%=user%>&id=${pieza.id}">Editar</a>
                            <a class="btn btn-danger btn-small" method="POST" href="DeleteServlet?user=<%=user%>&id=${pieza.id}" >Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>    
</body>
</html>
