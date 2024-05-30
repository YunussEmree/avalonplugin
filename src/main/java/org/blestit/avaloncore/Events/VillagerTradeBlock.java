package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class VillagerTradeBlock implements Listener {

    @EventHandler
    public void tradeblock(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getWorld().equals("bskyblock_world")) {
            if (!player.hasPermission("avalon.bypasstradecontrol")) {
                if (event.getHand() == EquipmentSlot.HAND) {
                    if (event.getRightClicked().getType() == EntityType.VILLAGER || event.getRightClicked().getType() == EntityType.WANDERING_TRADER) {
                        String playername = player.getName();
                        ConsoleCommandSender cs = Bukkit.getConsoleSender();
                        System.out.println(playername + " adlı oyuncu illegal girişiminde bulunduğu için kicklendi. (Illegal trade was blocked by AVALON)");
                        Bukkit.dispatchCommand(cs, "kick " + playername + " -s Köylü ile ticaret yapmanız yasaktır.");
                    }
                }
            }
        }
    }
}
