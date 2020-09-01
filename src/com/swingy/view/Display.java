package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;
import com.swingy.gameplay.Move;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Display {

    private final Model model;
    private final Controller controller;

    public Display(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    public void consoleGenerate() {
        printView();

        Move moving = new Move();
        Scanner scan = new Scanner(System.in);

        while (!(moving.reachedBorder(this.model))) {
            printView();
            System.out.println("What's your next move? W/A/S/D");
            String line = scan.nextLine();

            if (!(moving.validateInput(line))) {
                System.out.println("Please use the characters 'W'/'A'/'S'/'D' to move.");
            } else {
                if (controller.move(line)) {
                    // alter array
                    // save new coords for hero
                } else {
                    // fight/run
                    // alter array based on outcome
                }
            }
            clearTerminal();
        }

        if (moving.reachedBorder(this.model)) {
            clearTerminal();
            // display winning view with new stats
            // save stats to file
        }

    }

    private void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing console!");
        }
    }


    private void printView() {
        char[][] temp = this.model.getMap();

        // print header with stats

        try {
            for (char[] chars : temp) {
                System.out.println(Arrays.toString(chars)
                        .replace('[', ' ')
                        .replace(',', ' ')
                        .replace(']', ' '));
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error while displaying game interface: " + e);
        }
    }

    public void guiGenerate() {
        System.out.println("GUI");
    }
}
