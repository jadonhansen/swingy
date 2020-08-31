package com.swingy.characters.villains;

public abstract class Villain {
    public int power;
    public int[] currentPosition = new int[2];

    public void setCurrentPosition(int x, int y) {
        currentPosition[0] = x;
        currentPosition[1] = y;
    }

    public int getPower() {
        return power;
    }
}
