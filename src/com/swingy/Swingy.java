package com.swingy;

public class Swingy {

    public static void main(String[] args) {

        try {
            if (args[0] == "console") {
//                give option to chose chars or new
//                check saves file for heroes if needed: display options
//                generate map
            } else if (args[0] == "gui") {
//                give option to chose chars or new
//                check saves file for heroes if needed: display options
//                generate map
            } else {
                System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a suitable option for running the game!\n'console' or 'gui'");
        }
    }
}
