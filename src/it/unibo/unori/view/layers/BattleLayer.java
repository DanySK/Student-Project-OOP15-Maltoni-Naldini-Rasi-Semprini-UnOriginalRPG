package it.unibo.unori.view.layers;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.controller.action.InteractAction;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;

import it.unibo.unori.view.sprites.JobSprite;
import it.unibo.unori.view.layers.battle.BattleMainMenu;
import it.unibo.unori.view.layers.common.MenuStack;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

import java.awt.image.BufferedImage;

/**
 *
 * A game battle.
 *
 */
public class BattleLayer extends Layer {
    private static final long serialVersionUID = 1L;

    private static final int BORDER = 5;
    private static final Dimension SIZE = MapLayer.SIZE;

    private String dialogueText;
    private boolean dialogueActive;

    private final Bag bag;
    private final FoeSquad foeTeam;
    private final HeroTeam heroTeam;

    private BufferedImage background;

    private final MenuStack battleMenuStack = new MenuStack();

    private final Map<Hero, BufferedImage> heroSprites = new HashMap<Hero, BufferedImage>();
    private final Map<Foe, BufferedImage> foeSprites = new HashMap<Foe, BufferedImage>();

    /**
     * Creates a battle.
     *
     * @param heroTeam
     *            the hero team to be displayed
     * @param foeTeam
     *            the foe team to be displayed
     * @param bag
     *            the bag of the party
     * @throws SpriteNotFoundException
     *             some sprites are not found
     */
    public BattleLayer(final HeroTeam heroTeam, final FoeSquad foeTeam, final Bag bag) throws SpriteNotFoundException {
        super();

        this.bag = bag;
        this.foeTeam = foeTeam;
        this.heroTeam = heroTeam;

        for (final Hero hero : heroTeam.getAllHeroes()) {
            heroSprites.put(hero, getHeroSprite(hero.getBattleFrame()));
        }

        for (final Foe foe : foeTeam.getAllFoes()) {
            foeSprites.put(foe, getFoeSprite(foe.getBattleFrame()));
        }

        try {
            background = ImageIO.read(new File("res/sprites/battle/background.png"));
        } catch (final IOException e) {
            background = null;
            throw new SpriteNotFoundException("res/sprites/battle/backgdound.png");
        }

        this.setBackground(Color.WHITE);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        battleMenuStack.setPreferredSize(new Dimension(SIZE.width, SIZE.height));
        this.add(battleMenuStack);

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
        actionMap.put("ENTER", new InteractAction());
    }

    /**
     * Create new turn.
     */
    public void newTurn() {
        battleMenuStack.push(new BattleMainMenu(battleMenuStack, heroTeam, foeTeam, bag, BORDER,
                SIZE.height - BattleMainMenu.SIZE.height - BORDER * 2));
    }

    private void drawHero(final Graphics g, final int x, final int y, final Hero hero, final BufferedImage sprite) {

        final String name = hero.getName() + ", Livello: " + hero.getLevel();
        final String status = "Status: " + hero.getStatus().name();
        final String health = "HP: " + hero.getRemainingHP() + "/" + hero.getTotalHP();
        final String magic = "MP: " + hero.getCurrentMP() + "/" + hero.getTotalMP();
        final String special = "Attacco Speciale: " + hero.getCurrentBar() + "/" + hero.getTotBar();

        int newY = y;
        final int yStep = 15;

        g.setColor(Color.WHITE);

        g.drawString(name, x + 32 - g.getFontMetrics().stringWidth(name), newY);
        newY += yStep;
        g.drawString(status, x + 32 - g.getFontMetrics().stringWidth(status), newY);
        newY += yStep;
        g.drawString(health, x + 32 - g.getFontMetrics().stringWidth(health), newY);
        newY += yStep;
        g.drawString(magic, x + 32 - g.getFontMetrics().stringWidth(magic), newY);
        newY += yStep;
        g.drawString(special, x + 32 - g.getFontMetrics().stringWidth(special), newY);
        newY += yStep;

        g.drawImage(sprite, x, newY, sprite.getWidth(), sprite.getHeight(), null);
    }

