package com.swingy.view;

import com.swingy.artifacts.Artifact;
import com.swingy.gameplay.Move;
import com.swingy.Controller;
import com.swingy.Model;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Scanner;

public class Retro {

    @NotNull
    private final Model model;
    @NotNull
    private final Controller controller;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_CYAN = "\u001B[36m";
    private boolean lost = false;

    public Retro(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    public void consoleGenerate() {
        Move moving = new Move();
        Scanner scan = new Scanner(System.in);

        printHeader();

        while (!(moving.reachedBorder(this.model)) && !lost) {
            printView();
            System.out.println(ANSI_CYAN + "\nWhat's your next move? w/a/s/d (Press ctrl+c to exit OR 'M' to switch to GUI view)" + ANSI_RESET);
            String line = scan.nextLine();

            if (!(moving.validateInput(line))) {
                clearTerminal();
                System.out.println(ANSI_YELLOW + "\nPlease use the characters 'w'/'a'/'s'/'d' to move.\n" + ANSI_RESET);
            } else {
                if (line.equals("M")) {
                    controller.swapView();
                    return;
                }
                if (!(controller.move(line))) {
                    fightOrRun();
                } else {
                    clearTerminal();
                }
            }
        }

        if (moving.reachedBorder(this.model)) {
            clearTerminal();
            printHeader();
            printStats();
            System.out.println(ANSI_GREEN + "You won this round!\n" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Go to the menu to start on a new map." + ANSI_RESET);
        }
        controller.save(true);

    }

    public void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void fightOrRun() {
        System.out.println(ANSI_CYAN + "You have encountered a villain! To fight press 'Y'. To attempt an escape press 'N'" + ANSI_RESET);
        Scanner input = new Scanner(System.in);
        String ans = input.nextLine();

        while (!ans.equals("Y") && !ans.equals("N")) {
            System.out.println(ANSI_YELLOW + "Either 'Y' or 'N' is accepted as input!" + ANSI_RESET);
            ans = input.nextLine();
        }

        switch (ans) {
            case "Y":
                System.out.println(ANSI_RED + "Epic battles ensues..." + ANSI_RESET);
                fight();
                break;
            case "N":
                if (controller.run()) {
                    System.out.println(ANSI_GREEN + "You're one lucky guy!\n" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "You're going to have to fight me!" + ANSI_RESET);
                    fight();
                }
                break;
        }
    }

    private void fight() {
        if (controller.fight()) {
            System.out.println(ANSI_GREEN + "You won this battle...but I'll be back!\n" + ANSI_RESET);
            artifactView();
        } else {
            clearTerminal();
            printHeader();
            printStats();
            System.out.println(ANSI_RED + "You lost this round, next time!" + ANSI_RESET);
            lost = true;
        }
    }

    private void artifactView() {
        Scanner input = new Scanner(System.in);

        if (model.getArtifactDrop() != null) {
            Artifact art = model.getArtifactDrop();

            System.out.println(ANSI_CYAN + "\nArtifact dropped! Do you want to keep or drop the following artifact?\nPress 'Y' to keep and 'N' to drop." + ANSI_RESET);
            System.out.println("\nArtifact: " + art.toString() + "\nAttack Increase: " + art.getAttack());
            System.out.println("Defence: " + art.getDefense() + "\nHit Points Increase: " + art.getHitPoints() + "\n");

            String outcome = input.nextLine();

            while (!outcome.equals("Y") && !outcome.equals("N")) {
                System.out.println(ANSI_YELLOW + "Either 'Y' or 'N' is accepted as input!" + ANSI_RESET);
                outcome = input.nextLine();
            }

            switch (outcome) {
                case "Y" -> {
                    controller.addArtifact(art);
                    System.out.println(ANSI_GREEN + "Added '" + art.toString() + "' to inventory!" + ANSI_RESET);
                }
                case "N" -> System.out.println(ANSI_RED + "No artifact gained!" + ANSI_RESET);
            }
        }
    }

    private void printView() {
        char[][] temp = this.model.getMap();
        String line;

        printStats();

        try {
            for (char[] chars : temp) {

                line = Arrays.toString(chars)
                        .replace('[', ' ')
                        .replace(',', ' ')
                        .replace(']', ' ');

                if (line.contains("H")) {
                    String heroLine = "";

                    for (char aChar : chars) {
                        if (aChar == 'H') {
                            heroLine += ANSI_RED + " H " + ANSI_RESET;
                        } else {
                            heroLine += " " + aChar + " ";
                        }
                    }
                    System.out.println(heroLine);
                } else {
                    System.out.println(line);
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error while displaying game interface -> Retro.java -> printView(): " + e);
        }
    }

    private void printStats() {
        System.out.println("Hero: " + model.getChosenHero().getName() + " (" + model.getChosenHero().getType() + ")");
        System.out.println("Level: " + model.getChosenHero().getLevel());
        System.out.println("Experience: " + model.getChosenHero().getExperience());
        System.out.println("Attack: " + model.getChosenHero().getAttack());
        System.out.println("Defence: " + model.getChosenHero().getDefence());
        System.out.println("Hit Points: " + model.getChosenHero().getHitPoints() + "");
        if (model.getChosenHero().getArtifacts() != null) {
            System.out.println("Artifacts: " + model.getChosenHero().getArtifacts().size() + "\n");
        } else {
            System.out.println("Artifacts: 0\n");
        }
    }

    private void printHeader() {
        System.out.println(ANSI_CYAN + "*******************************************" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*                                         *" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*      Jadon Hansen - " + ANSI_GREEN + "SWINGY" + ANSI_RESET + ANSI_CYAN + " - 2020       *");
        System.out.println(ANSI_CYAN + "*                                         *" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*******************************************\n" + ANSI_RESET);
    }
}
