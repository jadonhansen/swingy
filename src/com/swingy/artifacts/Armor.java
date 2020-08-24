package com.swingy.artifacts;

public abstract class Armor implements Artifact {

    public int defenceIncrease;
    public int attackIncrease = 0;
    public int hitPointsIncrease = 0;
    public Armor(int strength) {
        defenceIncrease = strength;
    } //determine algo for applying strength

}
