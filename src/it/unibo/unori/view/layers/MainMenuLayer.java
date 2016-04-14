package it.unibo.unori.view.layers;

import java.awt.Graphics;
import javax.swing.JPanel;

import it.unibo.unori.view.View;

import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedInputStream;

import javax.sound.midi.Sequencer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;

/**
 * 
 * Main menu view layer.
 *
 */
public class MainMenuLayer extends JPanel {

    /**
     * Creates an instance of the main menu layer.
     */
    public MainMenuLayer() {
        super();

        setPreferredSize(View.SIZE);
        setBounds(0, 0, View.SIZE.width, View.SIZE.height);
    }

    /**
     * Draw the content of the main menu.
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
    }

    /**
     * MIDI playing main.
     */
    public static void main(final String... args) throws MidiUnavailableException,
                                                         IOException,
                                                         InvalidMidiDataException,
                                                         InterruptedException {
        final View view = new View();
        final MainMenuLayer layer = new MainMenuLayer();

        view.pushLayer(layer);

        /*
         * MIDI
         */

        final int delay = 20000;
        final Sequencer sequencer = MidiSystem.getSequencer();
        final InputStream is = new BufferedInputStream(new FileInputStream(new File("res/9.mid")));

        sequencer.setSequence(is);

        sequencer.open();
        Thread.sleep(delay);

        sequencer.start();
    }
}
