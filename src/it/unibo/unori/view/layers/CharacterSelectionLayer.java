package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.Button;
import it.unibo.unori.view.sprites.JobSprite;
import it.unibo.unori.model.character.jobs.Jobs;

import java.util.List;
import java.util.LinkedList;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
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

    private JLabel sprite;
    private Jobs job = Jobs.values()[0];
    private Map<String, Jobs> party = new HashMap<String, Jobs>();

    /**
     * Displays the character-selection menu.
     * @param maxHero the number of heroes in the party
     * @param button the button to be displayed when finished
     */
    public CharacterSelectionLayer(final int maxHero, final Button button) {
        super();

        this.setBackground(BACKGROUND_COLOR);

        this.setPreferredSize(SIZE);
        this.setBounds(0, 0, SIZE.width, SIZE.height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel partyPanel = new JPanel();
        partyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        partyPanel.setBackground(BACKGROUND_COLOR);

        final JTextField textField = new JTextField(20);
        textField.setMaximumSize(textField.getPreferredSize());
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (party.size() < maxHero) {
                    party.add(job);

                    partyPanel.add(new JLabel(new ImageIcon(getSprite())));
                    partyPanel.validate();
                }
                if (party.size() == maxHero) {
                    button.setEnabled(true);
                }
            }
        });

        final JPanel spritePanel = new JPanel();
        spritePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        spritePanel.setBackground(BACKGROUND_COLOR);

        final BasicArrowButton left = new BasicArrowButton(SwingConstants.WEST);
        left.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                previousJob();
            }
        });

        sprite = new JLabel(new ImageIcon(getSprite()));

        final BasicArrowButton right = new BasicArrowButton(SwingConstants.EAST);
        right.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                nextJob();
            }
        });

        final JLabel statistics = new JLabel("Statistics");
        statistics.setForeground(Color.WHITE);
        statistics.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(textField);

        spritePanel.add(left);
        spritePanel.add(sprite);
        spritePanel.add(right);

        spritePanel.setMaximumSize(spritePanel.getPreferredSize());

        this.add(spritePanel);

        this.add(statistics);

        this.add(partyPanel);

        this.add(button);
        button.setEnabled(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * @return the party chosen by the user
     */
    public List<Jobs> getParty() {
        return party;
    }

    private BufferedImage getSprite() {
        BufferedImage spriteSheet;

        try {
            spriteSheet = ImageIO.read(new File(job.getBattleFrame()));
        } catch (IOException e) {
            spriteSheet = null;
        }

        return spriteSheet.getSubimage(JobSprite.BATTLE.getPosition().x,
                                       JobSprite.BATTLE.getPosition().y,
                                       JobSprite.BATTLE.getDimension().width,
                                       JobSprite.BATTLE.getDimension().height);
    }

    private void nextJob() {
        job = Jobs.values()[(job.ordinal() + 1)  % (Jobs.values().length - 1)];
        sprite.setIcon(new ImageIcon(getSprite()));
    }

    private void previousJob() {
        if (job.ordinal() - 1 >= 0) {
            job = Jobs.values()[job.ordinal() - 1];
            sprite.setIcon(new ImageIcon(getSprite()));
        } else {
            job = Jobs.values()[Jobs.values().length - 2];
            sprite.setIcon(new ImageIcon(getSprite()));
        }
    }

    public static void main(final String... args) {
        final View view = new View();
        final Button button = new Button("Test");
        final JPanel characterSelection = new CharacterSelectionLayer(5, button);

        view.push(characterSelection);
        view.resizeTo(characterSelection);

        view.run();
        view.centerToScreen();
    }
}