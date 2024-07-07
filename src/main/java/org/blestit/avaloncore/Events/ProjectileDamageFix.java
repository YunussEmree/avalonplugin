package org.blestit.avaloncore.Events;

import com.willfp.ecoskills.api.EcoSkillsAPI;
import com.willfp.ecoskills.stats.Stats;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;

public class ProjectileDamageFix implements Listener {
    private JavaPlugin plugin;

    public ProjectileDamageFix(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileDamage(EntityDamageByEntityEvent event) {
        //blaze damage fix
        if (event.getDamager() instanceof Projectile projectile) {
            ProjectileSource projectileSource = projectile.getShooter();

            if (projectileSource instanceof Blaze blaze && blaze.getCustomName() != null) {
                damagefix(event, blaze);
            }
            if(projectileSource instanceof Wither wither && wither.getCustomName() != null) {
                damagefix(event, wither);
            }
            if(projectileSource instanceof Ghast ghast && ghast.getCustomName() != null) {
                damagefix(event, ghast);
            }
            if(projectileSource instanceof Skeleton skeleton && skeleton.getCustomName() != null) {
                damagefix(event, skeleton);
            }
        }
    }


    public void damagefix(EntityDamageByEntityEvent event, Entity projectileSource){
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

                    if (projectileSource.getCustomName().startsWith(nameStartsWith)) {
                        try{
                            double finaldamage = damage * ( 1 - (double) EcoSkillsAPI.getStatLevel(player, Stats.INSTANCE.getByID("defense")) / (EcoSkillsAPI.getStatLevel(player, Stats.INSTANCE.getByID("defense")) + 100));
                            event.setDamage(finaldamage);
                            break;
                        } catch(NullPointerException ignored) {
                            return;
                        }
                    }
                }
            }
        }

    }
}
