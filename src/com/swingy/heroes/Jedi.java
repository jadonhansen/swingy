package com.swingy.heroes;

import com.swingy.artifacts.Artifact;

import java.util.ArrayList;

public class Jedi extends Hero {

    // for generation of a saved hero
    public Jedi(int level, int xp, ArrayList<Artifact> artifacts) {
        name = "Jedi";
        type = "Protector";
        attack = 5;
        defence = 5;
        hitPoints = 6;
        this.level = level;
        this.experience = xp;
        this.artifacts = artifacts;
    }

    // for generation of a new hero
    public Jedi() {
        name = "Jedi";
        type = "Protector";
        attack = 5;
        defence = 5;
        hitPoints = 6;
        level = 0;
        experience = 0;
        artifacts = null;
    }
}
