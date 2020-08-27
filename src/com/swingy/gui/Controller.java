package com.swingy.gui;

import com.swingy.heroes.Hero;

import java.util.ArrayList;

public class Controller {

    private int option;
    private ArrayList<Hero> heroes;

    public Controller(ArrayList<Hero> heroes, int option) {
        this.option = option;
        this.heroes = heroes;
    }

    public void displayOptions() {

        Menu menu = new Menu();
        String chosen = menu.displayMenu(heroes);

        System.out.println("here: " + chosen);
//        if (decision == (heroes.size() + 1)) {
////            new char
//        } else {
////            select char with index of 'decision'
//        }
////        make villains, make map, display from there
    }

}
