package it.unibo.unori.model.battle;

import java.util.List;

import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {

    private final HeroTeam squad;
    private final FoeSquad enemies;
    private Foe foeOnTurn;
    private Hero heroOnTurn;
    private boolean over;
    private final Bag itemBag;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     * @param en a List of Enemies.
     * @param bag the Item Bag.
     */
    public BattleImpl(final HeroTeam team, final FoeSquad en, final Bag bag) {
        this.squad = team;
        this.enemies = en;
        this.itemBag = bag;
        this.heroOnTurn = this.squad.getFirstHeroOnTurn();
        this.foeOnTurn = this.enemies.getFirstFoeOnTurn();
        this.over = false;
    }
    
    private Character defeated(final Character ch) {
        if (ch instanceof Foe) {
            final Foe toRemove = (Foe) ch;
            this.enemies.removeFoe(toRemove);
            if (this.enemies.getAliveFoes().size() > 0) {
                this.over = false;
            } else {
                this.over = true;
            }
        } else if (ch instanceof Hero) {
            final Hero toRemove = (Hero) ch;
            this.squad.removeHero(toRemove);
        }
        return ch;
    }
    
    private boolean isDefeated(final Character ch) {
        return ch.getRemainingHP() == 0;
    }
    
    private int getMediumLevelFoes() {
        int mediumLevel = 0;
        for (final Foe h : this.enemies.getAllFoes()) {
            mediumLevel += h.getLevel();
        }
        mediumLevel /= this.enemies.getAllFoes().size();
        return mediumLevel;
    }
    
    @Override
    public String runAway(final Foe enemy, 
            final Hero my) throws CantEscapeException {
        if (BattleLogics.canEscape(my.getLevel(), enemy.getLevel())) {
            this.over = true;
            return "Sei riuscito a fuggire!";
        } else {
            throw new CantEscapeException();
        }
    }

    @Override
    public int attack() throws NoWeaponException {
        if (BattleLogics.whosFirst(this.heroOnTurn.getSpeed(), this.foeOnTurn.getSpeed())) {
            final int atkTot = this.heroOnTurn.getAttack() 
                    + (this.heroOnTurn.getWeapon().getPhysicalAtk());
            final int damage = 
                    BattleLogics.getStandardDamage(this.heroOnTurn.getLevel(), atkTot);
            this.foeOnTurn.takeDamage(damage);
            if (this.isDefeated(this.foeOnTurn)) {
                this.defeated(this.foeOnTurn);
            } else {
                if (this.foeOnTurn.getStatus().equals(Status.NONE)) {
                    this.foeOnTurn.setStatus(BattleLogics.causingStatus(this.heroOnTurn,
                            this.foeOnTurn, true));
                }
            }
            return damage;
        } else {
            final int atkTot = this.foeOnTurn.getAttack()
                    + this.foeOnTurn.getWeapon().getPhysicalAtk();
            final int damage = BattleLogics.getStandardDamage(this.foeOnTurn.getLevel(), atkTot);
            if (this.isDefeated(this.heroOnTurn)) {
                this.defeated(this.heroOnTurn);
            } else {
                if (this.heroOnTurn.getStatus().equals(Status.NONE)) {
                    this.heroOnTurn.setStatus(BattleLogics.causingStatus(this.heroOnTurn,
                            this.foeOnTurn, false));
                }
            }
            return damage;
        }
    }

    @Override
    public String defend(final Hero friend) throws NotDefendableException {
        if (friend.getStatus() == Status.NOT_DEFENDABLE) {
            throw new NotDefendableException();
        }
        if (friend.isDefended()) {
            return friend.getName() + " e' gia'  difeso! Peccato...";
        } else {
            friend.setDefended();
            return "Hai difeso " + friend.getName();
        }
    }

    @Override
    public String usePotion(final Hero my, final Potion toUse) 
            throws ItemNotFoundException, HeroDeadException, HeroNotDeadException {
        if (this.itemBag.contains(toUse)) {
            toUse.using(my);
            return "Hai usato " + toUse.getName() + " su " + my.getName() + "!";
        } else {
            throw new ItemNotFoundException();
        }
    }
    
    @Override
    public String foeUsesRestore(final Statistics statToRestore) {
        return this.foeOnTurn.getName() + " ha rigenerato i suoi "
                + this.foeOnTurn.restoreInBattle(statToRestore) + ", ora è più in forma!";
        
    }

    @Override
    public String specialAttack() throws BarNotFullException {
        if (this.heroOnTurn.getCurrentBar() == this.heroOnTurn.getTotBar()) {
            this.heroOnTurn.resetSpecialBar();
            final int damage = 
                    BattleLogics.specialAttackCalc(this.heroOnTurn.getLevel(),
                            this.heroOnTurn.getAttack());
            this.enemies.getAllFoes().forEach(e -> {
                e.takeDamage(damage);
                if (this.isDefeated(e)) {
                    this.defeated(e);
                }
            });
            return this.heroOnTurn.getName() + "ha usato l'attacco speciale!";
        } else {
            throw new BarNotFullException();
        }
    }

    @Override
    public int useMagicAttack(final MagicAttack m, final Foe enemy)
            throws NotEnoughMPExcpetion, MagicNotFoundException {
        if (BattleLogics.whosFirst(this.heroOnTurn.getSpeed(), this.foeOnTurn.getSpeed())) {
            if (this.heroOnTurn.getMagics().contains(m)) {
                if (this.heroOnTurn.getCurrentMP() > m.getMPRequired()) {
                    this.heroOnTurn.consumeMP(m.getMPRequired());
                } else {
                    throw new NotEnoughMPExcpetion();
                }
                //TODO A lot of things.
                return 0;
            } else {
                throw new MagicNotFoundException();
            }
        } else {
            if (this.foeOnTurn.getMagics().contains(m)) {
                if (this.foeOnTurn.getCurrentMP() > m.getMPRequired()) {
                    this.foeOnTurn.consumeMP(m.getMPRequired());
                } else {
                    throw new NotEnoughMPExcpetion();
                }
                //TODO A lot of things.
                return 0;
            } else {
                throw new MagicNotFoundException();
            }
        }
        
    }

    @Override
    public boolean isOver() {
        return this.over;
    }

    @Override
    public void acquireExp() {
        if (this.isOver()) {
            final List<Integer> points =
                    BattleLogics.expAcquired(this.squad, this.getMediumLevelFoes(),
                            this.squad.getAliveHeroes().size());
            int index = 0;
            for (final Hero h : this.squad.getAliveHeroes()) {
                h.addExp(points.get(index));
                index++;
            }
        }
    }

    @Override
    public FoeSquad getEnemies() {
        return new FoeSquadImpl(this.enemies.getAllFoes());
    }

    @Override
    public HeroTeam getSquad() {
        return new HeroTeamImpl(this.squad.getAllHeroes());
    }

    @Override
    public Bag getItemBag() {
        return new BagImpl(this.itemBag);
    }
    
    @Override
    public Foe getFoeOnTurn() {
        return this.foeOnTurn;
    }
    
    @Override
    public Hero getHeroOnTurn() {
        return this.heroOnTurn;
    }
    
    @Override
    public String setHeroOnTUrn(final Hero h) {
        this.heroOnTurn = h;
        return "E' il turno di " + h.getName();
    }
    
    @Override
    public String setFoeOnTurn(final Foe en) {
        this.foeOnTurn = en;
        return "E' il turno di " + en.getName(); 
    }

}
