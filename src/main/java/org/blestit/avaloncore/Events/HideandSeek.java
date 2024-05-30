package org.blestit.avaloncore.Events;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Objects;

public class HideandSeek implements Listener {


    public static HashMap<String, Long> hideandseektoolcooldown = new HashMap<String, Long>();
    Plugin plugin;

    public HideandSeek(Plugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void SaklambacTool(PlayerInteractEntityEvent event) {
        int cooldownTime = 4;
        String prefix = plugin.getConfig().getString("saklambactool.text.prefix");
        String ebeledin = plugin.getConfig().getString("saklambactool.text.ebeledin");
        String ebelendin = plugin.getConfig().getString("saklambactool.text.ebelendin");
        String ebeyiebeleyemezsin = plugin.getConfig().getString("saklambactool.text.ebeyiebeleyemezsin");
        String particle = plugin.getConfig().getString("saklambactool.effect.particle");
        String sound = plugin.getConfig().getString("saklambactool.effect.sound");
        String needlore = plugin.getConfig().getString("saklambactool.item.need_lore");
        String ebeefekt = plugin.getConfig().getString("saklambactool.item.ebe_effect");
        String cooldowntext = plugin.getConfig().getString("saklambactool.text.cooldown_text");
        int particlepower = plugin.getConfig().getInt("saklambactool.effect.particlepower");
        String command = plugin.getConfig().getString("saklambactool.item.command");
        String msgcolor = plugin.getConfig().getString("saklambactool.text.msg_color");
        String broadcastmsg = plugin.getConfig().getString("saklambactool.text.broadcast_text");
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        Location location = entity.getLocation();
        World world = entity.getWorld();
        String playername = event.getPlayer().getName();
        String entityname = event.getRightClicked().getName();
        if (event.getHand() == EquipmentSlot.HAND) {
            if (event.getRightClicked() instanceof Player) {
                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                        && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(needlore)) {

                    if (hideandseektoolcooldown.containsKey(player.getName())) {
                        int secondsLeft = (int) (((hideandseektoolcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                        if (secondsLeft > 0) {
                            // Still cooling down
                            player.sendMessage(ChatColor.RED + "Biraz yavaşla! Şu kadar süre sonra tekrar dene: " + secondsLeft);

                            return;
                        }
                    }

                    assert ebeefekt != null;
                    if (((Player) entity).hasPotionEffect(Objects.requireNonNull(PotionEffectType.getByName(ebeefekt)))) {
                        player.sendMessage(prefix + ebeyiebeleyemezsin);
                    } else {
                        world.spawnParticle(Particle.valueOf(particle), location, particlepower, 0, 0, 0);
                        world.playSound(location, Sound.valueOf(sound), 3, 1);
                        player.sendMessage(prefix + ebeledin);
                        entity.sendMessage(prefix + ebelendin);
                        ((Player) entity).performCommand(command);
                        Bukkit.broadcastMessage(prefix + msgcolor + playername + ", " + entityname + " " + broadcastmsg);

                        hideandseektoolcooldown.put(player.getName(), System.currentTimeMillis());
                    }

                }
            }
        }
    }


}
