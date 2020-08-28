package com.swingy.characters.heroes;

import com.swingy.artifacts.Artifact;
import com.swingy.characters.villains.Villain;
import com.swingy.gameplay.*;
import java.util.ArrayList;

public abstract class Hero {
    public String name;
    public String type;
    public int level;
    public int experience;
    public int attack;
    public int defence;
    public int hitPoints;
    public int[] currentPosition;
    public ArrayList<Artifact> artifacts;

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }
}
