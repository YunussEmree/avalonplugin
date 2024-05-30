package org.blestit.avaloncore.Events;

import org.bukkit.World;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.Plugin;

public class NerfPhantom implements Listener {
    Plugin plugin;

    public NerfPhantom(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void phantomnerf(EntitySpawnEvent event) {
        World world = event.getEntity().getWorld();
        int chance = plugin.getConfig().getInt("nerfphantom.nerfrate");
        if (world.equals("bskyblock_world")) {
            if (event.getEntity() instanceof Phantom) {
                if (Math.random() * 100 <= chance) {

                    event.setCancelled(true);

                }
            }
        }
    }

}
