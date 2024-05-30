package org.blestit.avaloncore.DragonOptimized;

import org.blestit.avaloncore.AvalonCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DragonTypeSelection {
    private Plugin plugin;

    public int dragonHealth;
    public int dragonDamage;
    public int dragonSpeed;
    public int dragonArmor;
    public int dragonSkillDamage;
    public int dragonSkillCooldown;

    public DragonTypeSelection(Plugin plugin) {
        this.plugin = plugin;
    }

    public String selectDragonType() {
        FileConfiguration config = plugin.getConfig();
        Set<String> dragonTypes = config.getConfigurationSection("dragons").getKeys(false); //deep:false means only direct children not grandchildren

        // fill the map with dragon chances and types
        Map<String, Double> dragonChances = new HashMap<>();
        for (String dragonType : dragonTypes) {
            double chance = config.getDouble("dragons." + dragonType + ".chance");
            dragonChances.put(dragonType, chance);
        }

        // Calculating total weight
        double totalWeight = 0.0;
        for (double chance : dragonChances.values()) {
            totalWeight += chance;
        }

        double random = Math.random() * totalWeight;
        for (Map.Entry<String, Double> entry : dragonChances.entrySet()) {
            random -= entry.getValue();
            if (random <= 0.0) {
                return entry.getKey();
            }
        }

        // Eğer hiçbir ejderha türü seçilmezse (bu olmamalı), null döndür
        return null;
    }

}
