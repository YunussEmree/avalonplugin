package org.blestit.avaloncore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class DisableQuitMessage implements Listener {

    @EventHandler
    public void disablemessage(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }


}
