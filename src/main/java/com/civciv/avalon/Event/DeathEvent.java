package com.civciv.avalon.Event;

import com.civciv.avalon.GrapplingHook;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
    public GrapplingHook plugin;

    public DeathEvent(GrapplingHook plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        FileConfiguration config = this.plugin.getConfig();
        Player player = event.getEntity();
        String percent;
        if (player.hasPermission("deathmoney.0")) {
            String msg = config.getString("mesaj2");
            percent = ChatColor.translateAlternateColorCodes('&', msg);
            player.sendMessage(percent);
            System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: Parası gitmedi");
        } else {
            int deathBalance;
            String msg;
            String balance;
            String msgFormated;
            if (player.hasPermission("deathmoney.5")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.05);
                percent = "5";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.10")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.10);
                percent = "10";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.15")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.15);
                percent = "15";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.20")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.20);
                percent = "20";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.25")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.25);
                percent = "25";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.30")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.30);
                percent = "30";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.35")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.35);
                percent = "35";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.40")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.40);
                percent = "40";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.45")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.45);
                percent = "45";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            } else if (player.hasPermission("deathmoney.50")) {
                deathBalance = (int)(this.plugin.economy.getBalance(player) * 0.5);
                percent = "50";
                this.plugin.economy.withdrawPlayer(player, (double)deathBalance);
                msg = config.getString("mesaj");
                balance = "" + deathBalance;
                msgFormated = ChatColor.translateAlternateColorCodes('&', msg.replace("%percent%", percent).replace("%money%", balance));
                System.out.println("§8[§6AVALON§8] §c" + player.getName() + "adlı oyuncunun death money logu: "+msgFormated);
                player.sendMessage(msgFormated);
            }
        }
    }
}
