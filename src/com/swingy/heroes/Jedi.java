package com.swingy.heroes;

public class Jedi extends Hero {

    // for generation of a saved hero
    public Jedi(int level, int xp) {
        name = "Jedi";
        type = "Protector";
        attack = 5;
        defence = 5;
        hitPoints = 6;
        this.level = level;
        this.experience = xp;
        // assign artifacts here  <----------
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
