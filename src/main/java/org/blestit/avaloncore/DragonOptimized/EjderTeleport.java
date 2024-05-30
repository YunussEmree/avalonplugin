package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import static org.blestit.avaloncore.Dragon.spawn.bizimDragon;
import static org.blestit.avaloncore.Dragon.spawn.ejdertp;

public class EjderTeleport {

    public EjderTeleport(Entity entity, Location loc){
        entity.teleport(loc);
    }

    //if (command.equalsIgnoreCase("/ejderfix")) {
    //    if (player.hasPermission("avalon.ejdertp")) {
    //        ejdertp();
    //    }
    //}
}
