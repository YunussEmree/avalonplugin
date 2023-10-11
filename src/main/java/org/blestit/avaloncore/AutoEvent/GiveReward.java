package org.blestit.avaloncore.AutoEvent;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class GiveReward {

    public GiveReward(String player1, String player2, String player3, String player4, String player5){
        ConsoleCommandSender cs = Bukkit.getConsoleSender();

        Bukkit.dispatchCommand(cs, "crazycrates give p EfsaneviKutu "+ player1 + " 1");
        Bukkit.dispatchCommand(cs, "crazycrates give p NadirKutu "+ player1 + " 1");

        Bukkit.dispatchCommand(cs, "crazycrates give p EfsaneviKutu "+ player2 + " 1");

        Bukkit.dispatchCommand(cs, "crazycrates give p NadirKutu "+ player3 + " 3");

        Bukkit.dispatchCommand(cs, "crazycrates give p NadirKutu "+ player4 + " 2");

        Bukkit.dispatchCommand(cs, "crazycrates give p NadirKutu "+ player5 + " 1");
    }
}