    private int drawFoe(final Graphics g, final int x, final int y, final Foe foe, final BufferedImage sprite) {

        final String name = foe.getName() + ", Livello: " + foe.getLevel();
        final String status = "Status: " + foe.getStatus().name();
        final String health = "HP: " + foe.getRemainingHP() + "/" + foe.getTotalHP();

        int newY = y;
        final int yStep = 15;

        g.setColor(Color.WHITE);

        g.drawString(name, x, newY);
        newY += yStep;
        g.drawString(status, x, newY);
        newY += yStep;
        g.drawString(health, x, newY);
        newY += yStep;

        g.drawImage(sprite, x, newY, sprite.getWidth(), sprite.getHeight(), null);

        return sprite.getHeight();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);

        final int yStart = 50;
        final int xStart = SIZE.width - 100;

        int y = yStart;
        int x = xStart;

        final int yStepHero = 145;

        for (final Hero hero : heroTeam.getAliveHeroes()) {
            drawHero(g, x, y, hero, heroSprites.get(hero));
            y += yStepHero;
        }

        y = yStart;

        final int yStep = 65;

        for (final Foe foe : foeTeam.getAliveFoes()) {
            final int step = drawFoe(g, 50, y, foe, foeSprites.get(foe));

            y += step + yStep;
        }

        if (dialogueActive) {
            final int border = 10;
            final int height = 100;
            final double leftBorder = 4.5;

            x = border * 2;
            y = SIZE.height - height - border / 2;

            g.setColor(Color.WHITE);
            g.fillRect(border, SIZE.height - border - height, SIZE.width - border * 2, height);

            g.setColor(Color.BLACK);
            final StringBuilder stringBuilder = new StringBuilder();

            for (final char c : dialogueText.toCharArray()) {
                if (c == '\n') {
                    y += g.getFontMetrics().getHeight();
                    g.drawString(stringBuilder.toString(), x, y);

                    stringBuilder.setLength(0);
                } else if (g.getFontMetrics().stringWidth(stringBuilder.toString() + c) > SIZE.width
                        - border * leftBorder) {
                    y += g.getFontMetrics().getHeight();
                    g.drawString(stringBuilder.toString(), x, y);

                    stringBuilder.setLength(0);
                    stringBuilder.append(c);
                } else {
                    stringBuilder.append(c);
                }
            }
            g.drawString(stringBuilder.toString(), x, y + g.getFontMetrics().getHeight());
        }
    }

    private BufferedImage getHeroSprite(final String spriteSheetPath) throws SpriteNotFoundException {
        BufferedImage spriteSheet;

        try {
            spriteSheet = ImageIO.read(new File(spriteSheetPath));
        } catch (final IOException e) {
            spriteSheet = null;
            throw new SpriteNotFoundException(spriteSheetPath);
        }

        return spriteSheet.getSubimage(JobSprite.BATTLE.getPosition().x, JobSprite.BATTLE.getPosition().y,
                JobSprite.BATTLE.getDimension().width, JobSprite.BATTLE.getDimension().height);
    }

    private BufferedImage getFoeSprite(final String spritePath) throws SpriteNotFoundException {
        BufferedImage sprite;

        try {
            sprite = ImageIO.read(new File(spritePath));
        } catch (final IOException e) {
            sprite = null;
            throw new SpriteNotFoundException(spritePath);
        }

        return sprite;
    }

    /**
     * Show a dialogue.
     *
     * @param dialogue
     *            the dialogue to be shown
     */
    public void showDialogue(final String dialogue) {
        this.dialogueText = dialogue;
        dialogueActive = true;

        repaint();
    }

    /**
     * Hide dialogue.
     */
    public void hideDialogue() {
        dialogueActive = false;

        repaint();
    }

    /**
     * Update the view.
     */
    public void updateView() {

    }
}
