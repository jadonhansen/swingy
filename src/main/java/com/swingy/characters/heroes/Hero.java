package com.swingy.characters.heroes;

import com.swingy.artifacts.Artifact;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public abstract class Hero {

    @NotNull
    protected int ID;
    @NotNull
    protected String name;
    @NotNull
    protected String type;
    @NotNull
    protected int level;
    @NotNull
    protected int experience;
    @NotNull
    protected int attack;
    @NotNull
    protected int defence;
    @NotNull
    protected int hitPoints;
    @NotNull
    protected int[] currentPosition = new int[2];
    protected ArrayList<Artifact> artifacts;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
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
    public void setArtifacts(ArrayList<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(int x, int y) {
        currentPosition[0] = x;
        currentPosition[1] = y;
    }
}
