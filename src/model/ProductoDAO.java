package model;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class ProductoDAO {
	
	private DataSource connectionPull;
	private Connection conn;
	private Statement state;
	private PreparedStatement preState;
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
	
	
	public void setProductos(Producto miProducto) throws Exception {
		createConnextion();
		String sql = "insert into articulos (nombre, precio, fabricante) values(?,?,?)";
		preState = conn.prepareStatement(sql);
		//establecemos cuales serán los valores de la consulta sql por cada "?" tenemos debajo que valores asignamos por el orden de las columnas
		preState.setString(1, miProducto.getArticulo());
		preState.setInt(2, miProducto.getPrecio());
		preState.setInt(3, miProducto.getCodFabricante());
		preState.execute();
			
	}
	
	public void deleteProductos(int codProducto) throws Exception {
		createConnextion();
		String sql = "delete from articulos where codigo = ?";
		preState = conn.prepareStatement(sql);
		preState.setInt(1, codProducto);
		preState.execute();
	}

	public void updateProductos(Producto miProducto) throws Exception {
		createConnextion();
		String sql = "update articulos set nombre = ?, precio = ?, fabricante = ? where codigo = ?";
		preState = conn.prepareStatement(sql);
		//establecemos cuales serán los valores de la consulta sql por cada "?" tenemos debajo que valores asignamos por el orden de las columnas
		
		preState.setString(1, miProducto.getArticulo());
		preState.setInt(2, miProducto.getPrecio());
		preState.setInt(3, miProducto.getCodFabricante());
		preState.setInt(4, miProducto.getCodigo());
		preState.execute();
			
	}

	public DataSource getConnectionPull() {
		return connectionPull;
	}



	public Connection getConn() {
		return conn;
	}



	public Statement getState() {
		return state;
	}



	public ResultSet getResult() {
		return result;
	}



	public void setConnectionPull(DataSource connectionPull) {
		this.connectionPull = connectionPull;
	}



	public void setConn(Connection conn) {
		this.conn = conn;
	}



	public void setState(Statement state) {
		this.state = state;
	}



	public void setResult(ResultSet result) {
		this.result = result;
	}



	public PreparedStatement getPreState() {
		return preState;
	}



	public void setPreState(PreparedStatement preState) {
		this.preState = preState;
	}

	
	
}
