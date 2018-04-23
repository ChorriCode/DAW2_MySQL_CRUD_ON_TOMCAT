package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Producto;
import model.ProductoDAO;

/**
 * Servlet implementation class Controllador
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductoDAO miProductoDAO;

    //Establecer el DataSource
	@Resource(name="jdbc/mysql")
	private DataSource miPool;
 
	
	public ControllerServlet() {

	}

	

	@Override
	public void init() throws ServletException {

		super.init();
		try {
			
			miProductoDAO = new ProductoDAO(miPool);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Crear el objeto PrinWriter
		PrintWriter salida = response.getWriter();
		
		response.setContentType("text/plain");
		
		String opcion = request.getParameter("opcion");
		if (opcion == null) opcion = "listar";
		switch (opcion) {
		case "listar":
			getProductos(request,response);
			try {
				miProductoDAO.getResult().close();
				miProductoDAO.getState().close();
				miProductoDAO.getConn().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "insertar":
			setProductos(request,response);
			break;
		}

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void getProductos(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Obtengo la lista de productos en forma de ResultSet de la consulta SQL 
			ResultSet resultProductos = miProductoDAO.getProdutos();
			//Convierto el ResulSet en un ArrayList de Productos
			ArrayList<Producto> listaProductos = convertirResultSetToProducto(resultProductos);
			
			//Agrego al request la lista de productos;
			request.setAttribute("listaProductos", listaProductos);
			
			//Envío ese request a la página JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/listaProductos.jsp");
			miDispatcher.forward(request, response);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
	
	
	private void setProductos(HttpServletRequest request, HttpServletResponse response) {
		//int codigo = (int) request.getAttribute("codigo");

		String articulo = request.getParameter("articulo");		
		int precio = Integer.parseInt(request.getParameter("precio"));		
		int codFabricante = Integer.parseInt(request.getParameter("codFabricante"));	
		Producto miProducto = new Producto(articulo, precio, codFabricante);
		try {
			miProductoDAO.setProductos(miProducto);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Producto> convertirResultSetToProducto(ResultSet resultProductos){
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		
		
		try {
			while (resultProductos.next()) {
			
				int codigo = resultProductos.getInt(1);
				String articulo = resultProductos.getString(2);
				int precio = resultProductos.getInt(3);
				int codFabricante = resultProductos.getInt(4);
				Producto miProducto = new Producto(codigo, articulo, precio, codFabricante);
				resultado.add(miProducto);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
		
	}

}
