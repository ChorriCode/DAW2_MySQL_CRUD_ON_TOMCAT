package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.ConnectionDB;
import model.Model;
import view.View;

public class Controller {
	
	private Model model;
	private View view;

	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
	}
	
	public void selectConnectionToDDBB() {
		ConnectionDB myConnection = new ConnectionDB("jdbc:mysql://localhost:3306/", 
				"information_schema",
				"com.mysql.jdbc.Driver", 
				"root", 
				"");
		this.model = new Model(myConnection);
		this.model.setConnDB(myConnection);
		model.connectToBD();
	}

	public void listDDBB(Model myModel){
		ArrayList<String> resultado = new ArrayList<String>();
		ResultSet datos = myModel.readDDBBNames();

		try {
			while (datos.next()) {
				if (datos.getRow() == 0) break;	
				resultado.add(datos.getString(1));
			}
			resultado.add("Atras");
			resultado.add("Salir");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		view.mostrarMenuBBDD(resultado, "Selecciona Base de Datos ");
		
		//almaceno el tipo de opcion para que en main el switch reconozca cuando se elige una opcion y sepa cuando es atrás salir u otra
		if (Integer.parseInt(view.getOptionNumber()) <= (resultado.size()-3)) {
			view.setOptionForMain("Tablas");
			model.getConnDB().setDbName(resultado.get(Integer.parseInt(view.getOptionNumber()))); //asigno al atributo dbName de la clase ConnectionDB como se llama la base de datos que acabo de seleccionar
		} else if (Integer.parseInt(view.getOptionNumber()) == (resultado.size()-2)) {
			view.setOptionForMain("Atras");
		} else {
			view.setOptionForMain("Salir");
		}
	}
	
	public void listTables(Model myModel){
		ArrayList<String> resultado = new ArrayList<String>();
		
		ResultSet datos = myModel.readTableNames(view.getOptionName());

		try {
			while (datos.next()) {
				if (datos.getRow() == 0) break;	
				resultado.add(datos.getString(1));			
			}
			resultado.add("Atras");
			resultado.add("Salir");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		view.setDbName(view.getOptionName()); //guardo el nombre de la base de datos para usarlo en la consulta
		view.mostrarMenuBBDD(resultado, "Selecciona Tabla de la Base de Datos " + view.getOptionName());

		//almaceno el tipo de opcion para que en main el switch reconozca cuando se elige una opcion y sepa cuando es atrás salir u otra
		if (Integer.parseInt(view.getOptionNumber()) <= (resultado.size()-3)) {
			view.setOptionForMain("Registros");
		} else if (Integer.parseInt(view.getOptionNumber()) == (resultado.size()-2)) {
			view.setOptionForMain("Atras");
		} else {
			view.setOptionForMain("Salir");
		}
	}
	
	public void listDatasTable(Model myModel, String tabla, String sql){
		ArrayList<HashMap<String,Object>> registros = new ArrayList<HashMap<String,Object>>();
		//myModel.readOnBD(myModel.getConnDB().getDbName(),tabla, sql);
		ResultSet datos = myModel.readOnBD(view.getDbName(),tabla, sql);
		
		try {
			ResultSetMetaData metaData = datos.getMetaData();
			
			datos.first();
			if (datos.getRow() == 0) {
				System.out.println(view.hyphenatedFill("NO HAY REGISTROS".length(), ">"));
				System.out.println("NO HAY REGISTROS");
				System.out.println(view.hyphenatedFill("NO HAY REGISTROS".length(), "<"));
				myModel.getConn().close();
				datos.close();
				view.setOptionForMain("Atras");
				return;
			} else
				datos.beforeFirst();
			
			
			while (datos.next()) {
				HashMap<String, Object> datosUnaLinea = new HashMap<String,Object>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					datosUnaLinea.put(metaData.getColumnName(i), datos.getObject(i));
					//datosUnaLinea.put(metaData.getColumnName(i), rs.getString(i)); //Hace lo mismo pero con otro método
					//System.out.println(rs.getString(i));					
				}
			registros.add(datosUnaLinea);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try {
			myModel.getConn().close(); //cerramos la conexion que aun estaba abierta
			datos.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		view.setOptionForMain("Atras");
		view.showTableRegistries(registros, tabla);
		
	}
	
	public void deleteRegistry(Model myModel, String tabla, String sql) {
		String DBname = model.getConnDB().getDbName(); //Recupero el nombre de la base de datos seleccionada para poderla usar en la siguiente consulta
		//antes de pasar a borrar el registro debemos saber cual es el campo clave de la tabla para que el usuario elija borrar por ese campo.
		ResultSet datos = model.readOnBD(DBname, "", "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = \"" + DBname + "\" AND TABLE_NAME = \"" + tabla + "\" and COLUMN_KEY IN(\"PRI\", \"UNI\");");
		//SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = "tiendainformatica" AND TABLE_NAME = "articulos" and COLUMN_KEY IN("PRI", "UNI");
		try {
			datos.next();
			System.out.println("----------->" + datos.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void menuCRUD () {
		view.showCRUDMenu();
		String option = view.getOptionForMain();
		switch (option) {
		case "1":
			listDatasTable(model, view.getOptionName(), "Select * from ");
			break;
		case "2":
			deleteRegistry(model, view.getOptionName(), "");
			break;
		case "3":
			break;
		case "4":
			break;	
		}
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}	

	
}
