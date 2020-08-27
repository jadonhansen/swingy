package com.swingy.artifacts;

public class Lightsaber extends Helm implements Artifact {

    public Lightsaber(int strength) {
        //        determine algo for strength from villain
        this.hitPointsIncrease = strength;
    }

    public Lightsaber(int attack, int defense, int hitPoints) {
        this.hitPointsIncrease = hitPoints;
    }

    @Override
    public String toString() {
        return "Lightsaber";
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
