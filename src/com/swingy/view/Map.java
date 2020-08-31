package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;
import com.swingy.characters.villains.Joker;
import com.swingy.characters.villains.Ragnarok;
import com.swingy.characters.villains.Vader;
import com.swingy.characters.villains.Villain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Map {

    private static char[][] mapArr;
    private static int mapSize;
    private static ArrayList<Villain> villains = new ArrayList<>();
    private static int startPos;

    public void generateMap(Model model, Controller controller) {

        int level= model.getChosenHero().level;
        mapSize = (level-1)*5+10-(level%2);
        mapArr = new char[mapSize][mapSize];

        int numVillains = (int)Math.ceil(mapSize / 2);

        startPos = (mapSize - 1)/ 2 - 1;
        model.getChosenHero().setCurrentPosition(startPos, startPos);

        assignVillains(numVillains);

        for (int i = 0; i < mapSize - 1; i++) {
            for (int j = 0; j < mapSize - 1; j++) {
                mapArr[i][j] = '-';
            }
        }
        mapArr[startPos][startPos] = 'H';

        System.out.println(level + "\n\n");
        System.out.println(mapSize + "\n\n");
        System.out.println(numVillains + "\n\n");
        System.out.println(Arrays.toString(model.getChosenHero().getCurrentPosition()) + "\n\n");
        for (int i = 0; i < mapSize - 1; i++) {
            System.out.println(mapArr[i]);
        }

        if (model.getOption() == 1) {
            guiGenerate();
        } else if (model.getOption() == 0) {
            termGenerate();
        } else {
            System.out.println("Invalid option has been found in Map class - generateMap.java");
        }

    }

    private void assignVillains(int num) {
        Random rand = new Random();
        ArrayList<Integer> xCoords = new ArrayList<>();
        ArrayList<Integer> yCoords = new ArrayList<>();

        while (num != 0) {
            int randomIndex = rand.nextInt(2);

            int randomX = rand.nextInt(mapSize - 1);
            int randomY = rand.nextInt(mapSize - 1);

            //NEED TO CREATE 2D ARRAY OF VILLAINS CAUSE NEED TO CHECK DOUBLE COORDINATE X AND Y


            // checking to see if coordinate already taken
            while (xCoords.contains(randomX)) {
                randomX = rand.nextInt(mapSize - 1);
            }
            xCoords.add(randomX);
            while (yCoords.contains(randomY)) {
                randomY = rand.nextInt(mapSize - 1);
            }
            yCoords.add(randomY);

            switch (randomIndex) {
                case 0 -> {
                    villains.add(new Joker());
                    villains.get(num).setCurrentPosition(randomX, randomY);
                }
                case 1 -> {
                    villains.add(new Ragnarok());
                    villains.get(num).setCurrentPosition(randomX, randomY);
                }
                case 2 -> {
                    villains.add(new Vader());
                    villains.get(num).setCurrentPosition(randomX, randomY);
                }
                default -> {
                    villains.add(new Joker());
                    villains.get(num).setCurrentPosition(randomX, randomY);
                    System.out.println("Unknown random int attained - Map.java - assignVillains()\nContinuing.");
                }
            }
            num--;
        }
    }

    private void termGenerate() {

    }

    private void guiGenerate() {

    }
}
