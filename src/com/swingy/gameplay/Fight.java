package com.swingy.gameplay;

import com.swingy.artifacts.*;
import com.swingy.characters.heroes.Hero;
import com.swingy.characters.villains.Villain;

public class Fight {

    public Fight() {}

    public Hero fightVillain(Villain villain, Hero hero) {
        //        fighting algo
        //        calls either win or lose
        return hero;
    }

    private Hero win(Villain villain, Hero hero) {
        //        xp will change
        //        level might change based on xp
        //        might obtain artifact (call artifactChance method) then add it to hero artifact array
        return hero;
    };

    private void lose() {
        //        should handle quiting of the level etc. No saving needed.
    };

    private Artifact artifactChance(Villain villain, Hero hero) {
        //        random drop of artifact
        //        assign artifact quality rating based on villain strength

        //        below is an example
        Artifact artifact = new Batmobile(2);
        return artifact;
    };

}
