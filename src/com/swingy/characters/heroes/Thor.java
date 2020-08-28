package com.swingy.characters.heroes;

import com.swingy.artifacts.Artifact;

import java.util.ArrayList;

public class Thor extends Hero {

    // for generation of a saved hero
    public Thor(int level, int xp, ArrayList<Artifact> artifacts) {
        name = "Thor";
        type = "Attacker";
        attack = 6;
        defence = 4;
        hitPoints = 5;
        this.level = level;
        this.experience = xp;
        this.artifacts = artifacts;
    }

    // for generation of a new hero
    public Thor() {
        name = "Thor";
        type = "Attacker";
        attack = 6;
        defence = 4;
        hitPoints = 5;
        level = 0;
        experience = 0;
        artifacts = null;
    }
}
