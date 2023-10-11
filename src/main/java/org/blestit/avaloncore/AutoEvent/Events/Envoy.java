package org.blestit.avaloncore.AutoEvent.Events;

import org.blestit.avaloncore.AvalonCore;

public class Envoy {

    public void Start(AvalonCore plugin){
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "envoy start");
    }

}
