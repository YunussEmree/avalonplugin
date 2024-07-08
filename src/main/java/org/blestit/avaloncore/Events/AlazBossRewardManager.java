package org.blestit.avaloncore.Events;

import org.blestit.avaloncore.AvalonCore;
import org.blestit.avaloncore.Modules.ChatColorFix;
import org.blestit.avaloncore.Modules.ExecuteCommandWithChance;
import org.blestit.avaloncore.Modules.MapSorter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.*;

public class AlazBossRewardManager implements Listener {

    AvalonCore plugin;

    public AlazBossRewardManager(AvalonCore plugin) {
        this.plugin = plugin;
    }


    public HashMap<String, Integer> map = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR)
    public void ondamage(EntityDamageByEntityEvent event) {
        if (event.getEntity().getCustomName() == null) return;
        if (event.getEntity().getCustomName().startsWith("§8[§7Lv??§8] §cÖlü Devin Yardımcısı")) {
            if (event.getDamager() instanceof Player player) {
                if (map.get(player.getName()) == null) {
                    map.put(player.getName(), (int) event.getFinalDamage());
                } else {
                    map.put(player.getName(), map.get(player.getName()) + (int) event.getFinalDamage());
                }
            }
        }
    }

    @EventHandler
    public void ondeath(EntityDeathEvent event) {

        if (event.getEntity().getCustomName() == null) return;

        String namestartswith = plugin.getConfig().getString("blazeboss.namestartswith");

        assert namestartswith != null;
        if (event.getEntity().getCustomName().startsWith(namestartswith)) {

            map = MapSorter.sortByValueDescending(map);
            System.out.println("Blaze Mağlup Edildi ve Sıralama: " + map.toString());

            String rewardmessage = plugin.getConfig().getString("blazeboss.rewardmessage");

            if (map.isEmpty()){
                System.out.println("[AVALONCORE] No player has dealt damage to the boss");
                return;
            }
            if(rewardmessage == null) {
                System.out.println("[AVALONCORE] Reward message is null");
                return;
            }

            rewardmessage = switch (map.keySet().toArray().length) {
                case (1) -> rewardmessage
                        .replace("%top_name_1%", map.keySet().toArray()[0].toString())
                        .replace("&6&l       2. %top_name_2%: %top_damage_2%", "")
                        .replace("&e&l       3. %top_name_3%: %top_damage_3%", "")
                        .replace("%top_damage_1%", map.values().toArray()[0].toString());
                case (2) -> rewardmessage
                        .replace("%top_name_1%", map.keySet().toArray()[0].toString())
                        .replace("%top_name_2%", map.keySet().toArray()[1].toString())
                        .replace("&e&l       3. %top_name_3%: %top_damage_3%", "")
                        .replace("%top_damage_1%", map.values().toArray()[0].toString())
                        .replace("%top_damage_2%", map.values().toArray()[1].toString());
                default -> rewardmessage
                        .replace("%top_name_1%", map.keySet().toArray()[0].toString())
                        .replace("%top_name_2%", map.keySet().toArray()[1].toString())
                        .replace("%top_name_3%", map.keySet().toArray()[2].toString())
                        .replace("%top_damage_1%", map.values().toArray()[0].toString())
                        .replace("%top_damage_2%", map.values().toArray()[1].toString())
                        .replace("%top_damage_3%", map.values().toArray()[2].toString())
                ;
            };

            rewardmessage = ChatColorFix.fixColor(rewardmessage);

            Set<String> players = map.keySet();
            for (int i = 0; i < players.size(); i++) {
                String player = (String) players.toArray()[i];
                if (player != null && Bukkit.getPlayer(player) != null) {
                    Bukkit.getPlayer(player).sendMessage(rewardmessage.replace("%personal_damage%", map.get(player).toString()));
                }

            }

            rewardPlayers(map);
            //todo rewards will be given
            map.clear();
        }
    }

    public void rewardPlayers(Map<String, Integer> damageMap) {
        ConfigurationSection rewardsSection = plugin.getConfig().getConfigurationSection("blazeboss.RewardCommands");
        if (rewardsSection == null) return;

        List<String> allPlayersRewards = rewardsSection.getStringList("all");
        Set<String> playerNames = damageMap.keySet();

        // Reward all players
        playerNames.forEach(playerName -> allPlayersRewards.forEach(command -> ExecuteCommandWithChance.executeCommand(command, playerName)));

        // Reward based on rank
        String[] playersArray = playerNames.toArray(new String[0]);
        for (int i = 0; i < playersArray.length; i++) {
            int rank = i + 1; // Rank starts from 1
            List<String> rewards = rewardsSection.getStringList(String.valueOf(rank));
            if (!rewards.isEmpty()) {
                int finalI = i;
                rewards.forEach(command -> ExecuteCommandWithChance.executeCommand(command, playersArray[finalI]));
            } else {
                // Check if "none" is explicitly set for this rank
                String noReward = rewardsSection.getString(String.valueOf(rank));
                if ("none".equals(noReward)) {
                    break; // Stop rewarding if "none" is encountered
                }
            }
        }
    }

}

