package org.blestit.avaloncore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DisableJoinMessage implements Listener {


    @EventHandler
    public void disableMessage(PlayerJoinEvent event){
        event.setJoinMessage("");
    }
}
