<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form name="fomrInsertar" method="get" action="ControllerServlet">
		<p>INSERTAR UN ARTÍCULO</p>
		Codigo: <input type="text" name="codigo" id="codigo"><br>
		Nombre: <input type="text" name="articulo" id="articulo"><br>
		Precio: <input type="text" name="precio" id="precio"><br>
		CodFabricante: <input type="text" name="codFabricante" id="codFabricante"><br>
		<input type="submit" name="opcion" id="insertar" value="insertar">
	</form>
</body>
</html>