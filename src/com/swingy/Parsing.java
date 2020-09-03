package com.swingy;

import com.swingy.artifacts.*;
import com.swingy.characters.heroes.*;

import java.io.*;
import java.util.ArrayList;

public class Parsing {

    private ArrayList<Hero> savedHeroes = new ArrayList<>();

    public ArrayList<Hero> characters() {

        getSavedCharacters();

//        for (Hero single: savedHeroes) {
//            System.out.print(single.getID() + "\n");
//            System.out.print(single.getName() + "\n");
//            System.out.print(single.getType() + "\n");
//            System.out.print(single.getExperience() + "\n");
//            System.out.print(single.getLevel() + "\n");
//            System.out.print(single.getHitPoints() + "\n");
//            System.out.print(single.getAttack() + "\n");
//            System.out.print(single.getDefence() + "\n");
//            if (single.getArtifacts() != null) {
//                ArrayList<Artifact> temp = single.getArtifacts();
//                for (Artifact hectic: temp) {
//                    System.out.print(hectic.getAttack() + "\n");
//                    System.out.print(hectic.getDefense() + "\n");
//                    System.out.print(hectic.getHitPoints() + "\n");
//                }
//            }
//        }

        return savedHeroes;
    }

    private void getSavedCharacters() {

        String line;

        try {
            FileReader fr = new FileReader("saves.txt");
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
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println("Error -> Parsing.java -> getSavedCharacters(): " + e);
                }
            }
            fr.close();
            bf.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saves file found, continuing.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
    }

    private void saveToLocal(String[] details, String[] artifactType, String[] defenceInc, String[] attackInc, String[] hitPointsInc) {

        ArrayList<Artifact> artifacts = new ArrayList<>();

        try {
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
                        default -> System.out.println("Unknown artifact encountered from saves.txt! -> Parsing.java -> saveToLocal()");
                    }
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error while assigning artifacts -> Parsing.java -> saveToLocal(): " + e);
        }

        try {
            switch (details[1]) {
                case "Thor" -> {
                    Hero thor = new Thor(Integer.parseInt(details[2]), Integer.parseInt(details[3]), artifacts, Integer.parseInt(details[5]));
                    savedHeroes.add(thor);
                }
                case "Jedi" -> {
                    Hero jedi = new Jedi(Integer.parseInt(details[2]), Integer.parseInt(details[3]), artifacts, Integer.parseInt(details[5]));
                    savedHeroes.add(jedi);
                }
                case "Batman" -> {
                    Hero batman = new Batman(Integer.parseInt(details[2]), Integer.parseInt(details[3]), artifacts, Integer.parseInt(details[5]));
                    savedHeroes.add(batman);
                }
                default -> System.out.println("Unknown hero encountered from saves.txt! -> Parsing.java -> saveToLocal()");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error while accessing details -> Parsing.java -> saveToLocal(): " + e);
        }
    }
}
