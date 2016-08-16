package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.Button;
import it.unibo.unori.view.sprites.JobSprite;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;

import java.util.Map;
import java.util.HashMap;

import java.io.File;
import java.io.IOException;
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
public class CharacterSelectionLayer extends Layer {
	private static final long serialVersionUID = 1L;
	
	private static final Dimension SIZE = View.SIZE;
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private final int maxHero;
    private Jobs job = Jobs.values()[0];

    private final JLabel sprite;
    private final Button button;
    private final JLabel statistics;
    private final JPanel partyPanel;
    private final JTextField textField;

    private final Map<String, Jobs> party = new HashMap<String, Jobs>();

    // TODO bottone che aggiunge il job
    // TODO visualizzazione personaggio nome uguale
    // TODO disabilitare il campo testo
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

        statistics = new JLabel(getStatistics(), SwingConstants.CENTER);

        statistics.setForeground(Color.WHITE);
        statistics.setAlignmentX(Component.CENTER_ALIGNMENT);

        partyPanel = new JPanel();
        partyPanel.setBackground(BACKGROUND_COLOR);
        partyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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

    private String getStatistics() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<html>");

        stringBuilder.append("HP: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.TOTALHP));
        stringBuilder.append("<br>");
        stringBuilder.append("MP: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.TOTALMP));
        stringBuilder.append("<br>");
        stringBuilder.append("Speed: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.SPEED));
        stringBuilder.append("<br>");
        stringBuilder.append("Fire Attack: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.FIREATK));
        stringBuilder.append("<br>");
        stringBuilder.append("Fire Defense: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.FIREDEF));
        stringBuilder.append("<br>");
        stringBuilder.append("Thunder Attack: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.THUNDERATK));
        stringBuilder.append("<br>");
        stringBuilder.append("Thunder Defense: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.THUNDERDEF));
        stringBuilder.append("<br>");
        stringBuilder.append("Ice Attack: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.ICEATK));
        stringBuilder.append("<br>");
        stringBuilder.append("Ice Defense: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.ICEDEF));
        stringBuilder.append("<br>");
        stringBuilder.append("Physical Attack: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.PHYSICATK));
        stringBuilder.append("<br>");
        stringBuilder.append("Physical Defense: ");
        stringBuilder.append(job.getInitialStats().get(Statistics.PHYSICDEF));
        stringBuilder.append("<br>");

        stringBuilder.append("</html>");

        final String statisticsText = stringBuilder.toString();

        return statisticsText;
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

                statistics.setText(getStatistics());
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
}