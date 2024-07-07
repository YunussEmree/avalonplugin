package org.blestit.avaloncore.Events;

import org.blestit.avaloncore.Modules.MapSorter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Collections;
import java.util.HashMap;

public class AlazBossRewardManager implements Listener {

    public HashMap<String, Integer> map = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR)
    public void ondamage(EntityDamageByEntityEvent event) {
        if (event.getEntity().getCustomName() == null) return;
        if (event.getEntity().getCustomName().startsWith("§8[§7Lv??§8] §cÖlü Devin Yardımcısı")) {
            if (event.getDamager() instanceof Player player) {
                if (map.get(player.getName()) == null) {
                    map.put(player.getName(), (int) event.getFinalDamage());
                } else {
                    map.put(player.getName(), map.get(player.getName()) + (int) event.getFinalDamage());
                }
            }
        }
    }

    @EventHandler
    public void ondeath(EntityDeathEvent event) {

        if (event.getEntity().getCustomName() == null) return;

        if (event.getEntity().getCustomName().startsWith("§8[§cLv45§8] §cBlaze ")) {
            //give reward
            Bukkit.broadcastMessage(map.toString());

            map = MapSorter.sortByValueDescending(map);
            //todo sorting will be checked

            String rewardmessage =
                    "                - \"&c▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\"\n" +
                    "                - \"&6    Blaze Mağlup Edildi\"\n" +
                    "                - \"&f\"\n" +
                    "                - \"&c&l       1. %top_name_1%: %top_damage_1%\"\n" +
                    "                - \"&6&l       2. %top_name_2%: %top_damage_2%\"\n" +
                    "                - \"&e&l       3. %top_name_3%: %top_damage_3%\"\n" +
                    "                - \"&f\"\n" +
                    "                - \"&b      Your damage: %personal_damage%\"\n" +
                    "                - \"&f\"\n" +
                    "                - \"&c▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\"";

            //todo reward message will be formatted

            //todo rewards will be given
            map.clear();
        }
    }





}
