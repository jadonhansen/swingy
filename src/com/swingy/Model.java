package com.swingy;

import com.swingy.characters.heroes.Hero;

import java.util.ArrayList;

public class Model {

    private ArrayList<Hero> heroes;
    private Hero chosenHero;

    public void setChosenHero(Hero chosen) {
        this.chosenHero = chosen;
    }

    public Hero getChosenHero() {
        return chosenHero;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }
}
