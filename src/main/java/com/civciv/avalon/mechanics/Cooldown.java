package com.civciv.avalon.mechanics;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    public static HashMap<UUID, Double> cooldowns;

    public static void setupCooldown(){
        cooldowns = new HashMap<>();
    }

    public static boolean setCooldown(Player player, int seconds){
        double delay = System.currentTimeMillis() + (seconds + 1700);
        cooldowns.put(player.getUniqueId(), delay);
        return false;
    }

    public static boolean checkCooldown(Player player){
        if(!cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
}
