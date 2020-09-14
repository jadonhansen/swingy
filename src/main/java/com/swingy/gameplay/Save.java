package com.swingy.gameplay;

import com.swingy.characters.heroes.Hero;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save {

    public void saveCharacter(Hero chosenHero, ArrayList<Hero> heroes) {

        boolean replaced = false;
        String line;

        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getID() == chosenHero.getID()) {
                heroes.set(i, chosenHero);
                replaced = true;
                break;
            }
        }
        if (!replaced) {
            heroes.add(chosenHero);
        }

        line = generateLine(heroes);

        try {
            FileWriter fw = new FileWriter("saves.txt");
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.print("Error while saving to saves.txt -> Save.java -> Save(): " + e);
        }
    }

    private String generateLine(ArrayList<Hero> heroes) {
        String line = "";
        int j = 0;

        for (Hero hero: heroes) {
            j++;
            if (hero.getArtifacts() != null) {
                line += "Details:" + hero.getName() + ":" + hero.getLevel() + ":" + hero.getExperience() + ":" +
                        hero.getArtifacts().size() + ":" + hero.getID() + "";

                line += "\nartifactType:";
                for (int i = 0; i < hero.getArtifacts().size(); i++) {
                    if (i == hero.getArtifacts().size() - 1) {
                        line += hero.getArtifacts().get(i).toString();
                    } else {
                        line += hero.getArtifacts().get(i).toString() + ":";
                    }
                }

                line += "\ndefenceIncrease:";
                for (int i = 0; i < hero.getArtifacts().size(); i++) {
                    if (i == hero.getArtifacts().size() - 1) {
                        line += hero.getArtifacts().get(i).getDefense();
                    } else {
                        line += hero.getArtifacts().get(i).getDefense() + ":";
                    }
                }

                line += "\nattackIncrease:";
                for (int i = 0; i < hero.getArtifacts().size(); i++) {
                    if (i == hero.getArtifacts().size() - 1) {
                        line += hero.getArtifacts().get(i).getAttack();
                    } else {
                        line += hero.getArtifacts().get(i).getAttack() + ":";
                    }
                }

                line += "\nhitPointsIncrease:";
                for (int i = 0; i < hero.getArtifacts().size(); i++) {
                    if (i == hero.getArtifacts().size() - 1) {
                        line += hero.getArtifacts().get(i).getHitPoints();
                    } else {
                        line += hero.getArtifacts().get(i).getHitPoints() + ":";
                    }
                }
            } else {
                line += "Details:" + hero.getName() + ":" + hero.getLevel() + ":" + hero.getExperience() + ":" +
                        0 + ":" + hero.getID() + "";
            }
            if (j < heroes.size()) {
                line += "\n\n";
            }
        }
        return line;
    }
}
