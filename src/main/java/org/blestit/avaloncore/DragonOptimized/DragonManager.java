package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.blestit.avaloncore.DragonOptimized.Dragon.dragon;

public class DragonManager implements Listener {

    @EventHandler
    public void teleportDragonCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        String command = event.getMessage();
        if (command.equalsIgnoreCase("/ejderfix")) {
            if (player.hasPermission("avalon.ejdertp")) {
                dragon.teleport(player.getLocation());
            }
        }
    }




}
