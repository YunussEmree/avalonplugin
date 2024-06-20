package org.blestit.avaloncore.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class FirstJoinMessage implements Listener {

    Plugin plugin;

    public FirstJoinMessage(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void AFK(PlayerJoinEvent event) {
        Player player = event.getPlayer();


        if (!event.getPlayer().hasPlayedBefore()) {
            new BukkitRunnable() {

                public void run() {
                    List<String> messages = plugin.getConfig().getStringList("firstjoinmessages.messages");

                    int randomnumber = (int) Math.floor(Math.random() * messages.size());
                    int oyuncusayi = (Bukkit.getOfflinePlayers().length);
                    String msg = messages.get(randomnumber);
                    String msgyeni = msg.replace("%player%", player.getName());
                    String msgyeniyeni = msgyeni.replace("&", "§");
                    String msgyeniyeniyeni = msgyeniyeni.replace("%playersize%", String.valueOf(oyuncusayi));
                    Bukkit.broadcastMessage(msgyeniyeniyeni);

                    player.sendMessage("§a|----------------------------------------------|");
                    player.sendMessage("                 §6§lAvalona Hoşgeldin!");
                    player.sendMessage("§e");
                    player.sendMessage("§eBaşlangıçta §c/rehber §eve §c/sss §eyi okuyarak bilgi edinebilirsin.");
                    player.sendMessage("§eOyunla ilgili aklına takılan tüm soruları §crehberlere §esorabilirsin.");
                    player.sendMessage("§eKurallara uygun davranışlar sergilemeniz yararınıza olacaktır §c/kurallar§e.");
                    player.sendMessage("§a|----------------------------------------------|");

                    cancel();
                }
            }.runTaskTimer(plugin, 50, 99999);
        }

    }
}
