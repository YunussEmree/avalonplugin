package org.blestit.avaloncore.Events;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EnvironmentDamageFix implements Listener {

    @EventHandler
    public void damagefix(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.DamageCause.DROWNING || event.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
                Damageable player = (Player) event.getEntity();

                if (player.getMaxHealth() >= 600) player.damage(50);
                if (player.getMaxHealth() >= 450) player.damage(40);
                if (player.getMaxHealth() >= 300) player.damage(30);
                if (player.getMaxHealth() >= 150) player.damage(20);
            }
        }
    }
}
