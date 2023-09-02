package com.civciv.avalon.Event;

import com.civciv.avalon.GrapplingHook;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;



public class UndeadSword implements Listener {

    public static double damageyuzdepapi;

    GrapplingHook plugin;

    public UndeadSword(GrapplingHook plugin){
        this.plugin = plugin;
    }

    public static double permseviye;

   

   

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {


        var damager = event.getDamager();

        if (damager instanceof Player) {
            Player player = (Player) damager;


                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                        && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§b§lDEBUG MODE")) {
                    player.sendMessage(String.valueOf(event.getDamage()));
                }



        }
    }
}
