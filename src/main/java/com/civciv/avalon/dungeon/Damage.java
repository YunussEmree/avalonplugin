package com.civciv.avalon.dungeon;

import com.civciv.avalon.GrapplingHook;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class Damage implements Listener {

    GrapplingHook plugin;

    public Damage(GrapplingHook plugin) {
        this.plugin = plugin;
    }

    public static double permseviye;

    public static HashMap<String, Double> damageMap = new HashMap<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {


        var damager = event.getDamager();

        if (damager instanceof Player) {
            Player player = (Player) damager;

            //dungeon büyüleri
            String world1 = (event.getEntity().getWorld().getName());
            if(world1.equals("zindan")){
                    damageMap.clear();
                    for (int a = 50; a >= 0; a--) {

                        if (player.hasPermission("avalon.zindan" + a)) {


                            permseviye = a;

                            break;
                        }
                        if(a == 1){
                            permseviye = 1;

                            break;
                        }
                    }

                    if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                            && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Eşya özelliği: §cZindan Azabı I")) {
                        int dungeon1 = plugin.getConfig().getInt("Dungeon.buyuhasar.1");

                        Double olmasıGerekenHasar = event.getDamage() * dungeon1 / 100;
                        Double katlanmışHali = (permseviye / 25) * olmasıGerekenHasar;
                        event.setDamage(katlanmışHali * 3);

                        damageMap.put(player.getName(), (((permseviye * 12) / 100) * (dungeon1)));
                    }



                    if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                            && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Eşya özelliği: §cZindan Azabı II")) {
                        if(player.hasPermission("avalon.zindan10")) {
                            int dungeon2 = plugin.getConfig().getInt("Dungeon.buyuhasar.2");

                            Double olmasıGerekenHasar = event.getDamage() * dungeon2 / 100;
                            Double katlanmışHali = (permseviye / 25) * olmasıGerekenHasar;
                            event.setDamage(katlanmışHali * 3);

                            damageMap.put(player.getName(), (((permseviye * 12) / 100) * (dungeon2)));
                        } else {
                            player.performCommand("spawn");
                        }
                    }

                    if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                            && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Eşya özelliği: §cZindan Azabı III")) {
                        if(player.hasPermission("avalon.zindan15")) {
                            int dungeon3 = plugin.getConfig().getInt("Dungeon.buyuhasar.3");

                            Double olmasıGerekenHasar = event.getDamage() * dungeon3 / 100;
                            Double katlanmışHali = (permseviye / 25) * olmasıGerekenHasar;
                            event.setDamage(katlanmışHali * 3);

                            damageMap.put(player.getName(), (((permseviye * 12) / 100) * (dungeon3)));
                        } else {
                            player.performCommand("spawn");
                        }
                    }

                    if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                            && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Eşya özelliği: §cZindan Azabı IV")) {
                        if(player.hasPermission("avalon.zindan20")) {
                            int dungeon4 = plugin.getConfig().getInt("Dungeon.buyuhasar.4");

                            Double olmasıGerekenHasar = event.getDamage() * dungeon4 / 100;
                            Double katlanmışHali = (permseviye / 25) * olmasıGerekenHasar;
                            event.setDamage(katlanmışHali * 3);

                            damageMap.put(player.getName(), (((permseviye * 12) / 100) * (dungeon4)));
                        } else {
                            player.performCommand("spawn");
                        }
                    }

                    if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                            && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Eşya özelliği: §cZindan Azabı V")) {
                        if(player.hasPermission("avalon.zindan25")) {
                            int dungeon5 = plugin.getConfig().getInt("Dungeon.buyuhasar.5");

                            Double olmasıGerekenHasar = event.getDamage() * dungeon5 / 100;
                            Double katlanmışHali = (permseviye / 25) * olmasıGerekenHasar;
                            event.setDamage(katlanmışHali * 3);

                            damageMap.put(player.getName(), (((permseviye * 12) / 100) * (dungeon5)));
                        } else {
                            player.performCommand("spawn");
                        }
                    }

                    if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                            && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Eşya özelliği: §cZindan Azabı VI")) {
                        if (player.hasPermission("avalon.zindan30")) {
                            int dungeon6 = plugin.getConfig().getInt("Dungeon.buyuhasar.6");

                            Double olmasıGerekenHasar = event.getDamage() * dungeon6 / 100;
                            Double katlanmışHali = (permseviye / 25) * olmasıGerekenHasar;
                            event.setDamage(katlanmışHali * 3);

                            damageMap.put(player.getName(), (((permseviye * 12) / 100) * (dungeon6)));
                        } else {
                            player.performCommand("spawn");
                        }
                    }
            }
        }
    }
    @EventHandler
    public void onusbccollect(InventoryClickEvent event){
        
    }
}