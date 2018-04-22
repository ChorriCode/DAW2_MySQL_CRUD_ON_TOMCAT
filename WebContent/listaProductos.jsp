<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.sql.*, controller.*, model.*" %>
<% 
 //obtener productos de ControllerServlet
 
 ArrayList<Producto> listaProductos = (ArrayList<Producto>) request.getAttribute("listaProductos");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MySQL - JSP - SERVLETS</title>
  </head>
  <style type="text/css">
  
  	body {
  		font-family: Arial, Helvetica, sans-serif;
  	}
  	table {
  		float: left;
  	}
  	th,td {
  		border: 1px solid black;
  		text-align: center;
  	}
  	#contenedorBoton {
  		
  	}
  </style>
  <body>
    <table>
    	<caption>TABLA DE ARTÍCULOS</caption>
      <thead>
        <tr>
          <th>Codigo</th>
          <th>Nombre Artículo</th>
          <th>Precio</th>
          <th>CodFrabricante</th>
          <th>Accion</th>
        </tr>
      </thead>
      <tbody>
      <% for (Producto unProducto : listaProductos) { %>
        <tr>
          <td><%= unProducto.getCodigo()  %></td>
          <td><%= unProducto.getArticulo()  %></td>
          <td><%= unProducto.getPrecio()  %></td>
          <td><%= unProducto.getCodFabricante()  %></td>
          <td><a href="">Modificar</a> - <a href="">Eliminar</a></td>
        </tr>
      <% } %>
      </tbody>
    </table>
    <div id="contenedorBoton">
    	<br><input type="button" value="Insertar Artículo" onclick="window.location.href='insertaProducto.jsp'"/>
    </div>
  </body>
</html>