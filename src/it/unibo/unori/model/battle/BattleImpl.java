package it.unibo.unori.model.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.exceptions.FailedException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.battle.utility.MagicLogics;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;
import it.unibo.unori.model.menu.Dialogue;
import it.unibo.unori.model.menu.DialogueInterface;

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
    private Optional<String> outCome;
    private final Bag itemBag;
    
    private static final String OUTCOMEPOSITIVE = "Complimenti, hai vinto la Battaglia!!";
    private static final String OUTCOMENEGATIVE = "Peccato... Sei stato sconfitto";
    private static final String CHANGETURN = "E' il turno di ";
    private static final String PRESENTATION = "E' iniziata la lotta contro ";
    
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
        this.outCome = Optional.empty();
    }
    
    /**
     * Constructor for test purposes.
     * @param battle a battle to initialize a new Battle with.
     */
    public BattleImpl(final Battle battle) {
        this.enemies = battle.getEnemies();
        this.squad = battle.getSquad();
        this.itemBag = battle.getItemBag();
        this.heroOnTurn = battle.getHeroOnTurn();
        this.foeOnTurn = battle.getFoeOnTurn();
        this.over = battle.isOver();
        this.outCome = Optional.of(battle.getOutCome());
    }
    
    private void checkWhoAttacks() {
        if (this.foeOnTurn.getStatus().equals(Status.DEAD) 
                || this.heroOnTurn.getStatus().equals(Status.DEAD)) {
            throw new IllegalStateException();
        }
    }
    
    private Optional<Boolean> setOver() {
        if (this.enemies.getAliveFoes().size() == 0) {
            this.over = true;
            this.outCome = Optional.of(OUTCOMEPOSITIVE);
            return Optional.of(true);
        } else if (this.squad.getAliveHeroes().size() == 0) {
            this.over = true;
            this.outCome = Optional.of(OUTCOMENEGATIVE);
            return Optional.of(false);
        } else {
            this.over = false;
            this.outCome = Optional.empty();
            return Optional.empty();
        }
    }
    
    private int getMediumLevelFoes() {
        int mediumLevel = 0;
        for (final Foe h : this.enemies.getAllFoes()) {
            mediumLevel += h.getLevel();
        }
        mediumLevel /= this.enemies.getAllFoes().size();
        return mediumLevel;
    }
    
    private int getMediumDefenseFoes() {
        int medium = 0;
        for (final Foe h : this.enemies.getAliveFoes()) {
            medium += h.getDefense();
        }
        medium /= this.enemies.getAliveFoes().size();
        return medium / 2;
    }
    
    private boolean setUndefendedAndNotify(final Character whoSuffers) {
        if (whoSuffers instanceof Hero) {
            if (((Hero) whoSuffers).isDefended()) {
                ((Hero) whoSuffers).setUndefended();
                return true;
            } else {
                this.squad.getAliveHeroes().forEach(h -> {
                    if (h.isDefended()) {
                        h.setUndefended();
                    }
                });
                return false;
            }
        } else {
            return false;
        }
    }
    
    @Override
    public DialogueInterface runAway() throws CantEscapeException {
        if (BattleLogics.canEscape(this.getHeroOnTurn().getLevel(), this.getFoeOnTurn().getLevel())) {
            this.over = true;
            return new Dialogue("Sei riuscito a fuggire!");
        } else {
            throw new CantEscapeException();
        }
    }

    @Override
    public DialogueInterface attack(final boolean whosFirst) throws NoWeaponException {
        this.checkWhoAttacks();
        final Character whoAttacks;
        final Character whoSuffers;
        whoAttacks = whosFirst ? this.heroOnTurn : this.foeOnTurn;
        whoSuffers = whosFirst ? this.foeOnTurn : this.heroOnTurn;
        if (this.setUndefendedAndNotify(whoSuffers)) {
            return new Dialogue(whoSuffers.getName() + " e' difeso! Non subisce danni");
        }
        final int damage = 
                MagicLogics.mergeAtkAndDef(whoAttacks, whoSuffers, whoAttacks.getWeapon());
        
        whoSuffers.takeDamage(damage);
        String toReturn = whoAttacks.getName() + " attacca " + whoSuffers.getName() + " con "
                + whoAttacks.getWeapon() + "! E causa un danno pari a " + damage + " HP!";
        if (whosFirst) {
            this.heroOnTurn.setCurrentBar(
                    BattleLogics.toFillSpecialBar(this.foeOnTurn, false, this.heroOnTurn));
            toReturn = toReturn.concat(" " + this.enemies.defeatFoe(this.foeOnTurn));
            if (this.enemies.isDefeated(this.foeOnTurn)) {
                this.setOver();
                if (!this.over) {
                    this.setFoeOnTurn(this.enemies.getNextFoe());
                }
                return new Dialogue(toReturn);
            }
        } else {
            toReturn = toReturn.concat(" " + this.squad.defeatHero(this.heroOnTurn));
            if (this.squad.isDefeated(this.heroOnTurn)) {
                this.setOver();
                if (!this.over) {
                    this.setHeroOnTUrn(this.squad.getNextHero());
                }
                return new Dialogue(toReturn);
            }
        }
        if (whoSuffers.getStatus().equals(Status.NONE)) {
            whoSuffers.setStatus(BattleLogics.causingStatus(whoAttacks,
                    whoSuffers));
        }
        if (!whoSuffers.getStatus().equals(Status.NONE)) {
            toReturn = toReturn.concat(" " + whoSuffers.getName() 
                    + " ha subito un cambiamento di Stato! Ora � " + whoSuffers.getStatus());
        }

        return new Dialogue(toReturn);
    }

    @Override
    public DialogueInterface defend(final Hero friend) throws NotDefendableException {
        this.checkWhoAttacks();
        if (friend.getStatus() == Status.NOT_DEFENDABLE) {
            throw new NotDefendableException();
        }
        if (friend.isDefended()) {
            return new Dialogue(friend.getName() + " e' gia'� difeso! Peccato...");
        } else {
            friend.setDefended();
            return new Dialogue("Hai difeso " + friend.getName());
        }
    }

    @Override
    public DialogueInterface usePotion(final Hero my, final Potion toUse) 
            throws ItemNotFoundException, HeroDeadException, HeroNotDeadException {
        this.checkWhoAttacks();
        if (this.itemBag.contains(toUse)) {
            toUse.using(my);
            return new Dialogue("Hai usato " + toUse.getName() + " su " + my.getName() + "!");
        } else {
            throw new ItemNotFoundException();
        }
    }
    
    @Override
    public DialogueInterface foeUsesRestore(final Statistics statToRestore) {
        return new Dialogue(this.foeOnTurn.getName() + " ha rigenerato i suoi "
                + this.foeOnTurn.restoreInBattle(statToRestore) + ", ora � pi� in forma!");
        
    }

    @Override
    public DialogueInterface specialAttack() throws BarNotFullException {
        this.checkWhoAttacks();
        final List<String> list = new ArrayList<>();
        if (this.heroOnTurn.getCurrentBar() == this.heroOnTurn.getTotBar()) {
            final String toReturn = 
                    this.heroOnTurn.getName() + " ha usato l'attacco speciale!";
            list.add(toReturn);
            int toAddAtk;
            try {
                toAddAtk = this.heroOnTurn.getWeapon().getPhysicalAtk();
            } catch (NoWeaponException e1) {
                toAddAtk = 0;
            }
            
            final int damage = 
                    BattleLogics.specialAttackCalc(this.heroOnTurn.getLevel(),
                            this.heroOnTurn.getAttack() + (toAddAtk - this.getMediumDefenseFoes()));
            this.enemies.getAliveFoes().forEach(e -> {
                String toAdd;
                e.takeDamage(damage);
                toAdd = " " + e.getName() + " subisce un danno di " + damage + " HP! " 
                        + this.enemies.defeatFoe(e);
                list.add(toAdd + "\n");
                if (this.enemies.isDefeated(e)) {
                    this.setOver();
                }
            });
            if (!this.isOver()) {
                this.setFoeOnTurn(this.enemies.getNextFoe());
            }
            this.heroOnTurn.resetSpecialBar();
            String finale = "";
            for (final String s : list) {
                finale = finale.concat(s);
            }
            return new Dialogue(finale);
        } else {
            throw new BarNotFullException();
        }
    }

    @Override
    public DialogueInterface useMagicAttack(final MagicAttackInterface m,
            final Foe enemy, final boolean whosFirst)
            throws NotEnoughMPExcpetion, MagicNotFoundException {
        this.checkWhoAttacks();
        final Character whoAttacks;
        final Character whoSuffers;
        whoAttacks = whosFirst ? this.heroOnTurn : enemy;
        whoSuffers = whosFirst ? enemy : this.heroOnTurn;
        String toShow = whoAttacks.getName() + " usa una Magia!";
        if (this.setUndefendedAndNotify(whoSuffers)) {
            return new Dialogue(toShow + " " + whoSuffers.getName() + " e' difeso! Non subisce danni");
        }
        if (whoAttacks.getMagics().contains(m)) {
            if (whoAttacks.getCurrentMP() > m.getMPRequired()) {
                whoAttacks.consumeMP(m.getMPRequired());
            } else {
                throw new NotEnoughMPExcpetion();
            }
            
            try {
                final int damage;
                damage = MagicLogics.calculateMagic(whoAttacks, whoSuffers, m);
                whoSuffers.takeDamage(damage);
                toShow = toShow.concat(" " + whoAttacks.getName() + " " + m.getStringToShow() 
                + " E causa un danno di " + damage + " HP a " + whoSuffers.getName() + "!");
                if (whosFirst) {
                    this.heroOnTurn.setCurrentBar(
                            BattleLogics.toFillSpecialBar(enemy, true, this.heroOnTurn));
                    toShow = toShow.concat(" " + this.enemies.defeatFoe(enemy));
                    if (this.enemies.isDefeated(enemy)) {
                        this.setOver();
                        if (!this.over) {
                            this.setFoeOnTurn(this.enemies.getNextFoe());
                        }
                    }
                } else {
                    toShow = toShow.concat(" " + this.squad.defeatHero(this.heroOnTurn));
                    if (this.squad.isDefeated(this.heroOnTurn)) {
                        this.setOver();
                        if (!this.isOver()) {
                            this.setHeroOnTUrn(this.squad.getNextHero());
                        }
                    }
                }
            } catch (FailedException e) {
                toShow = toShow.concat(" Attacco Fallito!");
            }
            return new Dialogue(toShow);
        } else {
            throw new MagicNotFoundException();
        }
       
        
    }

    @Override
    public boolean isOver() {
        return this.over;
    }

    @Override
    public DialogueInterface acquireExp() {
        if (this.isOver() && this.outCome.equals(Optional.of(OUTCOMEPOSITIVE))) {
            final List<String> toReturn = new ArrayList<>();
            this.squad.getAliveHeroes().forEach(h -> {
                BattleLogics.expAcquired(this.squad, this.getMediumLevelFoes(),
                        this.squad.getAliveHeroes().size()).entrySet().forEach(e -> {
                            if (h.equals(e.getKey())) {
                                h.addExp(e.getValue());
                                toReturn.add(h.getName() + " riceve " 
                                + e.getValue() + " punti Esperienza! ");
                            }
                        });
            });
            String s = "";
            for (final String str : toReturn) {
                s = s.concat(str);
            }
            return new Dialogue(s);
        } else {
            throw new IllegalStateException();
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
        return CHANGETURN + h.getName();
    }
    
    @Override
    public String setFoeOnTurn(final Foe en) {
        this.foeOnTurn = en;
        return CHANGETURN + en.getName() + " nemico"; 
    }

    @Override
    public String getOutCome() throws IllegalStateException {
        if (!this.isOver()) {
            throw new IllegalStateException();
        }
        return this.outCome.get();
    }
    
    @Override
    public DialogueInterface getPresentation() {
        return new Dialogue(PRESENTATION + this.enemies.getNameOfSquad());
    }
}
