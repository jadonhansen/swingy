package com.swingy.gameplay;

import com.swingy.Model;
import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;

import java.util.ArrayList;

public class Move {

    public boolean validateInput(String line) {
        if (line.equals("W") || line.equals("A") || line.equals("S") || line.equals("D")) {
            return true;
        }
        return false;
    }

    public boolean reachedBorder(Model model) {
        int[] currPos = model.getChosenHero().getCurrentPosition();
        int mapSize = model.getMap().length;

        if (currPos[0] == 0 || currPos[0] == mapSize - 1 || currPos[1] == 0 || currPos[1] == mapSize - 1) {
            return true;
        }
        return false;
    }

    public boolean move(ArrayList<Villain> villains, Hero hero, String newMove) {

        int[] currPos = hero.getCurrentPosition();
        int x;
        int y;

        switch (newMove) {
            case "W" -> {
                x = currPos[0];
                y = currPos[1] + 1;
            }
            case "A" -> {
                x = currPos[0] - 1;
                y = currPos[1];
            }
            case "S" -> {
                x = currPos[0];
                y = currPos[1] - 1;
            }
            case "D" -> {
                x = currPos[0] + 1;
                y = currPos[1];
            }
            default -> {
                x = currPos[0];
                y = currPos[1] + 1;
                System.out.println("Unknown move encountered: " + newMove);
                System.out.println("Continuing forward.");
            }
        }

        for (Villain villain: villains) {
            int[] pos = villain.getCurrentPosition();

            if (pos[0] == x && pos[1] == y) {
                return false;
            }
        }
        return true;
    }
}
