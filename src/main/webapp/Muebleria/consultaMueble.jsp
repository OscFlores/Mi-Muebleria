
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="os.Clases.MiMuebleriaException"%>
<%@page import="os.Config.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    ResultSet result3 = null;
    ResultSet result4 = null;
    String tipoConsulta;
    String user;
%>
<%
    user=request.getParameter("user");
    tipoConsulta = request.getParameter("tipoConsulta");
    String peticion = request.getParameter("consulta");
    if (peticion.equals("MueblemayorMenor")) {
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT mueble,usuarioEnsamble,costo,MAX(CAST(fecha AS CHAR)) FROM ensamble_mueble group by mueble,usuarioEnsamble");
            result3 = consulta.executeQuery();
        } catch (MiMuebleriaException | SQLException ex) {

        }

    } else if (peticion.equals("MueblemenorMayor")) {
        try {
            PreparedStatement consulta = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT mueble,usuarioEnsamble,costo,min(fecha) FROM ensamble_mueble GROUP BY mueble ORDER BY min(fecha)");
            result4 = consulta.executeQuery();
        } catch (MiMuebleriaException | SQLException ex) {

        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <link href="../css/consultacss.css" rel="stylesheet"/>
        <title>Consulta Mueble</title>
    </head>
    <body>
        <h1><%=tipoConsulta%></h1>
    <li id="back" class="btn btn-default btn-lg">
        <a href="../fabricajsp.jsp?user=<%=user%>">Regresar</a>
    </li>
    <hr>
    <div  id="table">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">Nombre</th>
                    <th class="text-center">Usuario</th>
                    <th class="text-center">Costo</th>
                    <th class="text-center">Fecha</th>
                </tr>
            </thead

            <tbody>
                <%                    if (request.getParameter("consulta").equals("MueblemayorMenor") && result3 != null) {
                        while (result3.next()) {

                %>
                <tr>
                    <td class="text-center"><%=result3.getString(1)%></td>
                    <td class="text-center"><%=result3.getString(2)%></td>
                    <td class="text-center"><%=result3.getString(3)%></td>
                    <td class="text-center"><%=result3.getString(4)%></td>
                </tr>
                <%                        }
                    }
                %>
                <%                    if (request.getParameter("consulta").equals("MueblemenorMayor") && result4 != null) {
                        while (result4.next()) {

                %>
                <tr>
                    <td class="text-center"><%=result4.getString(1)%></td>
                    <td class="text-center"><%=result4.getString(2)%></td>
                    <td class="text-center"><%=result4.getString(3)%></td>
                    <td class="text-center"><%=result4.getString(4)%></td>
                </tr>
                <%                        }
                    }
                %>

            </tbody>
        </table>
    </div>
</body>
</html>
