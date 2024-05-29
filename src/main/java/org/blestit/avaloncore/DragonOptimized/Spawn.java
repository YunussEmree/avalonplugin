package org.blestit.avaloncore.DragonOptimized;

import org.blestit.avaloncore.AvalonCore;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;

public class Spawn {

    AvalonCore plugin;
    Location location;
    Player player;
    World world;

    public Spawn(AvalonCore plugin, Location location, Player player, World world) {
        this.plugin = plugin;
        this.location = location;
        this.player = player;
        this.world = world;

    }


    public void spawnDragon(){

        EnderDragon dragon = location.getWorld().spawn(location,EnderDragon.class);
    }




}
