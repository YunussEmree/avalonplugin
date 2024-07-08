package org.blestit.avaloncore.Modules;

import java.util.concurrent.ThreadLocalRandom;

public class Chance {

    static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static boolean chance(double chance) {
        return random.nextDouble(0,100) < chance;
    }
}
