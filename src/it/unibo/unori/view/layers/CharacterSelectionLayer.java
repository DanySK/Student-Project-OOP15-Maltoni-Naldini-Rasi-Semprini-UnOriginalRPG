package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.Button;
import it.unibo.unori.view.sprites.JobSprite;
import it.unibo.unori.model.character.jobs.Jobs;

import java.util.Map;
import java.util.HashMap;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.imageio.ImageIO;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

/**
 *
 * The character-selection menu.
 *
 */
public class CharacterSelectionLayer extends JPanel {
    private static final Dimension SIZE = View.SIZE;
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private final int maxHero;
    private Jobs job = Jobs.values()[0];

    private final JLabel sprite;
    private final Button button;
    private final JPanel partyPanel;
    private final JTextField textField;

    private final Map<String, Jobs> party = new HashMap<String, Jobs>();

    /**
     * Displays the character-selection menu.
     * @param maxHero the number of heroes in the party
     * @param button the button to be displayed when finished
     * @throws SpriteNotFoundException if the sprite is not found
     */
    public CharacterSelectionLayer(final int maxHero,
                                   final Button button) throws SpriteNotFoundException {
        super();
        this.button = button;
        this.maxHero = maxHero;

        this.setBackground(BACKGROUND_COLOR);

        this.setPreferredSize(SIZE);
        this.setBounds(0, 0, SIZE.width, SIZE.height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        textField = new JTextField(20);
        textField.addActionListener(new SelectionAction(0));
        textField.setMaximumSize(textField.getPreferredSize());

        final JPanel spritePanel = new JPanel();
        spritePanel.setBackground(BACKGROUND_COLOR);
        spritePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        final BasicArrowButton left = new BasicArrowButton(SwingConstants.WEST);
        left.addActionListener(new SelectionAction(-1));

        sprite = new JLabel(new ImageIcon(getSprite()));

        final BasicArrowButton right = new BasicArrowButton(SwingConstants.EAST);
        right.addActionListener(new SelectionAction(1));

        final JLabel statistics = new JLabel("Statistics:");
        statistics.setForeground(Color.WHITE);
        statistics.setAlignmentX(Component.CENTER_ALIGNMENT);

        partyPanel = new JPanel();
        partyPanel.setBackground(BACKGROUND_COLOR);
        partyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // TODO statistiche

        button.setEnabled(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        spritePanel.add(left);
        spritePanel.add(sprite);
        spritePanel.add(right);

        spritePanel.setMaximumSize(spritePanel.getPreferredSize());

        this.add(textField);
        this.add(spritePanel);
        this.add(statistics);
        this.add(partyPanel);
        this.add(button);
    }

    /**
     * @return the party created by the user
     */
    public Map<String, Jobs> getParty() {
        return party;
    }

    private class SelectionAction implements ActionListener {
        private final int direction;

        SelectionAction(final int direction) {
            this.direction = direction;
        }

        public void actionPerformed(final ActionEvent e) {
            try {
                if (direction == 0) {
                    addCurrentJobToParty();
                }
                else if (direction == -1) {
                    previousJob();
                }
                else if (direction == 1) {
                    nextJob();
                }
            } catch (final SpriteNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void addCurrentJobToParty() throws SpriteNotFoundException {
        if (party.size() < maxHero && party.get(textField.getText()) == null) {
            party.put(textField.getText(), job);

            partyPanel.add(new JLabel(new ImageIcon(getSprite())));
            partyPanel.validate();
        } // TODO errore

        if (party.size() == maxHero) {
            button.setEnabled(true);
        }
    }

    private BufferedImage getSprite() throws SpriteNotFoundException {
        BufferedImage spriteSheet;

        try {
            spriteSheet = ImageIO.read(new File(job.getBattleFrame()));
        } catch (final IOException e) {
            spriteSheet = null;
            throw new SpriteNotFoundException(job.getBattleFrame());
        }

        if (spriteSheet != null) {
            return spriteSheet.getSubimage(JobSprite.BATTLE.getPosition().x,
                                           JobSprite.BATTLE.getPosition().y,
                                           JobSprite.BATTLE.getDimension().width,
                                           JobSprite.BATTLE.getDimension().height);
        } else {
            return null;
        }
    }

    private void nextJob() throws SpriteNotFoundException {
        job = Jobs.values()[(job.ordinal() + 1)  % (Jobs.values().length - 1)];
        sprite.setIcon(new ImageIcon(getSprite()));
    }

    private void previousJob() throws SpriteNotFoundException {
        if (job.ordinal() - 1 >= 0) {
            job = Jobs.values()[job.ordinal() - 1];
            sprite.setIcon(new ImageIcon(getSprite()));
        } else {
            job = Jobs.values()[Jobs.values().length - 2];
            sprite.setIcon(new ImageIcon(getSprite()));
        }
    }

    public static void main(final String... args) throws FileNotFoundException {
        final View view = new View();
        final Button button = new Button("Test");
        final JPanel characterSelection = new CharacterSelectionLayer(5, button);

        view.push(characterSelection);
        view.resizeTo(characterSelection);

        view.run();
        view.centerToScreen();
    }
}