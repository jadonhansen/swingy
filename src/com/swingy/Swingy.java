package com.swingy;

public class Swingy {

    public static void main(String[] args) {

        Parsing parse = new Parsing();

        try {
            if (args[0].equals("console")) {
                parse.characters(0);
            } else if (args[0].equals("gui")) {
                parse.characters(1);
            } else {
                System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
        }
    }
}
