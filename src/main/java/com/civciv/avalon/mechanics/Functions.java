package com.civciv.avalon.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.Console;
import java.util.ArrayList;

public class Functions {


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

    /*
    public static int yuzdeliksans(int b){

        int a = 0;

        ArrayList<String> liste = new ArrayList<>();

        for(int i=100-b;i>0; i--){
            liste.add("boş");

        }
        for(int i=b;i>0; i--){
            liste.add("geçti");

        }

        String sonuc = liste.get((int) Math.round(Math.floor((Math.random()*liste.size()))));


        if (sonuc.equals("boş")) {
            a = 0;
        }else{
            a = 1;
        }

        return a;
    }
*/

}
