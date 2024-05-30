package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

public class Dragon {
    String Health;
    String Damage;
    String Speed;
    String Armor;
    String SkillDamage;
    String SkillCooldown;

    static EnderDragon dragon;


    public Dragon(String Health, String Damage, String Speed, String Armor, String SkillDamage, String SkillCooldown, Location location) {
        this.Health = Health;
        this.Damage = Damage;
        this.Speed = Speed;
        this.Armor = Armor;
        this.SkillDamage = SkillDamage;
        this.SkillCooldown = SkillCooldown;

        dragon = location.getWorld().spawn(location,EnderDragon.class);


    }

}
