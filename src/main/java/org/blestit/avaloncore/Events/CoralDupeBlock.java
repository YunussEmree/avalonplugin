package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

public class CoralDupeBlock implements Listener {


    @EventHandler
    public void dupeblock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        if (!player.hasPermission("avalon.bypassmercanplacecontrol")) {
            if (Objects.requireNonNull(player.getLocation().getWorld()).getName().equals("bskyblock_world")) {
                if (block.getType() == Material.HORN_CORAL_BLOCK || block.getType() == Material.BUBBLE_CORAL_BLOCK || block.getType() == Material.FIRE_CORAL_BLOCK || block.getType() == Material.TUBE_CORAL_BLOCK || block.getType() == Material.BRAIN_CORAL_BLOCK) {

                    ConsoleCommandSender cs = Bukkit.getConsoleSender();

                    event.setCancelled(true);

                    event.setCancelled(true);
                    player.sendMessage("§6§lAVALON §b> §cBu bloğu adanıza koymanız yasaktır.");
                }
            }
        }
    }
}
