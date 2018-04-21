package main;

import controller.Controller;
import view.View;

public class Main2 {

	public static void main(String[] args) {


		View myView = new View();
		Controller myController = new Controller(null, myView);
		//Conecto a una base de datos definida en la clase controller dentro del metodo al que voy a llamar
		myController.selectConnectionToDDBB();
		
		

	}

}
