package it.unibo.unori.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import it.unibo.unori.controller.StateMachineStack;
import it.unibo.unori.controller.StateMachineStackImpl;
import it.unibo.unori.controller.state.CharacterSelectionState;
import it.unibo.unori.controller.state.GameState;
import it.unibo.unori.controller.state.MainMenuState;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;

/**
 * JUnit test class for interface
 * {@link it.unibo.unori.controller.StateMachineStack} and its implementation
 * {@link it.unibo.unori.controller.StateMachineStackImpl}.
 */
public class StateMachineStackImplTest {

    /**
     * This method tests methods {@link StateMachineStackImpl#push(GameState)},
     * {@link StateMachineStackImpl#peek()} and
     * {@link StateMachineStackImpl#pop()}.
     * @throws SpriteNotFoundException  if something wrong happens
     */
    @Test
    public void testStateMachineStack() throws SpriteNotFoundException {
        final StateMachineStack stack = new StateMachineStackImpl();

        final GameState mainMenu1 = new MainMenuState();
        final GameState mainMenu2 = new MainMenuState();
        final GameState charSelect = new CharacterSelectionState();

        stack.pushAndRender(mainMenu1);
        stack.pushAndRender(mainMenu2);
        assertNotSame(mainMenu1, mainMenu2);
        stack.pushAndRender(charSelect);

        assertEquals(charSelect, stack.peek());
        assertEquals(charSelect, stack.pop());
        
        assertEquals(mainMenu2, stack.peek());
        assertEquals(mainMenu2, stack.pop());

        assertEquals(mainMenu1, stack.peek());
        assertEquals(mainMenu1, stack.pop());
    }

}
