<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%

String codigo = (String) request.getAttribute("codProducto");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


    	<input type="button" value="Sí, acepto borrar" id="si" onclick="window.location.href='ControllerServlet?opcion=borrar&confirmoBorrar=si&codProducto=<%= codigo%>'">

    	<input type="button" value="No, cancelo borrar" id="no" onclick="window.location.href='ControllerServlet?opcion=listar&confirmoBorrar=no'"> 
</body>
</html>