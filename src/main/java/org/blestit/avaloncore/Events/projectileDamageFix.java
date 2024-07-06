package org.blestit.avaloncore.Events;

import com.willfp.ecoskills.api.EcoSkillsAPI;
import com.willfp.ecoskills.stats.Stats;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;

public class projectileDamageFix implements Listener {
    private JavaPlugin plugin;

    public projectileDamageFix(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileDamage(EntityDamageByEntityEvent event) {
        //blaze damage fix
        if (event.getDamager() instanceof Projectile projectile) {
            ProjectileSource projectileSource = projectile.getShooter();
            if (projectileSource instanceof Blaze blaze && blaze.getCustomName() != null) {
                damagefix(event, blaze.getCustomName());
            }
            if(projectileSource instanceof Wither wither && wither.getCustomName() != null) {
                damagefix(event, wither.getCustomName());
            }
            if(projectileSource instanceof Ghast ghast && ghast.getCustomName() != null) {
                damagefix(event, ghast.getCustomName());
            }
            if(projectileSource instanceof Skeleton skeleton && skeleton.getCustomName() != null) {
                damagefix(event, skeleton.getCustomName());
            }
        }
    }


    public void damagefix(EntityDamageByEntityEvent event, String CustomName){

        ConfigurationSection projectileDamageFixSection = plugin.getConfig().getConfigurationSection("projectiledamagefix");
        if (projectileDamageFixSection != null) {
            for (String key : projectileDamageFixSection.getKeys(false)) {
                ConfigurationSection section = projectileDamageFixSection.getConfigurationSection(key);
                if (section != null) {
                    if(!(event.getEntity() instanceof Player)) {
                        return;
                    }
                    Player player = (Player) event.getEntity();

                    String nameStartsWith = section.getString("namestartswith");
                    int damage = section.getInt("damage");
                    if (CustomName.startsWith(nameStartsWith)) {
                        try{
                            double finaldamage = damage * ( 1 - (double) EcoSkillsAPI.getStatLevel(player, Stats.INSTANCE.getByID("defense")) / (EcoSkillsAPI.getStatLevel(player, Stats.INSTANCE.getByID("defense")) + 100));
                            event.setDamage(finaldamage);
                        } catch(NullPointerException ignored) {
                            return;
                        } finally {
                            break; // Match found, no need to check further
                        }
                    }
                }
            }
        }

    }
}
