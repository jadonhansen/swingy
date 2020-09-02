package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;
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
        System.out.println("You have encountered a villain! To fight press 'Y'\nTo run to safety press 'N'");
        Scanner input = new Scanner(System.in);
        String ans = input.nextLine();

        switch (ans) {
            case "Y":
                System.out.println("Epic battles ensues...");
                if (controller.fight()) {
                    System.out.println("You won this battle..but I'll be back!");
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
                    controller.fight();
                }
                break;
            default:
                System.out.println("Either 'Y' or 'N' is accepted as input!");
        }
    }

    private void printView() {
        char[][] temp = this.model.getMap();
        String line;

        printHeader();

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

    public void printHeader() {
        System.out.println(ANSI_CYAN + "*******************************************" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*                                         *" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*      Jadon Hansen - " + ANSI_GREEN + "SWINGY" + ANSI_RESET + ANSI_CYAN + " - 2020       *");
        System.out.println(ANSI_CYAN + "*                                         *" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "*******************************************\n" + ANSI_RESET);
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


    public void guiGenerate() {
        System.out.println("GUI");
    }
}
