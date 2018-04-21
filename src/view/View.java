package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class View {
	
	private String optionNumber;
	private String optionName;
	private String optionForMain;
	private String dbName;
	Scanner sc; 
	
	public View() {
		this.sc = new Scanner(System.in);
	}
	
	//Muestra en un menu la consulta tanto si es una base de datos como si es una tabla
	//para poder seleccionar lo que sea segun el caso.
	//le paso por par�metro el arraylist con la cosulta que se haya hecho previamente
	//y un mensaje para que salga por pantalla si los datos corresponden a una BBDD o a una tabla
	public void mostrarMenuBBDD(ArrayList<String> listadoConsulta, String mensaje) {
		
		int optionInt = 0;
		System.out.println();
		System.out.println(hyphenatedFill(mensaje.length(),"="));
		System.out.println(mensaje);
		System.out.println(hyphenatedFill(mensaje.length(),"="));
		for (int i = 0; i < listadoConsulta.size(); i++) {		
			System.out.println(i + ") " + listadoConsulta.get(i));		
		}
		
		do {
			this.optionNumber = this.sc.nextLine();
			try {
				optionInt = Integer.parseInt(optionNumber);
			} catch (Exception e) {
				//En caso de que teclemos letras dara un error, asignamos el valor cero para que nos vuelva a preguntar
				//por un numero entre 1 y el que tenga la consulta
					optionInt = -1;		
			}
			if (!(optionInt>=0 && optionInt<= listadoConsulta.size()-1)) {System.out.println("Seleccione del 0 al " + (listadoConsulta.size()-1));}
		} while (!(optionInt>=0 && optionInt<= listadoConsulta.size()-1));

		this.optionName = listadoConsulta.get(optionInt);
	}
	
	public void showTableRegistries(ArrayList<HashMap<String,Object>> listadosRegistros, String tabla) {
		
		System.out.println("\r\r");
		hyphenatedFill(listadosRegistros.get(0).keySet().size()*23,"*");
		System.out.println("TABLA : " + tabla);
		Set<String> unRegistro = listadosRegistros.get(0).keySet();
		String[] nombreColumnasTabla = new String[unRegistro.size()];
		int indice = 0;	
		for ( String unCampo : unRegistro) {			
			System.out.printf("%-25s", unCampo + "");
			nombreColumnasTabla[indice++] = unCampo;
		}		
		System.out.println();
		String fill = hyphenatedFill(unRegistro.size()*23,"-");
		System.out.println(fill);
		for (int i = 0; i < listadosRegistros.size(); i++) {	
			for (int j = 0; j < listadosRegistros.get(i).size(); j++) {
				System.out.printf("%-25s", listadosRegistros.get(i).get(nombreColumnasTabla[j]));
			}		
			System.out.println("");
		}
		System.out.println("\r\r");
	}
	
	//Este metodo solo crea una linea de guiones para que se adapte segun las columnas que tenga la tabla
	public String hyphenatedFill(int spaces, String filler) {
		String fill = "";
		
		for (int i = 0; i < spaces; i++) {
			fill += filler;
		}
		
		
		return fill;
	}
	
	public void showCRUDMenu() {
		int optionInt = -1;
		System.out.println("SELECCIONAR OPCIONES CRUD");
		System.out.println(hyphenatedFill("SELECCIONAR OPCIONES CRUD".length(),"-"));
		System.out.println("1) Consultar");
		System.out.println("2) Modificar");
		System.out.println("3) Insertar");
		System.out.println("4) Borrar");
		System.out.println("5) Atras");
		System.out.println("6) Salir");
		
		do {
			this.optionNumber = this.sc.nextLine();
			try {
				optionInt = Integer.parseInt(optionNumber);
			} catch (Exception e) {
				//En caso de que teclemos letras dara un error, asignamos el valor cero para que nos vuelva a preguntar
				//por un numero entre 1 y 6
					optionInt = -1;		
			}
			if (!(optionInt>0 && optionInt< 7)) {System.out.println("Seleccione del 1 al 6");}
		} while (!(optionInt>0 && optionInt< 7));
		
		if (optionInt == 5)
			this.optionForMain = "Atras";
		else if (optionInt == 6)
			this.optionForMain = "Salir";
		else
			this.optionForMain = optionInt + "";
	}
	
	public void menuDelete(String tabla, String campo) {
	
		System.out.println("BORRADO DE REGISTRO EN LA TABLA: " + tabla);
		
		System.out.println("Debe seleccionar un registro por el campo " + campo);
	}

	public String getOptionNumber() {
		return optionNumber;
	}

	public String getOptionName() {
		return optionName;
	}

	public String getOptionForMain() {
		return optionForMain;
	}

	public String getDbName() {
		return dbName;
	}

	public void setOptionNumber(String optionNumber) {
		this.optionNumber = optionNumber;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public void setOptionForMain(String optionForMain) {
		this.optionForMain = optionForMain;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	
	
}
