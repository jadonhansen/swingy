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
        model.setMap(map);
        map.generateMap(model, this);
    }

    // updates model with randomly generated villains
    public void setVillains(ArrayList<Villain> villains) {
        model.setVillains(villains);
    }

    // retrieves outcome of the calculation of the move
    public boolean move(String newMove) {
        Move move = new Move();
        return move.Move(model.getVillains(), model.getChosenHero()); //check if occupied and if boundry reached
    }

    // simulates a fight between villain and hero
    public void fight(Villain villain) {
        Fight fighting = new Fight();
        model.setChosenHero(fighting.fightVillain(villain, model.getChosenHero()));
    }

    // simulates a hero running away from a villain
    public void run(Villain villain) {
        Run running = new Run();
        if (!running.runCowardRun(villain, model.getChosenHero())) {
            fight(villain);
        }
    }

    // saves character stats to the file
    public void save() {
        Save save = new Save();
        save.saveCharacter(model.getChosenHero());
    }
}
