package com.swingy.gui;

import java.io.*;

public class Options {

    public void characterOptions(int option) {
        getSavedCharacters();
    }

    private void newCharacter() {
//        makes new char
//        generate map
    }

    private void savedCharacter() {
//        loads saved character
//        generate map
    }

    private void getSavedCharacters() {

        try {
            FileReader fr = new FileReader("saves.txt");
            BufferedReader bf = new BufferedReader(fr);
            String line;

            while((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
    }

    private void generateMap(int option) {

        if (option == 0) {
//            generate gui
        } else if (option == 1) {
//            launch on terminal
        } else {
            System.out.println("Invalid option given in 'generateMap(int option)': '" + option + ".");
        }
    }
}
