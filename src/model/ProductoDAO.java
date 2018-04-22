package model;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class ProductoDAO {
	
	private DataSource connectionPull;
	private Connection conn;
	private Statement state;
	private ResultSet result;
	
	

	public ProductoDAO(DataSource connectionPull) {
		this.connectionPull = connectionPull;
	}
	
	
	
	public void createConnextion() {
		try {
			this.conn = this.connectionPull.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.state = null;
		this.result = null;
	}
	
	public ResultSet getProdutos() throws Exception{
		//Creo una conexion a través del pull de datos definido en el archivo context.xml que está en META-INF
		createConnextion();
		//Creo la secuencia SQL que me devuelve los productos o artículos y el Statement
		String sql = "select * from articulos";
		//Tanto state como conn son atributos de la clase ProductoDAO en donde nos encontramos;
		state = conn.createStatement();
		//Ejecuto la instuccion SQL anterio
		result = state.executeQuery(sql);
		//Recorro el ResultSet que obtengo de las BBDDD		
		
		return result;
		
	}

}
