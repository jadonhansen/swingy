package com.swingy.gameplay;

import com.swingy.Model;
import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;

public class Fight {

    private final Model model;
    private Villain villainToFight;
    private Hero hero;
    private int power;

    public Fight(Model model) {
        this.model = model;
        this.hero = model.getChosenHero();
    }

    public boolean fightVillain() {
        this.villainToFight = model.getVillainToFight();
        power = villainToFight.getPower();

        while (power != 0) {

        }
        //        calls either win or lose
        return true; // if wins
    }

    private void win() {
        //        xp will change
        //        level might change based on xp
        //        might obtain artifact (call artifactChance method) then add it to hero artifact array
        //        remove villain from villain array
        //        update map
    };

    private void artifactChance() {
        //        random drop of artifact
        //        assign artifact quality rating based on villain power
    };

}
