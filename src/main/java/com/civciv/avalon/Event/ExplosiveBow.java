/* package com.civciv.avalon;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;


public class ExplosiveBow implements Listener {

    GrapplingHook plugin;

    public ExplosiveBow(GrapplingHook plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();
        Location arrowloc = arrow.getLocation();
        World world = player.getWorld();
        if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null){
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§5Patlayıcı ok")) {
            world.createExplosion(arrowloc, 4, false, false);
            arrow.remove();
        }
        }
    }
}
*/