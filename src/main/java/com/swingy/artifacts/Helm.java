package com.swingy.artifacts;

import javax.validation.constraints.NotNull;

public abstract class Helm implements Artifact {

    @NotNull
    public int hitPointsIncrease;

    public int defenceIncrease = 0;
    public int attackIncrease = 0;

    public Helm() {}
}
