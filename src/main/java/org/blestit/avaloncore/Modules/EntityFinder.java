package org.blestit.avaloncore.Modules;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

public class EntityFinder {
    public static Entity getEntityByCustomName(String startsWith) {
        for (var world : Bukkit.getWorlds()) {
            for (var entity : world.getEntities()) {
                if (entity.getCustomName() != null && entity.getCustomName().startsWith(startsWith)) {
                    return entity;
                }
            }
        }
        return null; // No entity found with the given custom name
    }
}