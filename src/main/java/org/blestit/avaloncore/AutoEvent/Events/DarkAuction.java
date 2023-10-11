package org.blestit.avaloncore.AutoEvent.Events;

import org.blestit.avaloncore.AvalonCore;

public class DarkAuction{

    public void Start(AvalonCore plugin){
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "darkauctions speedUp");
    }
}
