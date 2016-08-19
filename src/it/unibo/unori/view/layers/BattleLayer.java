package it.unibo.unori.view.layers;

import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.sprites.JobSprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * A game battle.
 *
 */
public class BattleLayer extends Layer {
    private static final long serialVersionUID = 1L;

    private String dialogue;
    private boolean dialogueActive;

    private final List<BufferedImage> heroSprites = new LinkedList<BufferedImage>();
    private final List<BufferedImage> foeSprites = new LinkedList<BufferedImage>();

    /**
     * Creates a battle.
     * @param heroTeam the hero team to be displayed
     * @param foeTeam the foe team to be displayed
     * @param bag the bag of the party
     */
    public BattleLayer(final HeroTeam heroTeam, final FoeSquad foeTeam, final Bag bag) {
        super();

        for (final Hero hero : heroTeam.getAliveHeroes()) {
            final String heroSpriteSheetPath = hero.getBattleFrame();

            try {
                heroSprites.add(getHeroSprite(heroSpriteSheetPath));
            } catch (final SpriteNotFoundException e) {
                // TODO
            }
        }

        for (final Foe foe : foeTeam.getAliveFoes()) {
            final String foeSpritePath = foe.getBattleFrame();

            try {
                foeSprites.add(getFoeSprite(foeSpritePath));
            } catch (final SpriteNotFoundException e) {
                // TODO
            }
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        for (final BufferedImage hero : heroSprites) {
            // g.drawImage(hero, x, y, observer)
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

        return spriteSheet.getSubimage(JobSprite.BATTLE.getPosition().x,
                                       JobSprite.BATTLE.getPosition().y,
                                       JobSprite.BATTLE.getDimension().width,
                                       JobSprite.BATTLE.getDimension().height);
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
        this.dialogue = dialogue;
        dialogueActive = true;
    }

    /**
     * Hide dialogue.
     */
    public void hideDialogue() {
        dialogueActive = false;
    }

    /**
     * Create new turn.
     */
    public void newTurn() {

    }

    /**
     * Update the view.
     */
    public void updateView() {

    }
}
