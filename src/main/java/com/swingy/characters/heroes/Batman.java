package com.swingy.characters.heroes;

import com.swingy.artifacts.Artifact;

import java.util.ArrayList;
import java.util.Date;

public class Batman extends Hero {

    // for generation of a saved hero
    public Batman(int level, int xp, ArrayList<Artifact> artifacts, int ID) {
        name = "Batman";
        type = "Defender";
        attack = 4;
        defence = 6;
        hitPoints = 5;
        this.level = level;
        this.experience = xp;
        this.artifacts = artifacts;
        this.ID = ID;
    }

    // for generation of a new hero
    public Batman() {
        name = "Batman";
        type = "Protector";
        attack = 4;
        defence = 6;
        hitPoints = 5;
        level = 0;
        experience = 0;
        artifacts = null;
        ID = (int) (new Date().getTime()/1000);
    }
}
