package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KDAStat implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void kdastat(EntityDamageByEntityEvent event) {
        Entity vuran = event.getDamager();
        Entity vurulan = event.getEntity();
        ConsoleCommandSender cs = Bukkit.getConsoleSender();

        if (event.isCancelled()) {
            return;
        }

        if (vuran instanceof Player) {
            if (vurulan instanceof Player) {
                Player vurulanoyuncu = (Player) event.getEntity();
                Player vuranoyuncu = (Player) event.getDamager();
                if (vurulanoyuncu.getHealth() - event.getFinalDamage() <= 0) {
                    Bukkit.dispatchCommand(cs, "bq point " + vuranoyuncu.getName() + " add Gorevler.arenakill 1");
                    Bukkit.dispatchCommand(cs, "bq point " + vurulanoyuncu.getName() + " add Gorevler.arenadeath 1");
                }
            }
        }
        if (vuran instanceof Arrow) {
            if (vurulan instanceof Player) {
                Player vurulanoyuncu = (Player) event.getEntity();
                Projectile proj = (Projectile) event.getDamager();
                Entity projshooter = (Entity) proj.getShooter();
                if (projshooter instanceof Player) {
                    Player vuranoyuncuokcu = (Player) proj.getShooter();
                    if (vurulanoyuncu.getHealth() - event.getFinalDamage() <= 0) {
                        Bukkit.dispatchCommand(cs, "bq point " + vuranoyuncuokcu.getName() + " add Gorevler.arenakill 1");
                        Bukkit.dispatchCommand(cs, "bq point " + vurulanoyuncu.getName() + " add Gorevler.arenadeath 1");
                    }
                }
            }
        }
    }


}
