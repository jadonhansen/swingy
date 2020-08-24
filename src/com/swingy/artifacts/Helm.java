package com.swingy.artifacts;

public abstract class Helm implements Artifact {

    public int defenceIncrease = 0;
    public int attackIncrease = 0;
    public int hitPointsIncrease;

    public Helm(int strength) {
        hitPointsIncrease = strength;
    } //determine algo for applying strength
}
