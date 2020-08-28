package com.swingy;

import com.swingy.characters.heroes.Hero;

import java.util.ArrayList;

public class Swingy {

    public static void main(String[] args) {

        Parsing parse = new Parsing();
        ArrayList<Hero> characters = parse.characters();

        try {
            Model model = new Model();
            model.setHeroes(characters);

            if (args[0].equals("console")) {
                model.setOption(0);
            } else if (args[0].equals("gui")) {
                model.setOption(1);
            } else {
                System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
            }

            Controller controller = new Controller(model);
            controller.displayOptions();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
        }
    }
}
