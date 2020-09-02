package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;
import com.swingy.artifacts.Artifact;
import com.swingy.gameplay.Move;

import java.util.Arrays;
import java.util.Scanner;

public class Display {

    private final Model model;
    private final Controller controller;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_CYAN = "\u001B[36m";

    public Display(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    public void consoleGenerate() {
        Move moving = new Move();
        Scanner scan = new Scanner(System.in);

        printHeader();

        while (!(moving.reachedBorder(this.model))) {
//            clearTerminal();
            printView();
            System.out.println("\nWhat's your next move? w/a/s/d");
            String line = scan.nextLine();

            if (!(moving.validateInput(line))) {
                System.out.println("\nPlease use the characters 'w'/'a'/'s'/'d' to move.\n");
            } else {
                if (!(controller.move(line))) {
                    fightOrRun();
                }
            }
        }

        if (moving.reachedBorder(this.model)) {
            clearTerminal();
            printHeader();
            System.out.println(ANSI_RED + "You won this round!" + ANSI_RESET);
            //controller.save();
        }

    }

    private void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void fightOrRun() {
        System.out.println("You have encountered a villain! To fight press 'Y'\nTo attempt an escape press 'N'");
        Scanner input = new Scanner(System.in);
        String ans = input.nextLine();

        switch (ans) {
            case "Y":
                System.out.println("Epic battles ensues...");
                if (controller.fight()) {
                    System.out.println("You won this battle..but I'll be back!");
                    artifactView();
                } else {
                    clearTerminal();
                    printHeader();
                    System.out.println(ANSI_RED + "You lost this round, next time!" + ANSI_RESET);
                    System.exit(1);
                }
                break;
            case "N":
                if (controller.run()) {
                    System.out.println("You're one lucky guy!");
                } else {
                    System.out.println("You're going to have to fight me!");
                    if (controller.fight()) {
                        System.out.println("You won this battle..but I'll be back!");
                        artifactView();
                    } else {
                        clearTerminal();
                        printHeader();
                        System.out.println(ANSI_RED + "You lost this round, next time!" + ANSI_RESET);
                        System.exit(1);
                    }
                }
                break;
            default:
                System.out.println("Either 'Y' or 'N' is accepted as input!");
        }
    }

    private void artifactView() {
        Scanner input = new Scanner(System.in);

        if (model.getArtifactDrop() != null) {
            Artifact art = model.getArtifactDrop();

            System.out.println("\nArtifact dropped! Do you want to keep or drop the following artifact?\nPress 'Y' to keep and 'N' to drop.");
            System.out.println("\nArtifact: " + art.toString() + "\nAttack Increase: " + art.getAttack());
            System.out.println("Defence: " + art.getDefense() + "\nHit Points Increase: " + art.getHitPoints() + "\n");

            String outcome = input.nextLine();
            switch (outcome) {
                case "Y":
                    controller.addArtifact(art);
                    System.out.println("Added '" + art.toString() + "' to inventory!");
                    break;
                case "N":
                    System.out.println("No artifact gained!");
                    break;
                default:
                    System.out.println("Either 'Y' or 'N' is accepted as input!");
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
            System.out.println("Error while displaying game interface: " + e);
        }
    }

    public void printStats() {
        System.out.println("\nHero: " + model.getChosenHero().getName() + " (" + model.getChosenHero().getType() + ")");
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

    public void printHeader() {
        System.out.println(ANSI_CYAN + "*******************************************" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*                                         *" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*      Jadon Hansen - " + ANSI_GREEN + "SWINGY" + ANSI_RESET + ANSI_CYAN + " - 2020       *");
        System.out.println(ANSI_CYAN + "*                                         *" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*******************************************\n" + ANSI_RESET);
        printStats();
    }


    public void guiGenerate() {
        System.out.println("GUI");
    }
}
