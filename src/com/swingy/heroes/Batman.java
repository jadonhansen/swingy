package com.swingy.heroes;

public class Batman extends Hero {

    // for generation of a saved hero
    public Batman(int level, int xp) {
        name = "Batman";
        type = "Defender";
        attack = 4;
        defence = 6;
        hitPoints = 5;
        this.level = level;
        this.experience = xp;
        // assign artifacts here  <----------
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
    }
}
