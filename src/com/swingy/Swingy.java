package com.swingy;

import com.swingy.gui.Options;

public class Swingy {

    public static void main(String[] args) {

        Options gui = new Options();

        try {
            if (args[0].equals("console")) {
                gui.characterOptions(0);
            } else if (args[0].equals("gui")) {
                gui.characterOptions(1);
            } else {
                System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
        }
    }
}
