package com.civciv.avalon.Event;

import com.civciv.avalon.GrapplingHook;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class HomingBow implements Listener {

    GrapplingHook plugin;

    public HomingBow(GrapplingHook plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        int aiming1 = plugin.getConfig().getInt("homingbow.aiming1");
        int aiming2 = plugin.getConfig().getInt("homingbow.aiming2");
        int aiming3 = plugin.getConfig().getInt("homingbow.aiming3");
        if (event.getEntity() instanceof Player) {
            Entity arrow = event.getProjectile();
            Player player = (Player) event.getEntity();

            player.getInventory().getItemInMainHand();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null) {


                if (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Takip I")) {

                    new BukkitRunnable() {

                        public void run() {

                            if (arrow.isOnGround() || arrow.isDead()) {
                                cancel();
                            } else {
                                List<Entity> nearest = arrow.getNearbyEntities(aiming1, aiming1, aiming1);

                                for (Entity target : nearest) {

                                    if (player.hasLineOfSight(target) && target instanceof LivingEntity && !target.isDead() && target != player) {
                                        arrow.setVelocity(target.getLocation().toVector().subtract(arrow.getLocation().toVector()));
                                    }
                                }
                            }
                        }
                    }.runTaskTimer(plugin, 0L, 1L);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Takip II")) {
                    new BukkitRunnable() {

                        public void run() {

                            if (arrow.isOnGround() || arrow.isDead()) {
                                cancel();
                            } else {
                                List<Entity> nearest = arrow.getNearbyEntities(aiming2, aiming2, aiming2);

                                for (Entity target : nearest) {
                                    if (player.hasLineOfSight(target) && target instanceof LivingEntity && !target.isDead() && target != player) {
                                        arrow.setVelocity(target.getLocation().toVector().subtract(arrow.getLocation().toVector()));
                                    }
                                }
                            }
                        }
                    }.runTaskTimer(plugin, 0L, 1L);
                }
              if (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Takip III")) {
                    new BukkitRunnable() {

                        public void run() {

                            if (arrow.isOnGround() || arrow.isDead()) {
                                cancel();
                            } else {
                                List<Entity> nearest = arrow.getNearbyEntities(aiming3, aiming3, aiming3);

                                for (Entity target : nearest) {
                                    if (player.hasLineOfSight(target) && target instanceof LivingEntity && !target.isDead() && target != player) {
                                        arrow.setVelocity(target.getLocation().toVector().subtract(arrow.getLocation().toVector()));
                                    }
                                }
                            }
                        }
                    }.runTaskTimer(plugin, 0L, 1L);
                }
            }
        }
    }
}
