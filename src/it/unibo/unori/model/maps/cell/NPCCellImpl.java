package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.maps.exceptions.NoNPCFoundException;
import it.unibo.unori.model.menu.DialogueInterface;

/**
 * Class to handle a Non Playable Character on the map, extends the 
 * SimpleCellImpl class, when the party interact with the cell, it 
 * returns the dialogue of that NPC.
 */
public class NPCCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1377338158619069850L;
    private static final int PRIME = 31;
    private final Npc npc;


    /**
     * Constructor.
     * 
     * @param path
     *            the path of the image to set
     *@param npc
     *          the Non Playable Character who will generate the dialogue with the party
     */
    public NPCCellImpl(final String path, final Npc npc) {
        super(path, CellState.BLOCKED);
        this.npc = npc;
    }


    @Override
    public DialogueInterface talkToNpc() throws NoNPCFoundException {
        npc.getDialogue().resetNextToShow();
        return npc.getDialogue();
    }

    /**
     * Getter for the npc, needed for the serialization.
     * @return
     *      the npc object
     */
    public Npc getNpc() {
        return this.npc;
    }



    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + ((npc == null) ? 0 : npc.hashCode());
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
        if (!(obj instanceof NPCCellImpl)) {
            return false;
        }
        final NPCCellImpl other = (NPCCellImpl) obj;
        if (npc == null) {
            return other.npc == null;
        } else {
            return this.npc.equals(other.npc);
        }
    }

}
