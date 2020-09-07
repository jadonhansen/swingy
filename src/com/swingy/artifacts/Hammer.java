package com.swingy.artifacts;

public class Hammer extends Weapon implements Artifact {

    public Hammer(int strength) {
        this.attackIncrease = strength;
    }

    public Hammer(int attack, int defense, int hitPoints) {
        this.attackIncrease = attack;
    }

    @Override
    public String toString() {
        return "Hammer: Attack=" + attackIncrease + ", Defence=" + defenceIncrease + ", Hit Points=" + hitPointsIncrease;
    }

    @Override
    public int getAttack() {
        return attackIncrease;
    }

    @Override
    public int getDefense() {
        return defenceIncrease;
    }

    @Override
    public int getHitPoints() {
        return hitPointsIncrease;
    }
}
