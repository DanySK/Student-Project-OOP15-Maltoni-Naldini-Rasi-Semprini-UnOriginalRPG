package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.maps.exceptions.NoNPCFoundException;
import it.unibo.unori.model.menu.DummyMenu;

/**
 * An Extension of the SimpleCellImpl class to accomplish a dialogue with a
 * Non-Playable-Character(NPC).
 *
 */
public class NPCCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1377338158619069850L;
    private final Npc npc;


    /**
     * Constructor.
     * 
     * @param frame
     *            the frame to pass
     *@param npc
     *          the npc who will generate the dialogue with the party
     */
    public NPCCellImpl(final Object frame, final Npc npc) {
        super(frame, CellState.BLOCKED);
        this.npc = npc;
    }

    /**
     * Override the SimpleCellImpl method.
     * This new implementation of the method no longer throws always an Exception,
     * but instead get the dialogue from the npc field.
     * 
     */
    @Override
    public DummyMenu talkToNpc() throws NoNPCFoundException {
        return npc.getDialogue();
    }

}
