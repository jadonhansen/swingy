package com.swingy.artifacts;

public abstract class Weapon implements Artifact {

    public int attackIncrease;
    public int defenceIncrease = 0;
    public int hitPointsIncrease = 0;

    public Weapon(int strength) {
        attackIncrease = strength;
    } //determine algo for applying strength
}
