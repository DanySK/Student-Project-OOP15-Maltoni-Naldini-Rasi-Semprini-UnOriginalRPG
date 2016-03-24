package it.unibo.unori.view;

import java.util.Stack;

import javax.swing.*;
import java.awt.Dimension;

import it.unibo.unori.view.layers.*;

/**
 * 
 * The View class displays and contains game layers, behaving like a stack
 *
 */
public final class View extends JFrame {	
	private static final String title = "UnRPG";
	public static final Dimension size = new Dimension(800, 600);

	public final JLayeredPane layeredPane;
	private final Stack<GameLayer> layerStack = new Stack<>();

	/**
	 * Creates and instance of View
	 */
	public View() {
		super(title);
		setSize(size.width, size.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layeredPane = getLayeredPane();
	    
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				setVisible(true);
			}
		});
	}

	/**
	 * Removes the layer at the top of the stack
	 * @throws EmptyStackException  if this stack is empty.
	 */
	public final void popLayer() {
		layeredPane.remove(layerStack.pop());
	}

	/**
	 * Pushes a layer onto the top of the stack
	 * @param gameLayer  the layer to be pushed onto the stack
	 */
	public final void pushLayer(GameLayer gameLayer) {
		layeredPane.add(layerStack.push(gameLayer));
	}
}
