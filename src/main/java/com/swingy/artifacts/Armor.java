package com.swingy.artifacts;

import javax.validation.constraints.NotNull;

public abstract class Armor implements Artifact {

    @NotNull
    public int defenceIncrease;

    public int attackIncrease = 0;
    public int hitPointsIncrease = 0;

    public Armor() {}
}
