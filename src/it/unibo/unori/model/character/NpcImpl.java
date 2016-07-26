package it.unibo.unori.model.character;

import it.unibo.unori.model.menu.Dialogue;
import it.unibo.unori.model.menu.DialogueInterface;

/**
 * Implementation of a generic NPC.
 *
 */
public class NpcImpl implements Npc {

    /**
     * 
     */
    private static final long serialVersionUID = 2784146398991125905L;
    private final DialogueInterface sentence;
    
    /**
     * Simple Constructor for an Npc.
     * All I need is the Dialogue he's going to show.
     * @param d the sentence of the Npc, in form of Dialogue.
     */
    public NpcImpl(final Dialogue d) {
        this.sentence = d;
    }

    @Override
    public DialogueInterface getDialogue() {
        return this.sentence;
    }

}
