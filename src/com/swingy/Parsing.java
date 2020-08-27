package com.swingy;

import com.swingy.artifacts.*;
import com.swingy.gui.Controller;
import com.swingy.heroes.*;

import java.io.*;
import java.util.ArrayList;

public class Parsing {

    private static ArrayList<Hero> savedHeroes = new ArrayList<>();

    public void characters(int option) {
        getSavedCharacters();
        Controller options = new Controller(savedHeroes, option);
        options.displayOptions();

        for (Hero single: savedHeroes) {
            System.out.print(single.name + "\n");
            System.out.print(single.type + "\n");
            System.out.print(single.experience + "\n");
            System.out.print(single.level + "\n");
            System.out.print(single.hitPoints + "\n");
            System.out.print(single.attack + "\n");
            System.out.print(single.defence + "\n");
            if (single.artifacts != null) {
                for (Artifact hectic: single.artifacts) {
                    System.out.print(hectic.getAttack() + "\n");
                    System.out.print(hectic.getDefense() + "\n");
                    System.out.print(hectic.getHitPoints() + "\n");
                }
            }
        }
    }

    private void getSavedCharacters() {

        String line;

        try {
            FileReader fr = new FileReader("com/swingy/saves.txt");
            BufferedReader bf = new BufferedReader(fr);

            while ((line = bf.readLine()) != null) {

                while (line.equals("")) {
                    line = bf.readLine();
                }
                try {
                    String[] details = line.split(":");
                    if (details[4].equals("0")) {
                        saveToLocal(details, null, null, null, null);
                    } else {
                        line = bf.readLine();
                        String[] artifactType = line.split(":");
                        line = bf.readLine();
                        String[] defenceInc = line.split(":");
                        line = bf.readLine();
                        String[] attackInc = line.split(":");
                        line = bf.readLine();
                        String[] hitPointsInc = line.split(":");
                        saveToLocal(details, artifactType, defenceInc, attackInc, hitPointsInc);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error in parsing.java: " + e);
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
