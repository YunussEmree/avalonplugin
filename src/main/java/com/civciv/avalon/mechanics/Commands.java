package com.civciv.avalon.mechanics;

import com.civciv.avalon.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.civciv.avalon.dragon.spawn.ejdertp;


public class Commands implements CommandExecutor {
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
                if (cmd.getName().equalsIgnoreCase("givehomingbow")){
                    player.getInventory().addItem(ItemManager.HomingBow);
                }
//                if (cmd.getName().equalsIgnoreCase("giveexplosivebow")) {
//                    player.getInventory().addItem(ItemManager.ExplosiveBow);
//                }
                if (cmd.getName().equalsIgnoreCase("giveaspickaxe")) {
                    player.getInventory().addItem(ItemManager.ASPickaxe);
                }
               if (cmd.getName().equalsIgnoreCase("givetriplebow")) {
                    player.getInventory().addItem(ItemManager.TripleBow);
                }
                if (cmd.getName().equalsIgnoreCase("givetesthomingbow")) {
                    player.getInventory().addItem(ItemManager.testHomingBow);
                }
//                if (cmd.getName().equalsIgnoreCase("givepersonalcompactor")) {
//                    player.getInventory().addItem(ItemManager.PersonalCompactor);
//                }
//                if (cmd.getName().equalsIgnoreCase("giveenchanteditems")) {
//                    player.getInventory().addItem(ItemManager.EnchantedSand);
 //                   player.getInventory().addItem(ItemManager.EnchantedCobblestone);
 //               }
                if (cmd.getName().equalsIgnoreCase("giveUndeadSword")) {
                    player.getInventory().addItem(ItemManager.UndeadSword);
                }
                if (cmd.getName().equalsIgnoreCase("giveTestUndeadSword")) {
                    player.getInventory().addItem(ItemManager.TestUndeadSword);
                }
                if (cmd.getName().equalsIgnoreCase("giveDragonKatili")) {
                    player.getInventory().addItem(ItemManager.DragonKatili);
                }
                if (cmd.getName().equalsIgnoreCase("giveTestDragonKatili")) {
                    player.getInventory().addItem(ItemManager.TestDragonKatili);
                }
                if (cmd.getName().equalsIgnoreCase("giveKubizmSword")) {
                    player.getInventory().addItem(ItemManager.KubizmSword);
                }
                if (cmd.getName().equalsIgnoreCase("giveTestKubizmSword")) {
                    player.getInventory().addItem(ItemManager.TestKubizmSword);
                }
                if (cmd.getName().equalsIgnoreCase("giveSaklambacTool")) {
                    player.getInventory().addItem(ItemManager.SaklambacTool);
                }
                if (cmd.getName().equalsIgnoreCase("givePrismarineBow")) {
                    player.getInventory().addItem(ItemManager.prismarinebow);
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
