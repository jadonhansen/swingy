package com.swingy;

import com.swingy.artifacts.Artifact;
import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class Model {

    @NotNull(message = "Heroes cannot be null")
    private ArrayList<Hero> heroes;

    @NotNull(message = "Option cannot be null")
    private int option;

    private ArrayList<Villain> villains;
    private Villain villainToFight;
    private Hero chosenHero;
    private Artifact artifactDrop;
    private char[][] mapArr;

    // map
    public void setMap(char[][] map) {
        this.mapArr = map;
    }
    public char[][] getMap() {
        return mapArr;
    }

    // chosen hero
    public void setChosenHero(Hero chosen) {
        this.chosenHero = chosen;
    }
    public Hero getChosenHero() {
        return chosenHero;
    }

    // option. 1 = GUI, 2 = Terminal
    public void setOption(int option) {
        this.option = option;
    }
    public int getOption() {
        return option;
    }

    // heroes
    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }
    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    // villains
    public void setVillains(ArrayList<Villain> villains) {
        this.villains = villains;
    }
    public ArrayList<Villain> getVillains() {
        return villains;
    }

    // villain to fight
    public void setVillainToFight(Villain villain) {
        villainToFight = villain;
    }
    public Villain getVillainToFight() {
        return villainToFight;
    }

    // artifact drop
    public void setArtifactDrop(Artifact artifactDrop) {
        this.artifactDrop = artifactDrop;
    }
    public Artifact getArtifactDrop() {
        return artifactDrop;
    }
}
