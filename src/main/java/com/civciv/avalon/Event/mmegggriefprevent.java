package com.civciv.avalon.Event;

import com.civciv.avalon.GrapplingHook;
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class mmegggriefprevent implements Listener {
    GrapplingHook plugin;

    public mmegggriefprevent(GrapplingHook plugin) {
        this.plugin = plugin;
    }


    String mobcagirici = "null";

    //PUŞTLARI BANLAMA V2 (EVENT TROLL)
    @EventHandler
    public void onyumurtaspawn(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.PIG_SPAWN_EGG) {
            if (!Objects.requireNonNull(player.getLocation().getWorld()).getName().equals("bskyblock_world")) {
                String playername = player.getName();
                mobcagirici = playername;
            }
        }
    }
    @EventHandler
    public void mmeggspawn(EntitySpawnEvent event){
        if(event.getLocation().getWorld().getName().equals("bskyblock_world")) {
            return;
        }
        new BukkitRunnable() {

            public void run() {
                String mobname = event.getEntity().getCustomName();
                List<String> fishmobnames = plugin.getConfig().getStringList("");

                for (String fmobnames : fishmobnames) {
                    if (mobname.startsWith(fmobnames)) {
                        Damageable entity = (Damageable) event.getEntity();
                        entity.setHealth(0);
                    }
                }
                cancel();
            }
        }.runTaskTimer(plugin, 3L, 200L);
    }

   // @EventHandler
   // public void onmmspawnevent(MythicMobSpawnEvent event){
   //     String worldad = event.getLocation().getWorld().getName();
   //         if(!String.valueOf(event.getMobType()).equals("MythicMob{endgolem}")){
   //             if (!worldad.equals("bskyblock_world")) {
   //                 String mobtype = String.valueOf(event.getMobType());
   //                 Location eventloc = event.getLocation();
   //                 event.setCancelled(true);
//
   //                 if(!(mobcagirici.equals("null"))) {
   //                     Player player = Bukkit.getPlayer(mobcagirici);
   //                     player.sendMessage("§8[§6AVALON§8] §cAda dışında bu yaratığı çağıramazsınız.");
   //                 }
//
   //                 plugin.getLogger().log(Level.WARNING, mobcagirici + "adlı oyuncunun " + mobtype + " adlı yaratığı " + eventloc + " lokasyonuna koyduğundan dolayı silinmiştir.");
//
   //         }
   //     }
//
   //     mobcagirici = "null";
   // }

   //@EventHandler
   //public void onmobspawn(EntitySpawnEvent event){
   //    Entity entity = event.getEntity();

   //    ArrayList<String> mobnamelist = new ArrayList<String>();
   //}
}
