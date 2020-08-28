package com.swingy.characters.villains;

public abstract class Villain {
    public int power;
    public int[] currentPosition;

    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getPower() {
        return power;
    }
}
