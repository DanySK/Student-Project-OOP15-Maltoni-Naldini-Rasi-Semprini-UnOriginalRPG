package it.unibo.unori.view.tests;

import java.util.LinkedList;
import java.util.List;

import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.exceptions.MaxHeroException;
import it.unibo.unori.model.character.factory.FoesFindable;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.view.View;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.BattleLayer;

/**
 *
 * This class is used to test the in-game menu.
 *
 */
public class BattleLayerTest {
    private final View view = new View();

    /**
     * Tests the in-game menu.
     */
    public BattleLayerTest() {
        try {
            createParty();
        } catch (IllegalArgumentException | MaxHeroException e) {
            System.out.println("Illegal argument exception | max hero exception");
        }

        final List<Foe> foeList = new LinkedList<Foe>();

        foeList.add(new FoeImpl(10, "Foe1", "/sprites/foes/stregone2.png", FoesFindable.STREGONE));
        foeList.add(new FoeImpl(10, "Foe2", "/sprites/foes/bambino.png", FoesFindable.BAMBINO));
        foeList.add(new FoeImpl(10, "Foe3", "/sprites/foes/cavaliere.png", FoesFindable.EROE_CADUTO));

        BattleLayer battleLayer;

        try {
            battleLayer = new BattleLayer(SingletonParty.getParty().getHeroTeam(), new FoeSquadImpl(foeList),
                    SingletonParty.getParty().getPartyBag());

            view.push(battleLayer);
            view.resizeTo(battleLayer);

            battleLayer.newTurn();
        } catch (final IllegalArgumentException e) {
            System.out.println("Illegal argument");
        } catch (final SpriteNotFoundException e) {
            System.out.println("Sprite not found");
        }
    }

    private void createParty() throws IllegalArgumentException, MaxHeroException {
        SingletonParty.getParty().getHeroTeam().addHero(new HeroImpl("Hero1", Jobs.COOK));
        SingletonParty.getParty().getHeroTeam().addHero(new HeroImpl("Hero2", Jobs.CLOWN));

        SingletonParty.getParty().getPartyBag().storeItem(new ItemImpl("Item1", "Desc1"));
        SingletonParty.getParty().getPartyBag().storeItem(new ItemImpl("Item2", "Desc2"));
    }

    /**
     * Runs this test.
     */
    public void run() {
        view.run();
    }

    /**
     * Starts this test.
     *
     * @param args
     *            command line arguments
     */
    public static void main(final String... args) {
        final BattleLayerTest battleTest = new BattleLayerTest();
        battleTest.run();
    }
}
