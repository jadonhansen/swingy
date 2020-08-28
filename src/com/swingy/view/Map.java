package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;

public class Map {

    public void generateMap(Model model, Controller controller) {

        if (model.getOption() == 1) {
        //            map size determined by formula: (level-1)*5+10-(level%2)
        //            generate villains of varying power randomly spread
        //            generate for gui
        } else if (model.getOption() == 0) {
        //            map size determined by formula: (level-1)*5+10-(level%2)
        //            generate villains of varying power randomly spread
        //            generate for terminal
        } else {
            System.out.println("Invalid option has been found in Map class - generateMap.java");
        }

    }
}
