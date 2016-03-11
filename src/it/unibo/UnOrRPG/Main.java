package it.unibo.UnOrRPG;

import it.unibo.UnOrRPG.controller.Control;
import it.unibo.UnOrRPG.controller.MainController;

public class Main {
	
	public static void main(String[] args) {
		Control C = new MainController();
		
		C.start();
	}

}
