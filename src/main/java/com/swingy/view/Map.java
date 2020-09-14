package com.swingy.view;

import com.swingy.characters.villains.*;
import com.swingy.Controller;
import com.swingy.Model;

import java.util.ArrayList;
import java.util.Random;

public class Map {

    private final Controller controller;
    private final Model model;
    private char[][] mapArr;
    private int mapSize;
    private int startPos;
    private final ArrayList<Villain> villains = new ArrayList<>();

    public Map(Model model, Controller controller) {
        this.controller = controller;
        this.model = model;
    }

    public void generateMap() {

        int level= model.getChosenHero().getLevel();
        mapSize = (level-1)*5+10-(level%2);
        mapArr = new char[mapSize][mapSize];

        startPos = (mapSize - 1) / 2;
        model.getChosenHero().setCurrentPosition(startPos, startPos);

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapArr[i][j] = '+';
            }
        }
        mapArr[startPos][startPos] = 'H';
        assignVillains();
        cleanMap();

        model.setMap(mapArr);

//        System.out.println("Level: " + level);
//        System.out.println("Map size: " + mapSize);
//        System.out.println("Villains count: " + villains.size());
//        System.out.println("Hero position: " + Arrays.toString(model.getChosenHero().getCurrentPosition()));
//        for (int i = 0; i < mapSize; i++) {
//            System.out.println(Arrays.toString(mapArr[i])
//                    .replace('[', ' ')
//                    .replace(',', ' ')
//                    .replace(']', ' '));
//        }
    }

    private void assignVillains() {
        int numVillains = mapSize;
        int i = 0;

        Random rand = new Random();

        while (i < numVillains) {
            int randomVillain = rand.nextInt(3);

            int randomX = rand.nextInt(mapSize);
            int randomY = rand.nextInt(mapSize);

            // make sure a villain & hero isn't on the same coordinate, then assign it if it's empty
            if ((mapArr[randomX][randomY] != 'V' && mapArr[randomX][randomY] != 'R' && mapArr[randomX][randomY] != 'J' &&
                    mapArr[startPos][startPos] != 'H') || mapArr[randomX][randomY] == '+') {

                switch (randomVillain) {
                    case 0 -> {
                        villains.add(new Joker());
                        villains.get(i).setCurrentPosition(randomX, randomY);
                        mapArr[randomX][randomY] = 'J';
                    }
                    case 1 -> {
                        villains.add(new Ragnarok());
                        villains.get(i).setCurrentPosition(randomX, randomY);
                        mapArr[randomX][randomY] = 'R';
                    }
                    case 2 -> {
                        villains.add(new Vader());
                        villains.get(i).setCurrentPosition(randomX, randomY);
                        mapArr[randomX][randomY] = 'V';
                    }
                    default -> {
                        villains.add(new Joker());
                        villains.get(i).setCurrentPosition(randomX, randomY);
                        mapArr[randomX][randomY] = 'J';
                        System.out.println("Unknown random int attained -> Map.java -> assignVillains()\nContinuing.");
                    }
                }
                i++;
            }
        }
        this.controller.setVillains(villains);
    }

    private void cleanMap() {
        try {
            for (int i = 0; i < mapArr.length; i++) {
                for (int j = 0; j < mapArr.length; j++) {
                    if (mapArr[i][j] == 'V' || mapArr[i][j] == 'J' || mapArr[i][j] == 'R') {
                        mapArr[i][j] = '+';
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Null err while cleaning map -> Map.java -> cleanMap(): " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index err while cleaning map -> Map.java -> cleanMap(): " + e);
        }
    }
}
