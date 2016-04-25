package it.unibo.unori.model.character;

import java.io.Serializable;

import it.unibo.unori.model.menu.DummyMenu;

/**
 * Interface for a non playable character.
 *
 */
public interface Npc extends Serializable {

    /**
     * Create the dialogue with the NPC.
     * @return Dialogue with NPC
     */
    DummyMenu getDialogue();
}
