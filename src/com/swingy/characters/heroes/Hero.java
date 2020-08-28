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
    public ArrayList<Artifact> artifacts;

    public void fight(Villain villain) {
        Fight fighting = new Fight();
        Hero temp = fighting.fightVillain(villain, this);
        this.level = temp.level;
        this.experience = temp.experience;
        this.artifacts = temp.artifacts;
    }

    public void run(Villain villain) {
        Run running = new Run();
        if (running.runCowardRun()) {
//            if true then he returns to previous position
        } else {
            fight(villain);
        }
    }
}
