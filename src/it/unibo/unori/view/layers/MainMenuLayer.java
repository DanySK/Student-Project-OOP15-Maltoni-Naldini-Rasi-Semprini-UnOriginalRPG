package it.unibo.unori.view.layers;

import java.awt.Graphics;

import it.unibo.unori.view.*;

public class MainMenuLayer extends GameLayer {
	private String s = "ciao";
	
	public MainMenuLayer() {
		setPreferredSize(View.size);
		setBounds(0, 0, View.size.width, View.size.height);
	}
	
	public void setString(String string)
	{
		s = string;
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString(s, 20, 20);
        g.drawRect(20, 20, 200, 200);
    }

	public static void main(final String... args) {
		MainMenuLayer layer = new MainMenuLayer();
		
		View view = new View();
		view.pushLayer(layer);
		
		layer.setString("test");
	}
}
