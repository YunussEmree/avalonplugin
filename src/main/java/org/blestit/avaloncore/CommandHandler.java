package org.blestit.avaloncore;

import org.blestit.avaloncore.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.blestit.avaloncore.Dragon.spawn.ejdertp;


public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4Sadece oyuncular bu komutu kullanabilir.");
            return true;
        } else {
            Player player = (Player) sender;
            if (player.hasPermission("avalon.giveitem")) {
                if (cmd.getName().equalsIgnoreCase("givegrapplinghook")) {
                    player.getInventory().addItem(ItemManager.GrapplingHook);
                }
                if (cmd.getName().equalsIgnoreCase("giveSaklambacTool")) {
                    player.getInventory().addItem(ItemManager.SaklambacTool);
                }
            } else {
                sender.sendMessage("§4Bu komutu kullanmak için yeterli izne sahip değilsin!");
            }

            if (player.hasPermission("avalon.ejdertp")){
                if (cmd.getName().equalsIgnoreCase("ejderfix")) {
                    ejdertp();
                }
            }

            return true;
        }
    }
}

