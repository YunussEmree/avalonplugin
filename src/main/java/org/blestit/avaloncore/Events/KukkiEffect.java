package org.blestit.avaloncore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class KukkiEffect implements Listener {

    Plugin plugin;

    public KukkiEffect(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void kukkipotion(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        assert player != null;

        if (player.hasPermission("Avalon.kukki")) {

            Collection<PotionEffect> effects = player.getPlayer().getActivePotionEffects();

            new BukkitRunnable() {

                public void run() {
                    if (!player.isDead()) {
                        player.addPotionEffects(effects);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 10, 10);
        }
    }

}
