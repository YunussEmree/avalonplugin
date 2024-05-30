package org.blestit.avaloncore.Events;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SelfHitBlock implements Listener {
    @EventHandler
    public void selfhitbugbow(EntityDamageByEntityEvent event) {
        Entity vuran = event.getDamager();
        Entity vurulan = event.getEntity();

        if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
            return;
        }

        Projectile proj = (Projectile) event.getDamager();

        if (!(proj.getShooter() instanceof Player)) {
            return;
        }

        if (vuran instanceof Arrow) {
            if (vurulan instanceof Player) {
                Player vuranoyuncu = (Player) proj.getShooter();
                Player vurulanoyuncu = (Player) event.getEntity();

                if (vuranoyuncu.getInventory().getItemInMainHand().getType() == Material.BOW) {
                    if (vuranoyuncu.getName().equals(vurulanoyuncu.getName())) {
                        event.setCancelled(true);
                        event.getDamager().remove();
                    }
                }
            }
        }
    }

}
