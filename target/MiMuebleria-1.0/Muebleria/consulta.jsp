
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="os.Clases.MiMuebleriaException"%>
<%@page import="os.Config.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    ResultSet result1 = null;
    ResultSet result2 = null;
    ResultSet result3 = null;
    ResultSet result4 = null;
    String tipoConsulta;
    String user;
%>
<%
    user=request.getParameter("user");
    tipoConsulta = request.getParameter("tipoConsulta");
    String peticion = request.getParameter("consulta");
    if (peticion.equals("mayorMenor")) {
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT  tipo,costo, COUNT(*) FROM pieza GROUP BY tipo,costo ORDER BY COUNT(*) DESC");
            result1 = consulta.executeQuery();
        } catch (MiMuebleriaException | SQLException ex) {

        }
    } else if (peticion.equals("menorMayor")) {
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT  tipo,costo, COUNT(*) FROM pieza GROUP BY tipo,costo ORDER BY COUNT(*) ASC");
            result2 = consulta.executeQuery();
        } catch (MiMuebleriaException | SQLException ex) {

        }
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <link href="../css/consultacss.css" rel="stylesheet"/>
        <title>Consulta de Piezas</title>
    </head>
    <body>
        <h1><%=tipoConsulta%></h1>
    <li id="back" class="btn btn-default btn-lg">
        <a href="../fabricajsp.jsp?user=<%=user%>"> Regresar</a>
    </li>
    <hr>
    <div  id="table">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">Nombre</th>
                    <th class="text-center">Costo </th>
                    <th class="text-center">Cantidad</th>
                </tr>
            </thead
            <tbody>
                <%                    if (request.getParameter("consulta").equals("mayorMenor") && result1 != null) {
                        while (result1.next()) {

                %>
                <tr>
                    <td class="text-center"><%=result1.getString(1)%></td>
                    <td class="text-center"><%=result1.getString(2)%></td>
                    <td class="text-center"><%=result1.getString(3)%></td>
                </tr>
                <%                        }
                    }
                %>

                <%                    if (request.getParameter("consulta").equals("menorMayor") && result2 != null) {
                        while (result2.next()) {

                %>
                <tr>
                    <td class="text-center"><%=result2.getString(1)%></td>
                    <td class="text-center"><%=result2.getString(2)%></td>
                    <td class="text-center"><%=result2.getString(3)%></td>
                </tr>
                <%                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
