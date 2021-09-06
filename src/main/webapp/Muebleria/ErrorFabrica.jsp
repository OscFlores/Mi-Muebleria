
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    String user;
%>

<%
    user = request.getParameter("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <title>Error</title>
    </head>
    <body>
        <h1>Error en Area de Fabrica</h1>
        <br>
    <li id="back" class="btn btn-default ">
        <a href="../fabricajsp.jsp?user=<%=user%>"> Regresar</a>
    </li>
    <h1 class="text-danger"> Error: ${param.error}</h1>
</body>
</html>
