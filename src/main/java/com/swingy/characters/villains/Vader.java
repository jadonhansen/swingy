package com.swingy.characters.villains;

public class Vader extends Villain {

    public Vader() {
        // assign it between 65 and 80
        this.power = (int) (Math.random() * (81 - 65 + 1) + 65);
    }

    @Override
    public String toString() {
        return "Vader: Power=" + this.power;
    }
}
