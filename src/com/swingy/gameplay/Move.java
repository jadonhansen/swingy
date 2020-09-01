package com.swingy.gameplay;

import com.swingy.Model;
import com.swingy.characters.villains.Villain;

public class Move {

    public boolean validateInput(String line) {
        if (line.equals("w") || line.equals("a") || line.equals("s") || line.equals("d")) {
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

    public boolean move(Model model, String newMove) {

        int[] currPos = model.getChosenHero().getCurrentPosition();
        int x;
        int y;

        switch (newMove) {
            case "w" -> {
                x = currPos[0] - 1;
                y = currPos[1];
            }
            case "a" -> {
                x = currPos[0];
                y = currPos[1] - 1;
            }
            case "s" -> {
                x = currPos[0] + 1;
                y = currPos[1];
            }
            case "d" -> {
                x = currPos[0];
                y = currPos[1] + 1;
            }
            default -> {
                x = currPos[0];
                y = currPos[1] + 1;
                System.out.println("Unknown move encountered: " + newMove);
                System.out.println("Continuing forward.");
            }
        }

        for (Villain villain: model.getVillains()) {
            int[] pos = villain.getCurrentPosition();

            if (pos[0] == x && pos[1] == y) {
                return false;
            }
        }

        char[][] temp = model.getMap();
        int[] position = model.getChosenHero().getCurrentPosition();

        temp[position[0]][position[1]] = '+';
        temp[x][y] = 'H';
        model.setMap(temp);

        model.getChosenHero().setCurrentPosition(x, y);
        return true;
    }
}
