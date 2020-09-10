package main.java.com.swingy.gameplay;

import java.util.Random;

public class Run {

    public boolean runCowardRun() {
        Random rand = new Random();
        int chance = rand.nextInt(2);
        if (chance == 0) {
            return true;
        } else {
            return false;
        }
    }
}
