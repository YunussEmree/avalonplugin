package org.blestit.avaloncore.Dragon;


import static org.blestit.avaloncore.Dragon.spawn.bizimDragonAd;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonHealth;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonDamage;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonSpeed;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonArmor;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonThunderAttack;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonThunderCooldown;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonAreaDamage;
import static org.blestit.avaloncore.Dragon.spawn.bizimDragonFireballDamage;


public class type {


    public static void Kilgharrahdragon(){
        bizimDragonAd = "§6§lKilgharrah Ejderhası";
        bizimDragonHealth = 12000000;
        //bizimDragonHealth = 50;
        bizimDragonDamage = 200.0;
        bizimDragonSpeed = 1.2;
        bizimDragonArmor = 0;
        bizimDragonThunderAttack = 350;
        bizimDragonThunderCooldown = 1000;
        bizimDragonAreaDamage = 150;
        bizimDragonFireballDamage = 175;
    }

    public static void Holydragon(){
        bizimDragonAd = "§6§lKutsal Ejderha";
        bizimDragonHealth = 17500000;
        //bizimDragonHealth = 50;
        bizimDragonDamage = 150.0;
        bizimDragonSpeed = 1.0;
        bizimDragonArmor = 0;
        bizimDragonThunderAttack = 200;
        bizimDragonThunderCooldown = 700;
        bizimDragonAreaDamage = 100;
        bizimDragonFireballDamage = 125;
    }
}

