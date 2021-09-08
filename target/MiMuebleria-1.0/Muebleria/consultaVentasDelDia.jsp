
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="os.Config.Conexion"%>
<%!
    String username;
    ResultSet result = null;
    ResultSet result2 = null;
    ResultSet result3 = null;
    ResultSet result4 = null;
    ResultSet result5 = null;
%>
<%
    username=request.getParameter("user");
    PreparedStatement consultaDelDia = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM venta WHERE fecha=CURDATE()");
    result2 = consultaDelDia.executeQuery();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <title>Ventas del dia</title>
    </head>
    <body>
        <h1>Consulta de ventas del dia</h1>
    <li id="back" class="btn btn-default btn-lg">
        <a href="../ventasJsp.jsp?user=<%=username%>"> Regresar</a>
    </li>
    <div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">Mueble</th>
                    <th class="text-center">NIT</th>
                    <th class="text-center">Fecha</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (result2 != null) {
                        while (result2.next()) {
                %>
                <tr>
                    <td class="text-center"><%=result2.getString(1)%></td>
                    <td class="text-center"><%=result2.getString(2)%></td>
                    <td class="text-center"><%=result2.getString(3)%></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
