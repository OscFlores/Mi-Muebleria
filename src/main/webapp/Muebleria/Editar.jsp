
<%@page import="javax.swing.JOptionPane"%>
<%@page import="os.Clases.Pieza"%>
<%@page import="os.Modelos.DBPieza"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    Pieza pieza = null;
    DBPieza db = new DBPieza();
    String user;
%>
<%
    user=request.getParameter("user");
    pieza = db.List(Integer.parseInt(request.getParameter("id")));
%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <title>Editar</title>
    </head>
    <body>
        <div class="container">
            <h1>Editar Pieza</h1>
            <hr>
            <% if (pieza != null) {
            %>
            <form method="POST" action="../EditPiezaServlet?user=<%=user%>" class="form-control" style="width: 500px; height: 400px" >
                ID:
                <input type="text" readonly="" name="idPieza" class="form-control" value="<%=pieza.getId()%>">
                Tipo:
                <input type="text" name="tipoPieza"  class="form-control" value="<%=pieza.getTipoPieza()%>" required/>
                Costo:
                <input type="number" name="costoPieza"  step="0.01" class="form-control" value="<%=pieza.getCostoPieza()%>" required><br>
                <br>
                <input type="submit" value="Guardar"  class="btn btn-primary btn-lg">
                <a href="../Piezas_Servlet?user=<%=user%>" > Regresar</a>
            </form>
            <%
                }
            %>
        </div> 
    </body>
</html>

