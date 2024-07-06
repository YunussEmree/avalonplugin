package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class BlockingHopperDupe implements Listener {

    @EventHandler
    public void onHopper(InventoryPickupItemEvent event) {
        if(event.getInventory().getType() == InventoryType.HOPPER) {
            if(event.getItem().getItemStack().getType() == Material.PIG_SPAWN_EGG) {
                event.setCancelled(true);
            }
        }
    }
}