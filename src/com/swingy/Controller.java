package com.swingy;

import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;
import com.swingy.gameplay.*;
import com.swingy.view.*;

import java.util.ArrayList;

public class Controller {

    private final Model model;
    private final Map map = new Map();

    public Controller(Model model) {
        this.model = model;
    }

    // calls menu view to display saved heroes
    public void displayOptions() {
        Menu menu = new Menu();
        menu.displayMenu(model.getHeroes(), this, model);
    }

    // updates model to set chosen hero from menu view
    public void setChosenHero(Hero hero) {
        model.setChosenHero(hero);
        generateMap();
    }

    // updates model with new map which is being generated
    public void generateMap() {
        map.generateMap(model, this);
        displayGame();
    }

    // updates model with randomly generated villains
    public void setVillains(ArrayList<Villain> villains) {
        model.setVillains(villains);
    }

    // takes generated map, hero, villain info and starts game
    public void displayGame() {
        Display display = new Display(model, this);

        if (model.getOption() == 1) {
            display.guiGenerate();
        } else if (model.getOption() == 0) {
            display.consoleGenerate();
        } else {
            System.out.println("Invalid option has been found -> Map.java -> generateMap()");
        }
    }

    // retrieves outcome of the calculation of the move
    public boolean move(String newMove) {
        Move move = new Move();
        return move.possibleMove(model, newMove);
    }

    // simulates a fight between villain and hero
    public boolean fight() {
        Fight fighting = new Fight(model);
        if (fighting.fightVillain()) {
            return true;
        } else {
            return false;
        }
    }

    // simulates a hero running away from a villain
    public boolean run() {
        Run running = new Run(model);
        if (running.runCowardRun()) {
            return true;
        } else {
            return false;
        }
    }

    // saves character stats to the file
    public void save() {
        Save save = new Save();
        save.saveCharacter(model.getChosenHero());
    }
}
