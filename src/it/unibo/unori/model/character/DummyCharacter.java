package it.unibo.unori.model.character;

import it.unibo.unori.model.menu.DummyMenu;

public class DummyCharacter implements Character{
/**
 * something.
 * @return something.
 */
    public DummyMenu createMenu() {
        return new DummyMenu();
    }

}
