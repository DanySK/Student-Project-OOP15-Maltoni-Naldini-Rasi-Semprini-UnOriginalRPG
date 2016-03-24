package it.unibo.unori.view.layers;

import java.awt.Graphics;

import it.unibo.unori.view.*;

public class MainMenuLayer extends GameLayer {

	/**
	 * Creates an instance of the MainMenu layer
	 */
	public MainMenuLayer() {
		setPreferredSize(View.size);
		setBounds(0, 0, View.size.width, View.size.height);
	}

	/**
	 * Draw the content of the main menu
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public static void main(final String... args) {
		MainMenuLayer layer = new MainMenuLayer();
		
		View view = new View();
		view.pushLayer(layer);
	}
}
