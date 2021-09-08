
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mi Muebleria|Login</title>
        <link href="css/estiloLogin.css" rel="stylesheet" type="text/css"/>
        <link href="css/fontello.css" rel="stylesheet"/>
    </head>
    <body>
        <header>
            <h1>Mi Muebleria</h1>
        </header>
        <div class="contenedor1">
            <h1>Sign in</h1>
            <form class="form1" method="POST" action="usuario-servlet">
                <img src="Imagenes/icono.png"/>
                <input type="text" name="username" placeholder="Username" required="Ingresa Usuario"/>
                <hr>
                <img src="Imagenes/icono.png"/>
                <input type="password" name="password" placeholder="Password" required="Ingresa la contraseña"/>
                <hr>
                <input type="submit" value="Sign In"/>
            </form>
    </body>
</html>
