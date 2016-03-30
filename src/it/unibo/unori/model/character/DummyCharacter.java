package it.unibo.unori.model.character;

import java.io.Serializable;

import it.unibo.unori.model.menu.DummyMenu;

public class DummyCharacter implements Serializable{
/**
 * something.
 * @return something.
 */
    public DummyMenu createMenu() {
        return new DummyMenu();
    }

}
