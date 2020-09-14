package com.swingy.characters.villains;

import javax.validation.constraints.NotNull;

public abstract class Villain {

    @NotNull
    protected int power;
    @NotNull
    protected int[] currentPosition = new int[2];

    public void setCurrentPosition(int x, int y) {
        currentPosition[0] = x;
        currentPosition[1] = y;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public int getPower() {
        return power;
    }
}
