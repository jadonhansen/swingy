package com.swingy.gameplay;

import com.swingy.Controller;
import com.swingy.Model;
import com.swingy.artifacts.*;
import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;

import java.util.ArrayList;
import java.util.Random;

public class Fight {

    private final Model model;
    private final Controller controller;
    private Villain villainToFight;
    private ArrayList<Artifact> artifacts;
    private Hero hero;
    private int power;
    private int attack;
    private int defence;
    private int hitPoints;

    public Fight(Model model, Controller controller) {
        this.model = model;
        this.hero = model.getChosenHero();
        this.artifacts = model.getChosenHero().getArtifacts();
        this.controller = controller;
    }

    public boolean fightVillain() {
        this.villainToFight = model.getVillainToFight();

        if (villainToFight != null) {
            power = villainToFight.getPower();
            attack = hero.getAttack();
            defence = hero.getDefence();
            hitPoints = hero.getHitPoints();
        } else {
            System.out.println("Error trying to retrieve villain to fight -> Fight.java -> fightVillain()");
        }

        if (artifacts != null) {
            for (Artifact art: artifacts) {
                attack += art.getAttack();
                defence += art.getDefense();
                hitPoints += art.getHitPoints();
            }
        }

//        System.out.println("Original Power: " + power);

        while (power >= 0) {
            if (power <= 50) {
                strikeBlow(4);
                power = strikeBack();
                if (hitPoints <= 0) {
                    hitPoints = luck();
                    return false;
                }
            } else if (power <= 65) {
                strikeBlow(5);
                power = strikeBack();
                if (hitPoints <= 0) {
                    hitPoints = luck();
                    return false;
                }
            } else if (power <= 80) {
                strikeBlow(6);
                power = strikeBack();
                if (hitPoints <= 0) {
                    hitPoints = luck();
                    return false;
                }
            }

//            System.out.println("Hitpoints after blow: " + hitPoints);
//            System.out.println("Power after strikeback: " + power);

            if (hitPoints <= 0) {
                return false;
            }
        }
        win();
        return true;
    }

    // if the hero can't defend the blow then his hit points will decrease
    private void strikeBlow(int power) {
        if ((defence - power) <= 0) {
            hitPoints--;
        }
    }

    // when the hero attacks it decreases the villains power by -> attack * 1.5
    private int strikeBack() {
        int temp = power;
        return (int)(temp - (attack * 1.5));
    }

    private int luck() {
        Random rand = new Random();
        int value = rand.nextInt(2);
        int extraPoints;

        if (value == 0) {
//            System.out.println("Luck was not on your side!: " + value);
            return 0;
        } else {
            extraPoints = (int) (Math.random() * (5 - 1 + 1) + 1);
//            System.out.println("Luck is on your side!: " + extraPoints);
        }
        return extraPoints;
    }

    // altering level, experience, hero position and map after a win
    private void win() {
        int xp;
        if (villainToFight.getPower() <= 50) {
            xp = 50;
            levelUp(xp);
            artifactChance();

        } else if (villainToFight.getPower() <= 65) {
            xp = 100;
            levelUp(xp);
            artifactChance();

        } else if (villainToFight.getPower() <= 80) {
            xp = 200;
            levelUp(xp);
            artifactChance();

        }
        char[][] temp = model.getMap();
        int[] villainPos = villainToFight.getCurrentPosition();
        int[] heroPos = hero.getCurrentPosition();

        temp[villainPos[0]][villainPos[1]] = 'H';
        temp[heroPos[0]][heroPos[1]] = '+';
        model.setMap(temp);

        model.getChosenHero().setCurrentPosition(villainPos[0], villainPos[1]);
        controller.villainDefeated(villainToFight);
    };

    private void levelUp(int xp) {
        int currXp = hero.getExperience();
        model.getChosenHero().setExperience(currXp + xp);

        for (int i = 1; i <= 100; i++) {
            if (i == 100) {
                model.getChosenHero().setLevel(100);
                break;
            }
            int temp = i - 1;
            if (model.getChosenHero().getExperience() <= (int)((i * 1000) + (Math.pow(temp, 2) * 450))) {
                model.getChosenHero().setLevel(i);
                break;
            }
        }
    }

    public void artifactChance() {
        Random rand = new Random();
        int chance = rand.nextInt(101);
        Artifact chosenArtifact = null;

        if (chance > 90) {
            int chosen = rand.nextInt(3);
            switch (chosen) {
                case 0:
                    if (villainToFight.getPower() >= 65) {
                        chosenArtifact = new Hammer(2);
                    } else {
                        chosenArtifact = new Hammer(1);
                    }
                    break;
                case 1:
                    if (villainToFight.getPower() >= 65) {
                        chosenArtifact = new Batmobile(2);
                    } else {
                        chosenArtifact = new Batmobile(1);
                    }
                    break;
                case 2:
                    if (villainToFight.getPower() >= 65) {
                        chosenArtifact = new Lightsaber(2);
                    } else {
                        chosenArtifact = new Lightsaber(1);
                    }
                    break;
                default:
                    System.out.println("Unknown random int obtained -> Fight.java -> artifactChance()");
            }
        }
        model.setArtifactDrop(chosenArtifact);
    };

}
