package com.swingy.characters.villains;

public class Joker extends Villain {

    public Joker() {
        // assign it between 25 and 50
        this.power = (int) (Math.random() * (51 - 25 + 1) + 25);
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
