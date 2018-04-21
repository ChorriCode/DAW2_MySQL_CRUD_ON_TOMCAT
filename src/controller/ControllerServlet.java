package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Controllador
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //Establecer el DataSource
	
	@Resource(name="jdbc/mysql")
	private DataSource miPool;
    public ControllerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Crear el objeto PrinWriter
		PrintWriter salida = response.getWriter();
		
		response.setContentType("text/plain");
		
		//Crear conexion base de datos
		
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;
		
		try {
			miConexion = miPool.getConnection();
			String miSql = "select * from tables";
			miStatement = miConexion.createStatement();
			miResultset = miStatement.executeQuery(miSql);
			
			while (miResultset.next()) {
				
				String nombre = miResultset.getString(3);
				salida.println(nombre);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
