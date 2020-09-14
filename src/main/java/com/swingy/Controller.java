package com.swingy;

import com.swingy.artifacts.Artifact;
import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;
import com.swingy.gameplay.*;
import com.swingy.view.*;

import java.util.ArrayList;

public class Controller {

    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    // calls menu view to display saved heroes
    public void displayOptions() {
        Menu menu = new Menu(model.getHeroes(), this);
        menu.displayMenu();
    }

    // updates model to set chosen hero from menu view
    public void setChosenHero(Hero hero) {
        model.setChosenHero(hero);
        generateMap();
    }

    // updates model with new map which is being generated
    public void generateMap() {
        Map map = new Map(model, this);
        map.generateMap();
        displayGame();
    }

    // updates model with randomly generated villains
    public void setVillains(ArrayList<Villain> villains) {
        model.setVillains(villains);
    }

    // starts game
    public void displayGame() {
        if (model.getOption() == 1) {
            Modern modern = new Modern(model, this);
            modern.guiGenerate();
        } else if (model.getOption() == 0) {
            Retro retro = new Retro(model, this);
            retro.consoleGenerate();
        } else {
            System.out.println("Invalid option has been found -> Map.java -> generateMap()");
            System.exit(1);
        }
    }

    // retrieves outcome of the calculation of the move
    public boolean move(String newMove) {
        Move move = new Move();
        return move.possibleMove(model, newMove);
    }

    // simulates a fight between villain and hero
    public boolean fight() {
        Fight fighting = new Fight(model, this);
        if (fighting.fightVillain()) {
            return true;
        } else {
            return false;
        }
    }

    // simulates a hero running away from a villain
    public boolean run() {
        Run running = new Run();
        if (running.runCowardRun()) {
            return true;
        } else {
            return false;
        }
    }

    // if an artifact is gained & the user wants to keep it then it is added to the heroes artifact array
    public void addArtifact(Artifact artifact) {
        ArrayList<Artifact> temp = model.getChosenHero().getArtifacts();
        if (temp == null) {
            temp = new ArrayList<>();
        }
        temp.add(artifact);
        model.getChosenHero().setArtifacts(temp);
    }

    // if a villain is defeated it is removed from the existing array
    public void villainDefeated(Villain villain) {
        ArrayList<Villain> temp = model.getVillains();

        if (temp.remove(villain)) {
            model.setVillains(temp);
        } else {
            System.out.println("Error removing villain: " + villain.toString());
        }
    }

    // swaps between the two views
    public void swapView() {
        if (model.getOption() == 1) {
            model.setOption(0);
        } else {
            model.setOption(1);
        }
        displayGame();
    }

    // saves character stats to the file
    public void save(boolean displayMenu) {
        Save save = new Save();
        save.saveCharacter(model.getChosenHero(), model.getHeroes());
        newGame(displayMenu);
    }

    public void newGame(boolean displayMenu) {
        Parsing parse = new Parsing();
        ArrayList<Hero> characters = parse.characters();
        model.setHeroes(characters);
        model.setMap(null);
        model.setVillains(null);
        model.setChosenHero(null);
        model.setArtifactDrop(null);
        model.setVillainToFight(null);
        if (displayMenu) {
            displayOptions();
        }
    }
}
