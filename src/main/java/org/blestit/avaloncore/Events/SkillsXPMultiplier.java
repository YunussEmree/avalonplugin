package org.blestit.avaloncore.Events;


import io.github.WeloxiaDev.UltraMinions.api.events.MinionCollectEvent;
import com.willfp.ecoskills.skills.Skills;
import com.willfp.ecoskills.api.EcoSkillsAPI;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;



public class SkillsXPMultiplier implements Listener {

    Plugin plugin;

    public SkillsXPMultiplier(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMinionCollect(MinionCollectEvent event) {
        Player p = event.getPlayer();
        if (!event.getItems().isEmpty()) {
            for (HashMap.Entry<ItemStack, Integer> entry : event.getItems().entrySet()) {
                ItemStack item = entry.getKey();
                int amount = entry.getValue();
                Material material = item.getType();
                double xpMultiplier = 1.0;

                if (!item.getEnchantments().isEmpty() && !material.equals(Material.HEART_OF_THE_SEA)) {
                    xpMultiplier = 160.0;
                }
                if (!item.getEnchantments().isEmpty() && material.equals(Material.HEART_OF_THE_SEA)) {
                    xpMultiplier = 9.0;
                }


                try {
                    double miningXP = getSkillXP("mining", material) * amount * xpMultiplier;
                    if (miningXP > 0) {
                        EcoSkillsAPI.gainSkillXP(p, Skills.INSTANCE.getByID("mining"), miningXP);
                        break;
                    }
                    double combatXP = getSkillXP("combat", material) * amount * xpMultiplier;
                    if (combatXP > 0) {
                        EcoSkillsAPI.gainSkillXP(p, Skills.INSTANCE.getByID("combat"), combatXP);
                        break;
                    }
                    double woodcuttingXP = getSkillXP("woodcutting", material) * amount * xpMultiplier;
                    if (woodcuttingXP > 0) {
                        EcoSkillsAPI.gainSkillXP(p, Skills.INSTANCE.getByID("woodcutting"), woodcuttingXP);
                        break;
                    }
                    double farmingXP = getSkillXP("farming", material) * amount * xpMultiplier;
                    if (farmingXP > 0) {
                        EcoSkillsAPI.gainSkillXP(p, Skills.INSTANCE.getByID("farming"), farmingXP);
                        break;
                    }
                    double fishingXP = getSkillXP("fishing", material) * amount * xpMultiplier;
                    if (fishingXP > 0) {
                        EcoSkillsAPI.gainSkillXP(p, Skills.INSTANCE.getByID("fishing"), fishingXP);
                        break;
                    }
                    double zindanXP = getSkillXP("zindan", material) * amount * xpMultiplier;
                    if (zindanXP > 0) {
                        EcoSkillsAPI.gainSkillXP(p, Skills.INSTANCE.getByID("zindan"), zindanXP);
                        break;
                    }
                    double alchemyXP = getSkillXP("alchemy", material) * amount * xpMultiplier;
                    if (alchemyXP > 0) {
                        EcoSkillsAPI.gainSkillXP(p, Skills.INSTANCE.getByID("alchemy"), alchemyXP);
                        break;
                    }
                } catch(NullPointerException ignored){

                }
            }
        }
    }

    private double getSkillXP(String skillName, Material material) {
        FileConfiguration config = plugin.getConfig();
        List<String> skillXPList = config.getStringList("skillxp." + skillName);
        for (String entry : skillXPList) {
            String[] parts = entry.split(":");
            if (parts.length == 2) {
                Material mat = Material.matchMaterial(parts[0]);
                if (mat == material) {
                    return Double.parseDouble(parts[1]);
                }
            }
        }
        return 0.0;
    }

}
