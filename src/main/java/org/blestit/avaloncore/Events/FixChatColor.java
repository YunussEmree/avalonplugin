package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FixChatColor implements Listener {

    //fixing chatcolor for old vip players
    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playername = player.getName();
        if (!player.hasPermission("avalon.kalicirenk")) {
            ConsoleCommandSender cs = Bukkit.getConsoleSender();
            Bukkit.dispatchCommand(cs, "chatcolor " + playername + " f");
            System.out.println(playername + " adlı oyuncunun sohbet rengi sıfırlandı.");
        }
    }
}
