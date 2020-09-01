package com.swingy.characters.heroes;

import com.swingy.artifacts.Artifact;
import java.util.ArrayList;

public abstract class Hero {
    protected String name;
    protected String type;
    protected int level;
    protected int experience;
    protected int attack;
    protected int defence;
    protected int hitPoints;
    protected int[] currentPosition = new int[2];
    protected ArrayList<Artifact> artifacts;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int x, int y) {
        currentPosition[0] = x;
        currentPosition[1] = y;
    }
}
