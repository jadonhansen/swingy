package com.swingy.artifacts;

public class Batmobile extends Armor implements Artifact {

    public Batmobile(int strength) {
        this.defenceIncrease = strength;
    }

    public Batmobile(int attack, int defense, int hitPoints) {
        this.defenceIncrease = defense;
    }

    @Override
    public String toString() {
        return "Batmobile";
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
