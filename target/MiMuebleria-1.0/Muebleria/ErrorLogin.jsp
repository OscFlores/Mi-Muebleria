
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <title>ERROR</title>
    </head>
    <body>
        <h1>Error Inicio de sesion</h1>
        <br>
        <br>
        <br>
    <li id="back" class="btn btn-default ">
        <a href="../index.jsp"> Regresar</a>
    </li>
    <h1 class="text-danger">
        Error: ${param.error}
    </h1>
</body>
</html>
