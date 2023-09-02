
package com.civciv.avalon.dragon;


import com.civciv.avalon.GrapplingHook;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static com.civciv.avalon.dragon.spawn.bizimDragonHealth;
import static com.civciv.avalon.dragon.spawn.bizimDragonThunderAttack;
import static com.civciv.avalon.dragon.type.*;

public class skills {

    public static void thunder(EnderDragon dragon) {


       List<Entity> yaratıklar = dragon.getNearbyEntities(50, 50, 50);
       for(Entity yaratık : yaratıklar){
           if(yaratık instanceof Player){
               Player p = (Player) yaratık;
               World world = dragon.getLocation().getWorld();

               p.damage(bizimDragonThunderAttack);

               world.strikeLightning(new Location(world, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()));

           }
       }
    }

    public static void fireball(EnderDragon dragon){

    }


    public static void Rush(EnderDragon dragon, Player player){

        
    }
/*
    public static void Heal(EnderDragon dragon, Player player, Location loc, World world){

        for(int i=0; i<3; i++)
        if (Math.random() > 0 && Math.random() < 0.05){
            Location kristalloc = new Location(world, 46, 46, -22);
            loc.getWorld().spawnEntity(kristalloc, EntityType.ENDER_CRYSTAL);

     //       for(loc.getWorld().getBlockAt(kristalloc).getType().isAir() = dragon.setHealth(dragon.getHealth() + bizimDragonHealth/10) ; break;);
        }

        if (Math.random() > 0 && Math.random() < 0.05){

        }

        if (Math.random() > 0 && Math.random() < 0.05){

        }

        if (Math.random() > 0 && Math.random() < 0.05){

        }

        if (Math.random() > 0 && Math.random() < 0.05){

        }

        if (Math.random() > 0 && Math.random() < 0.05){

        }

        if (Math.random() > 0 && Math.random() < 0.05){

        }

        if (Math.random() > 0 && Math.random() < 0.05){

        }

    }
*/
}

