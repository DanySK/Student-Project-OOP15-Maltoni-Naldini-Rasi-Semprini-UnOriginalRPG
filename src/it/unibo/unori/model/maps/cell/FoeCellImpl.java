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
    public static final String ENC = "Parte la sfida con ";
    private static final long serialVersionUID = -4153132398113019125L;
    private static final int PRIME = 31;
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

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + ((foe == null) ? 0 : foe.hashCode());
        return result;
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof FoeCellImpl)) {
            return false;
        }
        final FoeCellImpl other = (FoeCellImpl) obj;
        if (foe == null) {
            return other.foe == null;
        } else  {
            return foe.equals(other.foe);
        }
   }
}
