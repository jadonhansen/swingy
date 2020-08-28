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

            Controller controller = new Controller(model);
            controller.displayOptions();

            if (args[0].equals("console")) {
                System.out.println("1" + model.getChosenHero());
                System.out.println("2" + model.getHeroes());


            } else if (args[0].equals("gui")) {
                System.out.println("1" + model.getChosenHero());
                System.out.println("2" + model.getHeroes());

            } else {
                System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
        }
    }
}
