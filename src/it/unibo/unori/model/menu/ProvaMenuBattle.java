package it.unibo.unori.model.menu;

public class ProvaMenuBattle {

    public ProvaMenuBattle() {}

    public int attack(Object enemy) {
        /*attacco standard: attacca il nemico preso in input (classe Enemy?)*/
        int damage = 0;
        return damage;
    }

    public void defend(Object ch) {
        /*difendi il personaggio ch in input (classe personaggio?)*/
    }

    public void openObjects() {
        /*apri menu degli oggetti*/
    }

    public int magic() {
        /*utilizza una magia*/
        int damage = 0;
        return damage;
    }

    public void specialAttack() {
        /*utilizza un attaco speciale, se e solo se la barra è piena*/
        /*if(barraPiena) {
            this.attack(all enemies)*tot;
        }*/
    }

    public void runAway() {
        /*fuggi*/
    }
}
