 /*
package com.civciv.avalon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import java.util.List;

public class CommandCompleter implements TabCompleter{
    @Override
    public final List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        String commandName = command.getName();

        if(!(sender instanceof Player)) return null;

        if(commandName.equalsIgnoreCase("avalon")) {
            Player player = (Player) sender;
            if (player.hasPermission("op")) {
                if (args.length == 1) {
                    completions.add("give");
                    //        completions.add("ikinciYol");
                } else if (args.length == 2) {
                    completions.add("HomingBow");
                    completions.add("ExplosiveBow");
                    completions.add("ASPickaxe");
                    completions.add("TripleBow");
                    completions.add("testHomingBow");
                    completions.add("PersonalCompactor");
                    completions.add("EnchantedSand");
                    completions.add("EnchantedCobblestone");
                    completions.add("UndeadSword");
                    completions.add("TestUndeadSword");
                    completions.add("DragonKatili");
                    completions.add("TestDragonKatili");
                    completions.add("KubizmSword");
                    completions.add("TestKubizmSword");
                    completions.add("SaklambacTool");
                    completions.add("Odin");
                    completions.add("aimingbuyu");
                    completions.add("denemetahtasi");
                    completions.add("prismarinebow");

                } else {
                    completions.add("digeryol");
                }
            }
        } else {
            sender.sendMessage("§4Bu komutu kullanmak için yeterli izne sahip değilsin!");
        }



        return completions;
    }
    public static List<String> completions = new ArrayList<>();
}
*/