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
  	
  	th {
  		background-color: lightgrey;
  	}

  	a {
  		text-decoration: none;
  	}
  	a:hover{
  		color: white;
  		background-color: black;
  	}
  	input {
  		margin-left: 10px;
  		margin-top: 2px;
  		font-weight: bold;
  	}
  	input#eliminar {
  		background-color: green;
  		color: white;
  	}
  	input#modificar {
  		background-color: blue;
  		color: white;
  	}
   	input#no {
  		background-color: red;
  		color: white;
  	}
  	
  	input#si:hover, input#no:hover {
  		color: yellow;
  	}
  	
  	td.si{
  		display: none;
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
          <td><input type="button" value="Modificar" id="modificar"
          onclick="window.location.href='modificarProducto.jsp?codigo=<%= unProducto.getCodigo()%>&'+
          													   'articulo=<%= unProducto.getArticulo()%>&'+
          													   'precio=<%= unProducto.getPrecio()%>&'+
          													   'codFabricante=<%= unProducto.getCodFabricante()%>'"></td>
          <td><input type="button" value="Eliminar" id="eliminar" onclick="document.getElementById('cod<%= unProducto.getCodigo()%>').style.display = 'block'"></td>
          <td id="cod<%= unProducto.getCodigo()%>" class="si">
          <input type="button" value="Eliminar SI" id="eliminarSi" onclick="window.location.href='ControllerServlet?opcion=borrar&codProducto=<%= unProducto.getCodigo()%>'">
          <input type="button" value="Eliminar NO" id="eliminarno" onclick="window.location.href='ControllerServlet?opcion=listar'">
          </td>
       
        </tr>
      <% } %>
      </tbody>
    </table>
    <div id="contenedorBoton">
    	<br><input type="button" value="Insertar Artículo" onclick="window.location.href='insertaProducto.jsp'"/>
    </div>
    
<%-- 
    <%
    String opcion = (String) request.getAttribute("borrar");
    if(opcion == "borrar"){
%>
    <div id="alert">
    	<input type="button" value="Sí, acepto borrar" id="si" onclick="window.location.href='ControllerServlet?opcion=borrar&confirmoBorrar=si'">
    	<input type="button" value="No, cancelo borrar" id="no" onclick="window.location.href='ControllerServlet?opcion=listar&confirmoBorrar=no'">  
    </div>
<%  
   }    
%> --%>
  </body>
</html>