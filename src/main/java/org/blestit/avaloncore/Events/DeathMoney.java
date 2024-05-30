package org.blestit.avaloncore.Events;

import org.blestit.avaloncore.AvalonCore;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMoney implements Listener {

    AvalonCore plugin;
    public DeathMoney(AvalonCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void DeathMoneyMessage(PlayerDeathEvent event) {
        FileConfiguration config = plugin.getConfig();
        Player player = event.getEntity();
        String percent;
        if (player.hasPermission("deathmoney.0")) {
            String msg = config.getString("mesaj2");
            percent = ChatColor.translateAlternateColorCodes('&', msg);
            player.sendMessage(percent);
            System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: Parası gitmedi");
        } else {
            int[] permissions = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
            for (int permission : permissions) {
                if (player.hasPermission("deathmoney." + permission)) {
                    handleDeathMoney(player, permission, config);
                    break;
                }
            }
        }
    }

    private void handleDeathMoney(Player player, int percent, FileConfiguration config) {
        int deathBalance = (int)(plugin.economy.getBalance(player) * (percent / 100.0));
        plugin.economy.withdrawPlayer(player, (double)deathBalance);
        String msg = config.getString("mesaj");
        String balance = "" + deathBalance;
        String msgFormated = formatMessage(msg, percent, balance);
        System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
        player.sendMessage(msgFormated);
    }

    private String formatMessage(String msg, int percent, String balance) {
        return ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", String.valueOf(percent)).replace("%money%", balance));
    }
}