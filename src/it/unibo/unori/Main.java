package it.unibo.unori;

import it.unibo.unori.controller.Control;
import it.unibo.unori.controller.MainController;

public class Main {
	
	public static void main(String[] args) {
		Control c = new MainController();
		
		c.begin();
	}

}
