package com.swingy;

import com.swingy.characters.heroes.Hero;
import com.swingy.view.Menu;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    // calls menu view to display saved heroes
    public void displayOptions() {
        Menu menu = new Menu();
        menu.displayMenu(this.model.getHeroes(), this, model);
    }

    // updates model to set chosen hero from menu view
    public void setChosenHero(Hero hero) {
        this.model.setChosenHero(hero);
//        create map
    }

}
