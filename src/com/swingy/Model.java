package com.swingy;

import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;
import com.swingy.view.Map;

import java.util.ArrayList;

public class Model {

    private ArrayList<Hero> heroes;
    private ArrayList<Villain> villains;
    private Hero chosenHero;
    private Map map;
    private int option;

    // map
    public void setMap(Map map) {
        this.map = map;
    }
    public Map getMap() {
        return map;
    }

    // chosen hero
    public void setChosenHero(Hero chosen) {
        this.chosenHero = chosen;
    }
    public Hero getChosenHero() {
        return chosenHero;
    }

    // option. 1 = GUI, 2 = Terminal
    public void setOption(int option) {
        this.option = option;
    }
    public int getOption() {
        return option;
    }

    // heroes
    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }
    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    // villains
    public void setVillains(ArrayList<Villain> villains) {
        this.villains = villains;
    }
    public ArrayList<Villain> getVillains() {
        return villains;
    }
}
