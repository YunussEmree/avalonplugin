package com.civciv.avalon.Event;

import com.civciv.avalon.GrapplingHook;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MayorSystem implements Listener {

    GrapplingHook plugin;

    public MayorSystem(GrapplingHook plugin) {
        this.plugin = plugin;
    }
    Player AG = Bukkit.getPlayer("AvalonGuard");

    public void secim(){
        CommandSender sendera = Bukkit.getConsoleSender();
        new BukkitRunnable() {
            public void run() {

                String mayor1point = PlaceholderAPI.setPlaceholders(AG, "betonquest_point.default.secim1");
                String mayor2point = PlaceholderAPI.setPlaceholders(AG, "betonquest_point.default.secim2");
                String mayor3point = PlaceholderAPI.setPlaceholders(AG, "betonquest_point.default.secim3");
                String mayor4point = PlaceholderAPI.setPlaceholders(AG, "betonquest_point.default.secim4");


            }
        }.runTaskTimer(plugin, 1, 36000);
    }






}
