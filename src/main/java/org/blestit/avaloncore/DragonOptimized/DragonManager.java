package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.blestit.avaloncore.DragonOptimized.Dragon.dragon;
import static org.blestit.avaloncore.DragonOptimized.DragonAltar.altars;
import static org.blestit.avaloncore.DragonOptimized.DragonReward.*;

public class DragonManager implements Listener {

    @EventHandler
    public static void teleportDragon(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        if (command.equalsIgnoreCase("/ejderfix")) {
            if (player.hasPermission("avalon.ejdertp")) {
                dragon.teleport(player.getLocation());
            }
        }
    }


    //Dragon phase break for go to portal and land on portal
    @EventHandler
    public void dragonphasebreak(EnderDragonChangePhaseEvent event){
        if(event.getNewPhase().equals(EnderDragon.Phase.FLY_TO_PORTAL)){
            event.setCancelled(true);
        }
        if(event.getNewPhase().equals(EnderDragon.Phase.LAND_ON_PORTAL)){
            event.setCancelled(true);
        }
    }


    //Blocking the dragon break blocks
    @EventHandler
    public void blockingblockbreak(EntityChangeBlockEvent event){
        if (event.getEntity() instanceof EnderDragon edragon) {
            if (edragon == dragon) {
                event.setCancelled(true);
            }
        }
    }


    public static void killthedragon() {
        if (dragon != null) {
            if (dragon.getHealth() > 0) {
                ConsoleCommandSender cs = Bukkit.getConsoleSender();
                Bukkit.dispatchCommand(cs, "minecraft:kill @e[type=minecraft:ender_dragon]");

                dragonhp = 0;

                dragonBossBar.removeAll();

                for (Location altar : altars) {
                    EndPortalFrame frame = (EndPortalFrame) altar.getBlock().getBlockData();
                    frame.setEye(false);
                    altar.getBlock().setBlockData(frame);
                }

                createstatictik();

                Bukkit.broadcastMessage("§8[§6Avalon§8] §cEjderha düzgün bir şekilde öldürülemediği için ödüller verilmiyor...");
            }
        }
    }

}
