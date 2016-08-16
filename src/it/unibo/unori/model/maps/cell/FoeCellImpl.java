package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.character.Foe;

/**
 * Desing a Cell that, if the party interact, start a battle.
 *
 */
public class FoeCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -4153132398113019125L;
    private final Foe foe;

    /**
     * Standard constructor for BossCell.
     * @param path
     *          path of the boss sprite.
     * @param foe
     *          boss implementation.
     */
    public FoeCellImpl(final String path, final Foe foe) {
        super(path, CellState.BLOCKED);
        this.foe = foe;
    }

    @Override
    public Foe getBoss() throws IllegalStateException {
        return this.foe;
    }

}
