package org.blestit.avaloncore.Events;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.HashMap;

public class MenuWithF implements Listener {


    public static HashMap<String, Long> itemswapcooldown = new HashMap<String, Long>();


    @EventHandler
    public void openmenuwithf(PlayerSwapHandItemsEvent event) {
        int cooldownTime = 2;
        Player player = event.getPlayer();

        if (!player.hasPermission("avalon.fmenu")) {
            if (!(player.getInventory().getItemInMainHand().getType() == Material.SHIELD || player.getInventory().getItemInOffHand().getType() == Material.SHIELD)) {

                if (itemswapcooldown.containsKey(player.getName())) {
                    int secondsLeft = (int) (((itemswapcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                    if (secondsLeft > 0) {
                        // Still cooling down
                        player.sendMessage(ChatColor.RED + "Biraz yavaşla! Şu kadar süre sonra tekrar dene: " + secondsLeft);

                        return;
                    }
                }


                event.setCancelled(true);
                ConsoleCommandSender cs = Bukkit.getConsoleSender();
                Bukkit.dispatchCommand(cs, "dm execute " + player.getName() + " [openguimenu] menu");
                Location loc = player.getLocation();
                player.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 20, 0);
                itemswapcooldown.put(player.getName(), System.currentTimeMillis());
            }
        } else if (player.isOp()) {
            if (!(player.getInventory().getItemInMainHand().getType() == Material.SHIELD || player.getInventory().getItemInOffHand().getType() == Material.SHIELD)) {

                if (itemswapcooldown.containsKey(player.getName())) {
                    int secondsLeft = (int) (((itemswapcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                    if (secondsLeft > 0) {
                        // Still cooling down
                        player.sendMessage(ChatColor.RED + "Biraz yavaşla! Şu kadar süre sonra tekrar dene: " + secondsLeft);

                        return;
                    }
                }


                event.setCancelled(true);
                ConsoleCommandSender cs = Bukkit.getConsoleSender();
                Bukkit.dispatchCommand(cs, "dm execute " + player.getName() + " [openguimenu] menu");
                Location loc = player.getLocation();
                player.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 20, 0);
                itemswapcooldown.put(player.getName(), System.currentTimeMillis());
            }
        }
    }
}
