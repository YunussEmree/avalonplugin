package org.blestit.avaloncore.Events;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class BlazeFireballBlock implements Listener {

    Plugin plugin;

    public BlazeFireballBlock(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void fireballblock(ProjectileLaunchEvent event) {
        Entity blaze = (Entity) event.getEntity().getShooter();
        String worldisim = plugin.getConfig().getString("blazecancel.world");

        if (blaze instanceof Blaze) {
            if (Objects.requireNonNull(event.getLocation().getWorld()).getName().equals(worldisim)) {
                event.setCancelled(true);
            }
        }
    }

}
