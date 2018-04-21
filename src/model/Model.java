package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Model {
	
	private ConnectionDB connDB;
	private Connection conn;
	
	
	public Model(ConnectionDB connDB) {
		super();
		this.connDB = connDB;
	}

	
	//Metodo que conecta con la base de datos seleccionada.
	public Connection connectToBD() {	
		this.conn = null;
		try {
			
			//Registro el driver JDBC
			Class.forName(connDB.getDriver());		
			//Abrimos una coneccion a la Base de Datos
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(connDB.getUrl()+connDB.getDbName(),connDB.getUserName(),connDB.getPassword());
			//comprueba si la coneccion es validad y asigno el valor al atributo "validConnection"
			//del objeto connDB que pertenece a la clase ConnectionDB
			connDB.setValidConnection(conn.isValid(1000)); 
			System.out.println("Database connection ok...");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
		return conn;
	}
	
	public ResultSet readDDBBNames() {
		ResultSet myResulSet = null;
		try {
			Statement myStatement = conn.createStatement();
			myResulSet = myStatement.executeQuery("SELECT DISTINCT TABLE_SCHEMA FROM INFORMATION_SCHEMA.COLUMNS "
					+ "WHERE TABLE_SCHEMA <> 'information_schema' and TABLE_SCHEMA <> 'performance_schema' "
					+ "and TABLE_SCHEMA <> 'mysql';");
		} catch (SQLException e) {;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myResulSet;
	}
	
	public ResultSet readTableNames(String DBName) {
		ResultSet myResultSet = null;
		try {
			Statement myStatement = conn.createStatement();
			myResultSet = myStatement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + DBName + "';");
			
		} catch (SQLException e) {;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myResultSet;
	}

	// ************* C R U D -- INSERT   READ   UPDATE   DELETE **************
	
	public void createoOnBD() {
		
	}
	
	public ResultSet readOnBD(String DBname, String tabla, String sql) {
		ResultSet myResultSet = null;
		try {
			Statement myStatement = conn.createStatement();
			myStatement.execute("use "+ DBname);
			myResultSet = myStatement.executeQuery(sql + tabla + ";");	
		} catch (SQLException e) {;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myResultSet;
	}
	
	public void updateOnBD() {
		
	}
	
	public void  deleteOnBD(String DBname, String tabla, String sql) {
		int delete;
		try {
			Statement myStatement = conn.createStatement();
			myStatement.execute("use "+ DBname);
			delete = myStatement.executeUpdate(sql + tabla + ";");	//si no se ha borrado nada devuelve cero
		} catch (SQLException e) {;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public ConnectionDB getConnDB() {
		return connDB;
	}


	public void setConnDB(ConnectionDB connDB) {
		this.connDB = connDB;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
