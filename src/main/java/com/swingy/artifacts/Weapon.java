package com.swingy.artifacts;

import javax.validation.constraints.NotNull;

public abstract class Weapon implements Artifact {

    @NotNull
    public int attackIncrease;

    public int defenceIncrease = 0;
    public int hitPointsIncrease = 0;

    public Weapon() {}
}
