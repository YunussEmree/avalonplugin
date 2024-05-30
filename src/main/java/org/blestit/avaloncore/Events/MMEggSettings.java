package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

public class MMEggSettings implements Listener {


    Plugin plugin;

    public MMEggSettings(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void MythicMobeggban(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.PIG_SPAWN_EGG)) {
            if (!event.getPlayer().getWorld().getName().equalsIgnoreCase("bskyblock_world")) {
                event.setCancelled(true);
                Player player = event.getPlayer();
                if (Objects.equals(event.getHand(), EquipmentSlot.HAND))
                    player.sendMessage("§7[§6Avalon§7] " + ChatColor.RED + "Bu yumurtayı oyuncu adası dışında başka bir yerde kullanamazsınız!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void MythicMobdespawn(CreatureSpawnEvent event) {

        if (!event.getEntity().getWorld().getName().equalsIgnoreCase("bskyblock_world")) {
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    String mobname = event.getEntity().getCustomName();
                    List<String> fishmobs = plugin.getConfig().getStringList("sumobslist");

                    for (String fishmob : fishmobs) {
                        if (mobname != null) {
                            if (mobname.contains(fishmob)) {
                                Damageable mob = event.getEntity();


                                Location loc = new Location(mob.getWorld(), mob.getLocation().getX(), -1000, mob.getLocation().getZ());
                                //If mob dead with no teleport, mob dropping rewards
                                mob.teleport(loc);

                                //mob.setHealth(0);

                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }, 5);

        }
    }
}
