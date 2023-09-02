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
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Armor implements Listener {

    GrapplingHook plugin;

    public Armor(GrapplingHook plugin){
        this.plugin = plugin;
    }

    public static double korumayuzde;

    public static double permseviye;

    public static HashMap<String, Double> armorMap = new HashMap<>();



    //dungeon zırhları
    @EventHandler
    public void DungeonArmor(EntityDamageByEntityEvent event) {
        var entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;


            String world1 = (event.getEntity().getWorld().getName());
            if(world1.equals("zindan")){
                armorMap.clear();
                korumayuzde = 0;
                for (int a = 50; a >= 0; a--) {

                    if (player.hasPermission("avalon.zindan" + a)) {


                        permseviye = a;

                        break;
                    }
                }

                double buyu1 = plugin.getConfig().getDouble("Dungeon.buyuprotec.1");
                double buyu2 = plugin.getConfig().getDouble("Dungeon.buyuprotec.2");
                double buyu3 = plugin.getConfig().getDouble("Dungeon.buyuprotec.3");
                double buyu4 = plugin.getConfig().getDouble("Dungeon.buyuprotec.4");
                double buyu5 = plugin.getConfig().getDouble("Dungeon.buyuprotec.5");
                double buyu6 = plugin.getConfig().getDouble("Dungeon.buyuprotec.6");

                if(!(player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType().isAir())) {
                    if (player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getHelmet().getItemMeta().getLore() != null &&  player.getInventory().getHelmet().getItemMeta().getLore().stream().anyMatch(el -> el.contains("§6Eşya özelliği: §cZindan Koruyucusu"))) {
                        List<String> helmetLore = (player.getInventory().getHelmet().getItemMeta()).getLore();
                        assert helmetLore != null;
                        if (helmetLore.contains("§6Eşya özelliği: §cZindan Koruyucusu I")) korumayuzde += buyu1;
                        if (helmetLore.contains("§6Eşya özelliği: §cZindan Koruyucusu II")) korumayuzde += buyu2;
                        if (helmetLore.contains("§6Eşya özelliği: §cZindan Koruyucusu III")) korumayuzde += buyu3;
                        if (helmetLore.contains("§6Eşya özelliği: §cZindan Koruyucusu IV")) korumayuzde += buyu4;
                        if (helmetLore.contains("§6Eşya özelliği: §cZindan Koruyucusu V")) korumayuzde += buyu5;
                        if (helmetLore.contains("§6Eşya özelliği: §cZindan Koruyucusu VI")) korumayuzde += buyu6;

                    }
                }

                if(!(player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().isAir())) {
                    if (player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null &&  player.getInventory().getChestplate().getItemMeta().getLore().stream().anyMatch(el -> el.contains("§6Eşya özelliği: §cZindan Koruyucusu"))) {
                        List<String> chestplateLore = Objects.requireNonNull(player.getInventory().getChestplate().getItemMeta()).getLore();

                        assert chestplateLore != null;
                        if (chestplateLore.contains("§6Eşya özelliği: §cZindan Koruyucusu I")) korumayuzde += buyu1;
                        if (chestplateLore.contains("§6Eşya özelliği: §cZindan Koruyucusu II")) korumayuzde += buyu2;
                        if (chestplateLore.contains("§6Eşya özelliği: §cZindan Koruyucusu III")) korumayuzde += buyu3;
                        if (chestplateLore.contains("§6Eşya özelliği: §cZindan Koruyucusu IV")) korumayuzde += buyu4;
                        if (chestplateLore.contains("§6Eşya özelliği: §cZindan Koruyucusu V")) korumayuzde += buyu5;
                        if (chestplateLore.contains("§6Eşya özelliği: §cZindan Koruyucusu VI")) korumayuzde += buyu6;

                    }
                }

                if(!(player.getInventory().getLeggings() != null && player.getInventory().getLeggings().getType().isAir())) {
                    if (player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null &&  player.getInventory().getLeggings().getItemMeta().getLore().stream().anyMatch(el -> el.contains("§6Eşya özelliği: §cZindan Koruyucusu"))) {
                        List<String> leggingsLore = Objects.requireNonNull(player.getInventory().getLeggings().getItemMeta()).getLore();

                        assert leggingsLore != null;
                        if (leggingsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu I")) korumayuzde += buyu1;
                        if (leggingsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu II")) korumayuzde += buyu2;
                        if (leggingsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu III")) korumayuzde += buyu3;
                        if (leggingsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu IV")) korumayuzde += buyu4;
                        if (leggingsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu V")) korumayuzde += buyu5;
                        if (leggingsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu VI")) korumayuzde += buyu6;

                    }
                }

                if(!(player.getInventory().getBoots() != null && player.getInventory().getBoots().getType().isAir())) {
                    if (player.getInventory().getBoots().getItemMeta() != null && player.getInventory().getBoots().getItemMeta().getLore() != null &&  player.getInventory().getBoots().getItemMeta().getLore().stream().anyMatch(el -> el.contains("§6Eşya özelliği: §cZindan Koruyucusu"))) {
                    List<String> bootsLore = Objects.requireNonNull(player.getInventory().getLeggings().getItemMeta()).getLore();

                    assert bootsLore != null;
                    if (bootsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu I")) korumayuzde += buyu1;
                    if (bootsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu II")) korumayuzde += buyu2;
                    if (bootsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu III")) korumayuzde += buyu3;
                    if (bootsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu IV")) korumayuzde += buyu4;
                    if (bootsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu V")) korumayuzde += buyu5;
                    if (bootsLore.contains("§6Eşya özelliği: §cZindan Koruyucusu VI")) korumayuzde += buyu6;
                }
                }
                armorMap.put(player.getName(), permseviye + korumayuzde);
                event.setDamage(event.getDamage() - ((event.getDamage() / 100) * (permseviye + korumayuzde)));
            }
        }
    }
}
