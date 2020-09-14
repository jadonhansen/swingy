package com.swingy.characters.villains;

public class Ragnarok extends Villain {

    public Ragnarok() {
        // assign it between 50 and 65
        this.power = (int) (Math.random() * (66 - 50 + 1) + 50);
    }

    @Override
    public String toString() {
        return "Ragnarok: Power=" + this.power;
    }
}
