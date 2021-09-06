
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="os.Config.Conexion"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    String username;
    String tipoConsulta;
    String nitCliente;
    String fechaInicial;
    String fechaFinal;
    ResultSet result = null;
    ResultSet result2 = null;
    String nit;
    ResultSet result3 = null;
    String fechaIniDevolucion;
    String fechaFinDevolucion;
    String nitDev;
%>

<%
    username=request.getParameter("user");
    nit = request.getParameter("nitFactura");
    tipoConsulta = request.getParameter("tipoConsulta");
    nitCliente = request.getParameter("nitClienteConsultaCompra");
    fechaInicial = request.getParameter("fechaInicialConsultaCompras");
    fechaFinal = request.getParameter("fechaFinalConsultaCompras");
    fechaIniDevolucion = request.getParameter("fechaInicioConsultaDevoluciones");
    fechaFinDevolucion = request.getParameter("fechaFinalConsultaDevoluciones");
    nitDev = request.getParameter("nitClienteConsultaDevolucion");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <title>Consulta</title>
    </head>
    <body>
        <%        if (tipoConsulta.equals("CompraIntervalo")) {
                if (!fechaInicial.isEmpty() && !fechaFinal.isEmpty() && !nitCliente.contains("-")) {
                    PreparedStatement consulta1 = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM venta WHERE nitVenta='" + nitCliente + "' AND fecha>='" + fechaInicial + "' AND fecha<='" + fechaFinal + "';");
                    result = consulta1.executeQuery();

                } else if (!nitCliente.contains("-") && fechaFinal.isEmpty() && fechaInicial.isEmpty()) {
                    PreparedStatement consulta1 = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM venta WHERE nitVenta='" + nitCliente + "';");
                    result = consulta1.executeQuery();
                } else {
                    response.sendRedirect("ErroresVentas.jsp?user="+username+"&error=Nit incorrecto");
                }
        %>
        <div>
            <h1>Consulta de compras en un intervalo  de tiempo</h1>
            <h3>Fecha Inicial: <%=fechaInicial%></h3>
            <h3>Fecha Final: <%=fechaFinal%></h3>
            <li id="back" class="btn btn-default btn-lg">
                <a href="../ventasJsp.jsp?user=<%=username%>"> Regresar</a>
            </li>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">Mueble Comprado</th>
                        <th class="text-center">NIT</th>
                        <th class="text-center">Fecha</th>
                    </tr>
                </thead>
                <tbody>
                    <%                        if (result != null) {
                            while (result.next()) {
                    %>
                    <tr>
                        <td class="text-center"><%=result.getString(1)%></td>
                        <td class="text-center"><%=result.getString(2)%></td>
                        <td class="text-center"><%=result.getString(3)%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%
            }
        %>
        <%
            if (tipoConsulta.equals("devolucionIntervalo")) {
                if (!fechaIniDevolucion.isEmpty() && !fechaFinDevolucion.isEmpty() && !nitDev.contains("-")) {
                    PreparedStatement consulta2 = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM devolucion WHERE nitVenta='" + nitDev + "' AND fecha>='" + fechaIniDevolucion + "' AND fecha<='" + fechaFinDevolucion + "';");
                    result2 = consulta2.executeQuery();

                } else if (!nitDev.contains("-") && fechaFinDevolucion.isEmpty() && fechaIniDevolucion.isEmpty()) {
                    PreparedStatement consulta2 = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM devolucion WHERE nitVenta='" + nitDev + "';");
                    result2 = consulta2.executeQuery();
                } else {
                    response.sendRedirect("ErroresVentas.jsp?user="+username+"&error=Nit incorrecto");
                }
        %>
        <div>
            <h1>Consulta de devolucion en un intervalo  de tiempo</h1>
            <h3>Fecha Inicial: <%=fechaIniDevolucion%></h3>
            <h3>Fecha Final: <%=fechaFinDevolucion%></h3>
            <li id="back" class="btn btn-default btn-lg">
                <a href="../ventasJsp.jsp?user=<%=username%>"> Regresar</a>
            </li>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">Mueble</th>
                        <th class="text-center">NIT</th>
                        <th class="text-center">Fecha de devolucion</th>
                    </tr>
                </thead>
                <tbody>
                    <%                        if (result2 != null) {
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
        <%
            }
        %>
        <%
            if (tipoConsulta.equals("facturaCliete")) {
                if (!nit.contains("-")) {
                    PreparedStatement consulta3 = (PreparedStatement) Conexion.conexion().prepareStatement("SELECT *FROM cliente WHERE nit='" + nit + "'");
                    result3 = consulta3.executeQuery();
                } else {
                    response.sendRedirect("ErroresVentas.jsp?user="+username+"&error=Nit incorrecto");
                }

        %>
        <div>
            <h1>Consulta de faactura de un cliente</h1>
            <li id="back" class="btn btn-default btn-lg">
                <a href="../ventasJsp.jsp?user=<%=username%>"> Regresar</a>
            </li>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">NIT</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Municipio</th>
                        <th class="text-center">Departamento</th>
                    </tr>
                </thead>
                <tbody>
                    <%                        if (result3 != null) {
                            while (result3.next()) {
                    %>
                    <tr>
                        <td class="text-center"><%=result3.getString(1)%></td>
                        <td class="text-center"><%=result3.getString(2)%></td>
                        <td class="text-center"><%=result3.getString(3)%></td>
                        <td class="text-center"><%=result3.getString(4)%></td>
                        <td class="text-center"><%=result3.getString(5)%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%
            }
        %>

    </body>
</html>
