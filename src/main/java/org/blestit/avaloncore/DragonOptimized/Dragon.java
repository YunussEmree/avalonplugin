package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

public class Dragon {
    public static int Health = 0;
    static int Damage = 0;
    static int Speed = 0;
    static int Armor = 0;
    static int SkillDamage = 0;
    static int SkillCooldown = 0;

    public static EnderDragon dragon;


    public Dragon(int Health, int Damage, int Speed, int Armor, int SkillDamage, int SkillCooldown, Location location) {
        this.Health = Health;
        this.Damage = Damage;
        this.Speed = Speed;
        this.Armor = Armor;
        this.SkillDamage = SkillDamage;
        this.SkillCooldown = SkillCooldown;

        dragon = location.getWorld().spawn(location,EnderDragon.class);
    }



}
