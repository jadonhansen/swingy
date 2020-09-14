package com.swingy;

import com.swingy.characters.heroes.Hero;

import java.util.ArrayList;

public class Swingy {

    public static void main(String[] args) {

        Parsing parse = new Parsing();
        ArrayList<Hero> characters = parse.characters();

        Validate validator = new Validate();

        try {
            Model model = new Model();
            model.setHeroes(null);

            if (args[0].equals("console")) {
                model.setOption(0);
            } else if (args[0].equals("gui")) {
                model.setOption(1);
            } else {
                System.out.println("Please provide a suitable option for running the game! 'console' or 'gui'");
                System.exit(1);
            }

            validator.validateModel(model);

            Controller controller = new Controller(model);
            controller.displayOptions();

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
        }
    }
}
