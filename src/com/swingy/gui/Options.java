package com.swingy.gui;

import com.swingy.artifacts.Artifact;
import com.swingy.artifacts.Batmobile;
import com.swingy.artifacts.Hammer;
import com.swingy.artifacts.Lightsaber;
import com.swingy.heroes.Batman;
import com.swingy.heroes.Hero;
import com.swingy.heroes.Jedi;
import com.swingy.heroes.Thor;

import java.io.*;
import java.util.ArrayList;

public class Options {

    private static ArrayList<Hero> savedHeroes = new ArrayList<>();

    public void characterOptions(int option) {
        getSavedCharacters();

        for (Hero single: savedHeroes) {
            System.out.print(single.name + "\n");
            System.out.print(single.type + "\n");
            System.out.print(single.experience + "\n");
            System.out.print(single.level + "\n");
            System.out.print(single.hitPoints + "\n");
            System.out.print(single.attack + "\n");
            System.out.print(single.defence + "\n");
            System.out.print(single.artifacts.get(0).getAttack() + "\n");
            System.out.print(single.artifacts.get(0).getDefense() + "\n");
            System.out.print(single.artifacts.get(0).getHitPoints() + "\n");
        }
//        print chars
//        give options via gui
    }

    private void newCharacter() {
//        makes new char
//        generate map
    }

    private void savedCharacter() {
//        copies over saved character to new hero var
//        generate map
    }

    private void getSavedCharacters() {

        String line;

        try {
            FileReader fr = new FileReader("saves.txt");
            BufferedReader bf = new BufferedReader(fr);

            while((line = bf.readLine()) != null) {

                while (line.equals("\n")) {
                    line = bf.readLine();
                }
                try {
                    String[] details = line.split(":");
                    line = bf.readLine();
                    String[] artifactType = line.split(":");
                    line = bf.readLine();
                    String[] defenceInc = line.split(":");
                    line = bf.readLine();
                    String[] attackInc = line.split(":");
                    line = bf.readLine();
                    String[] hitPointsInc = line.split(":");
                    saveToLocal(details, artifactType, defenceInc, attackInc, hitPointsInc);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: " + e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
    }

    private void saveToLocal(String[] details, String[] artifactType, String[] defenceInc, String[] attackInc, String[] hitPointsInc) {

        ArrayList<Artifact> artifacts = new ArrayList<>();

        if (details[4].equals("0")) {
            artifacts = null;
        } else {
            for (int i = 1; i < artifactType.length; i++) {
                switch (artifactType[i]) {
                    case "Hammer" -> {
                        Artifact hammer = new Hammer(Integer.parseInt(attackInc[i]), 0, 0);
                        artifacts.add(hammer);
                    }
                    case "Lightsaber" -> {
                        Artifact lightsaber = new Lightsaber(0, 0, Integer.parseInt(hitPointsInc[i]));
                        artifacts.add(lightsaber);
                    }
                    case "Batmobile" -> {
                        Artifact batmobile = new Batmobile(0, Integer.parseInt(defenceInc[i]), 0);
                        artifacts.add(batmobile);
                    }
                    default -> System.out.println("Unknown artifact encountered from saves file!");
                }
            }
        }

        switch (details[1]) {
            case "Thor" -> {
                Hero thor = new Thor(Integer.parseInt(details[2]), Integer.parseInt(details[3]), artifacts);
                savedHeroes.add(thor);
            }
            case "Jedi" -> {
                Hero jedi = new Jedi(Integer.parseInt(details[2]), Integer.parseInt(details[3]), artifacts);
                savedHeroes.add(jedi);
            }
            case "Batman" -> {
                Hero batman = new Batman(Integer.parseInt(details[2]), Integer.parseInt(details[3]), artifacts);
                savedHeroes.add(batman);
            }
            default -> System.out.println("Unknown hero encountered from saves file!");
        }
    }
}
