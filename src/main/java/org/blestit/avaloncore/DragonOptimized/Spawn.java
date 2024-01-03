package org.blestit.avaloncore.DragonOptimized;

import org.blestit.avaloncore.AvalonCore;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;

public class Spawn {

    AvalonCore plugin;

    public Spawn(AvalonCore plugin, Location location, Player player, World world) {
        this.plugin = plugin;

        EnderDragon dragon = location.getWorld().spawn(location,EnderDragon.class);

    }


}
