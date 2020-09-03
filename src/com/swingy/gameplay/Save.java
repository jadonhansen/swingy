package com.swingy.gameplay;

import com.swingy.characters.heroes.Hero;

import java.util.ArrayList;

public class Save {

    public void saveCharacter(Hero chosenHero, ArrayList<Hero> heroes) {

        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getID() == chosenHero.getID()) {
                heroes.set(i, chosenHero);
                break;
            }
        }

    }
}
