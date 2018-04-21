package main;

import controller.Controller;
import model.Model;
import view.View;

public class Main {


	public static void main(String[] args) {
		
		
		View myView = new View();
		Model myModel = null;
		//El objeto de la clase Model no lo creo aun por eso lo paso null
		Controller myController = new Controller(myModel, myView);
		String optionSwitch = "";
		do {			
			//a parte de hacer la conexion, este metodo crea el objeto de la clase Model que necesita la clase controller
			myController.selectConnectionToDDBB();
			//ahora asignamos a este objeto de la clase Model el mismo que hemos creado dentro de la clase Controller
			myModel = myController.getModel();
			//pasamos a pedirle al controlador que solicite los datos de las diferentes BBDD que hay en nuestro MySQL
			//obviamente para eso usara clase Model y despues la View para mostrarlas
			myController.listDDBB(myModel);
			//En la clase View tenemos un atributo que guarda la opcion elegida cada vez que sale un menu a preguntar algo
			//y ese valor lo recuperamos para que el switch siguiente sepa que caso seleccionar
			optionSwitch = myView.getOptionForMain();
			
			do {
				switch (optionSwitch) {
					case "Tablas":
						myController.listTables(myModel);
						optionSwitch = myView.getOptionForMain();
						break;
					case "Registros":
						myController.menuCRUD();
						//myController.listDatasTable(myModel, myView.getOptionName(), "Select * from ");
						optionSwitch = myView.getOptionForMain();
						break;
				}
			} while (optionSwitch == "Tablas"  || optionSwitch == "Registros");
		} while (optionSwitch != "Salir");
		
		System.out.println("Que tenga un buen día");
		
	
	}

}

//SELECT DISTINCT TABLE_SCHEMA FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA <> 'information_schema' and TABLE_SCHEMA <> 'performance_schema' and TABLE_SCHEMA <> 'mysql'; 
//SELECT TABLE_SCHEMA, TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = "BD_EMPLEADOS"