package org.blestit.avaloncore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SlimeSplitEvent;

public class CubeSplitBlock implements Listener {
    @EventHandler
    public void oncubesplit(SlimeSplitEvent event) {
        if (event.getEntity().getWorld().getName().equalsIgnoreCase("AvalonMap")) {
            event.setCancelled(true);
        }
    }
}
