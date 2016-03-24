package it.unibo.unori.view.layers;

import java.awt.Graphics;

import it.unibo.unori.view.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

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

	public static void main(final String... args) throws MidiUnavailableException,
	                                                     IOException,
	                                                     InvalidMidiDataException,
	                                                     InterruptedException {
		MainMenuLayer layer = new MainMenuLayer();

		View view = new View();
		view.pushLayer(layer);

		Sequencer sequencer = MidiSystem.getSequencer();
		InputStream is = new BufferedInputStream(new FileInputStream(new File("res/9.mid")));
		sequencer.setSequence(is);
		sequencer.open();
		
		Thread.sleep(2000);
		sequencer.start();
	}
}
