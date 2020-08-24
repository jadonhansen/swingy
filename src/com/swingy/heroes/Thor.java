package com.swingy.heroes;

public class Thor extends Hero {

    // for generation of a saved hero
    public Thor(int level, int xp) {
        name = "Thor";
        type = "Attacker";
        attack = 6;
        defence = 4;
        hitPoints = 5;
        this.level = level;
        this.experience = xp;
        // assign artifacts here  <----------
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
