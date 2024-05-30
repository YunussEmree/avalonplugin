package org.blestit.avaloncore.Events;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class DungeonProtection implements Listener {


    @EventHandler
    public void whenfishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        String world1 = (player.getWorld().getName());
        if (world1.equals("AvalonMap")) {
            World world = Bukkit.getServer().getWorld("AvalonMap");
            assert world != null;
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(world));
            assert regions != null;
            ProtectedRegion region = regions.getRegion("zindanyeni");
            String worldad = event.getPlayer().getWorld().getName();
            if (worldad.equals("AvalonMap") && (region.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) && (!player.hasPermission("giris.zindan")))) {
                ConsoleCommandSender cs = Bukkit.getConsoleSender();
                Bukkit.dispatchCommand(cs, "spawn " + player.getName());
            }
        }
    }



    @EventHandler
    public void whenattack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            String world1 = (player.getWorld().getName());
            if (world1.equals("AvalonMap")) {
                World world = Bukkit.getServer().getWorld("AvalonMap");
                assert world != null;
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionManager regions = container.get(BukkitAdapter.adapt(world));
                assert regions != null;
                ProtectedRegion region = regions.getRegion("zindanyeni");
                String worldad = player.getWorld().getName();
                if (worldad.equals("AvalonMap") && (region.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) && (!player.hasPermission("giris.zindan")))) {
                    ConsoleCommandSender cs = Bukkit.getConsoleSender();
                    Bukkit.dispatchCommand(cs, "spawn " + player.getName());
                }
            }
        }
    }
}
