package com.swingy.gameplay;

import com.swingy.Model;
import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;

public class Run {

    private final Model model;
    private Hero hero;
    private Villain villainToRunFrom;

    public Run(Model model) {
        this.model = model;
        this.hero = model.getChosenHero();
        this.villainToRunFrom = model.getVillainToFight();
    }

    public boolean runCowardRun() {
        // do running algo here
        return true; //if successful, false if unsuccessful
    }
}
