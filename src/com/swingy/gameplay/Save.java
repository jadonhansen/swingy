package com.swingy.gameplay;

import com.swingy.artifacts.Artifact;
import com.swingy.characters.heroes.Hero;

import java.io.File;
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
            System.out.println("Error finding chosen hero with ID: '" + chosenHero.getID() + "' -> Save.java -> saveCharacter()");
        }

        line = generateLine(heroes);

        try {
            File saveFile = new File("saves.txt");
            saveFile.createNewFile();

            FileWriter fw = new FileWriter("saves.txt");
            fw.write(line);
            fw.close();

        } catch (IOException e) {
            System.out.print("Error while saving to saves.txt -> Save.java -> Save(): " + e);
        }
    }

    private String generateLine(ArrayList<Hero> heroes) {
        String line = "";

        for (Hero hero: heroes) {
            line += "Details:" + hero.getName() + ":" + hero.getLevel() + ":" + hero.getExperience() + ":" +
                    hero.getArtifacts().size() + ":" + hero.getID() + "\n";

            line += "artifactType:";
            for (int i = 0; i < hero.getArtifacts().size(); i++) {
                if (i == hero.getArtifacts().size() - 1) {
                    line += hero.getArtifacts().get(i).toString();
                } else {
                    line += hero.getArtifacts().get(i).toString() + ":";
                }
            }

            line += "\ndefenceIncrease:";
            for (Artifact art: hero.getArtifacts()) {
                line += art.getDefense() + ":";
            }
            line += "\nattackIncrease:";
            for (Artifact art: hero.getArtifacts()) {
                line += art.getAttack() + ":";
            }
            line += "\nhitPointsIncrease:";
            for (Artifact art: hero.getArtifacts()) {
                line += art.getHitPoints() + ":";
            }
            line += "\n\n";
        }
        return line;
    }
}
