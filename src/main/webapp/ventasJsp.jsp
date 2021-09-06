<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! 
String username;
%>

<%
username=request.getParameter("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css1/ventascss.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <title>Area de Ventas | Mi Muebleria</title>
    </head>
    <body>
        <header>Areá de Ventas</header>
        <div class="logout">
            <h4><%=username%></h4>
            <ul class="cerrarSesion">
                <li><a href="index.jsp">Cerrar Sesion</a></li>
            </ul>
        </div>
        <div class="ventas">
            <nav class="nav">
                <ul class="menu">
                    <li><a href="Muebleria/consultaVentas.jsp?user=<%=username%>">Consulta de Muebles disponibles en la sala de Ventas</a>
                    <li><a href="Muebleria/consultaVentasDelDia.jsp?user=<%=username%>">Consulta de Ventas del dia</a></li>
                    <li><a href="#">Consulta de compras de un Cliente en un intervalo de tiempo</a>
                        <form id="form" method="POST" action="Muebleria/consultaClienteIntervalo.jsp?tipoConsulta=CompraIntervalo&user=<%=username%>" style="height: 250px;">
                            NIT:
                            <input type="text" class="form-control" name="nitClienteConsultaCompra" placeholder="NIT" required>
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicialConsultaCompras" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalConsultaCompras" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger"  value="Consultar">
                        </form>
                    </li>
                    <li><a href="#">Consulta de devoluciones Realizadas por un cliente en un intervalo de tiempo</a>
                        <form id="form" method="POST" action="Muebleria/consultaClienteIntervalo.jsp?tipoConsulta=devolucionIntervalo&user=<%=username%>" style="height: 250px;"> 
                            NIT:
                            <input type="text"  class="form-control" name="nitClienteConsultaDevolucion" placeholder="NIT" required>
                            Fecha Inicial:
                            <input type="date" class="form-control" name="fechaInicioConsultaDevoluciones" placeholder="Fecha">
                            Fecha Final:
                            <input type="date" class="form-control" name="fechaFinalConsultaDevoluciones" placeholder="Fecha">
                            <br>
                            <input type="submit" class="btn btn-danger" value="Consultar">
                        </form>
                    </li>
                    <li><a href="#">Detalle de la factura de un cliente</a>
                        <form  id="form" method="POST" action="Muebleria/consultaClienteIntervalo.jsp?tipoConsulta=facturaCliete&user=<%=username%>" style="height: 200px">
                            <h3>Consulta</h3>
                            NIT:
                            <input type="text" class="form-control"name="nitFactura" placeholder="NIT" required/>
                            <br>
                            <input type="submit" class="btn btn-danger" value="Consultar">
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="container">
            <form class="form-control" method="POST" action="VentaServlet?user=<%=username%>" style="width: 500px; margin:0;height: 280px;margin-top:15%;text-align: center;"/>
            <h3>Venta de Mueble</h3>
            Tipo de mueble:
            <input type="text" class="form-control" name="tipoMuebleVenta" placeholder="Tipo de Mueble"required />
            <h4>Datos de Cliente</h4>
            NIT:
            <input type="text" class="form-control" name="nitClienteVenta" onclick="" placeholder="NIT" required />
            <br>
            <input type="submit"  class="btn btn-danger btn-small" value="Vender Mueble"/>
        </form>
    </div>
    <div class="container">
        <form class="form-control" method="POST" action="InsertClienteServlet?user=<%=username%>" style="width: 500px; height: 400px;margin-top:15%;margin-left: 600px;text-align: center;">
            <h3>Agregar Cliente</h3>
            Nombre:
            <input type="text" class="form-control" name="nombreCliente" placeholder="Ej. Juan" required/>
            NIT:
            <input type="text" class="form-control" name="nitCliente" placeholder="Ej. 323232k" required/>
            Direccion:
            <input type="text" class="form-control" name="direccion" placeholder="3ra calle" required/>
            Municipio:
            <input type="text" class="form-control" name="municipio" placeholder="Quetzaltenango"/>
            Departamento:
            <input type="text" class="form-control" name="departamento" placeholder="Quetzaltenango"/>
            <br>
            <input type="submit" class="btn btn-danger btn-sm" value="Agregar Cliente"/>
        </form>
    </div>
    <div class="container">
        <form class="form-control" method="POST" action="DevolucionServlet?user=<%=username%>" style="width: 500px; height: 270px;margin-top:33%;margin-left:0; text-align: center;">
            <h3>Devolucion de Mueble</h3>
            Mueble:
            <input type="text" class="form-control" name="tipoMuebleDevolucion" placeholder="Tipo de Mueble" required/>
            <h4>Datos de Cliente</h4>
            NIT:
            <input type="text" class="form-control" name="nitClienteDevolucion" placeholder="NIT" required/>
            <br>
            <input type="submit" class="btn btn-danger btn-sm" value="Devolver Mueble"/>
        </form>
    </div>
</body>
</html>
