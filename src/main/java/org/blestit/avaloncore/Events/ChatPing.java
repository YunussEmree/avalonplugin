package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;

public class ChatPing implements Listener {

    Plugin plugin;

    public ChatPing(Plugin plugin) {
        this.plugin = plugin;
    }




    public static boolean hasPermission(Player player, String permission) {
        Permission p = new Permission(permission, PermissionDefault.FALSE);
        return player.hasPermission(p);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent e) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (e.getMessage().contains(player.getName())) {
                ;
                String lastColor = ChatColor.getLastColors(e.getMessage());
                e.setMessage(e.getMessage().replaceAll(
                        player.getName(), ChatColor.GREEN + player.getName() + (lastColor.isEmpty() ? ChatColor.RESET : lastColor)
                ));
                if (!e.isCancelled()) {
                    Sound pingsound1 = getPingSound();

                    if (pingsound1 == null) {
                        return;
                    }
                    if (!player.isOp()) {
                        if (hasPermission(player, "avalon.ping")) {
                            player.playSound(player.getEyeLocation(), pingsound1, 1, 1);
                        }
                    }
                }
            }
        });
        //helper ping
        if (e.getMessage().contains("Rehber") || e.getMessage().contains("rehber")) {
            if (!e.isCancelled()) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    Sound pingsound1 = getPingSound();


                    if (hasPermission(player, "avalon.rehberping")) {
                        if (!hasPermission(player, "avalon.op")) {
                            player.playSound(player.getEyeLocation(), pingsound1, 1, 1);
                        }
                    }
                });
            }
            //chat click msg
        }
    }

    private Sound getPingSound() {
        String pingsound = plugin.getConfig().getString("PlayerPing.Sound");

        try {
            return Sound.valueOf(pingsound);
        } catch (IllegalArgumentException e) {
            try {
                return Sound.valueOf(pingsound);
            } catch (IllegalArgumentException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }
}
