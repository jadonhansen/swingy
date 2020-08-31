package com.swingy.characters.heroes;

import com.swingy.artifacts.Artifact;
import java.util.ArrayList;

public abstract class Hero {
    public String name;
    public String type;
    public int level;
    public int experience;
    public int attack;
    public int defence;
    public int hitPoints;
    public int[] currentPosition = new int[2];
    public ArrayList<Artifact> artifacts;

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int x, int y) {
        currentPosition[0] = x;
        currentPosition[1] = y;
    }
}
