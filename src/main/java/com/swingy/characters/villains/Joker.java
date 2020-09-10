package main.java.com.swingy.characters.villains;

import java.util.Arrays;

public class Joker extends Villain {

    public Joker() {
        // assign it between 25 and 50
        this.power = (int) (Math.random() * (51 - 25 + 1) + 25);
    }

    @Override
    public String toString() {
        return "Joker: power=" + this.power + ", Current Position=" + Arrays.toString(this.currentPosition);
    }
}
