package org.blestit.avaloncore.Statics;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class afterrestartcommands {


    public static void yayfix(Plugin plugin){
        if(Bukkit.getServer().getOnlinePlayers().size() < 10 ){
            CommandSender sendera = Bukkit.getConsoleSender();
            new BukkitRunnable() {
                public void run() {
                    Bukkit.dispatchCommand(sendera, "plugman reload AVALON");
                    Bukkit.dispatchCommand(sendera, "ecoarmor reload");

                    cancel();
                }
            }.runTaskTimer(plugin, 500, 500);
        }
    }
}
