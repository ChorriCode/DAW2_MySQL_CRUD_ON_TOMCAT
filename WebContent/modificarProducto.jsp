<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%   
	String codigo = (String) request.getParameter("codigo"); 
	String articulo = (String) request.getParameter("articulo");
	String precio = (String) request.getParameter("precio");
	String codFabricante = (String) request.getParameter("codFabricante");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form name="fomrModificar" method="get" action="ControllerServlet">
		<p>MODIFICAR UN ARTÍCULO</p>
 		Codigo: <input type="text" name="codigo" id="codigo" value="<%= codigo%>" readonly><br> 
		Nombre: <input type="text" name="articulo" id="articulo" value="<%= articulo%>"><br>
		Precio: <input type="text" name="precio" id="precio" value="<%= precio%>"><br>
		CodFabricante: <input type="text" name="codFabricante" id="codFabricante" value="<%= codFabricante%>"><br>
		<input type="submit" name="opcion" id="modificar" value="modificar">
	</form>
</body>
</html>