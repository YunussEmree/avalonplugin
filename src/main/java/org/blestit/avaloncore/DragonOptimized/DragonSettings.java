package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static org.blestit.avaloncore.Dragon.spawn.bizimDragon;

public class DragonSettings implements Listener {


    public static double bizimDragonAreaDamage;
    public static double bizimDragonFireballDamage;


    //EJDERİN PORTALDA KALMASINI ENGELLEME (YERDE ASILI KALMASINI ENGELLEME)
    @EventHandler
    public void dragonbreak(EnderDragonChangePhaseEvent event){
        if(event.getNewPhase().equals(EnderDragon.Phase.FLY_TO_PORTAL)){
            event.setCancelled(true);
        }
        if(event.getNewPhase().equals(EnderDragon.Phase.LAND_ON_PORTAL)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void dragonfireball(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof AreaEffectCloud){
            event.setDamage(bizimDragonAreaDamage);
        }
        if (event.getDamager() instanceof LargeFireball){
            Projectile proj = (Projectile) event.getDamager();
            Entity shooter = (Entity) proj.getShooter();

            if(shooter.equals(bizimDragon)){
                event.setDamage(bizimDragonFireballDamage);
            }
        }
    }
}
