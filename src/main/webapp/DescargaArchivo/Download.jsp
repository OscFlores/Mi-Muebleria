
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <title>JSP Page</title>
    </head>
    <body style="margin:auto; text-align: center;">
        <h1>${param.nombre}</h1>
        <br>
        <a class="btn btn-default" style="margin:auto" href="DownloadServlet?path=<%=request.getParameter("archivo")%>">Descargar</a>
        <br>
        <br>
        <br>
        <a class="btn btn-danger" style="margin:auto;" href="administracionJsp.jsp">Regresar</a>
    </body>
</html>
