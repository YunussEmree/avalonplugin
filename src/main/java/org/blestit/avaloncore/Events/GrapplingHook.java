package org.blestit.avaloncore.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class GrapplingHook implements Listener {


    public static HashMap<String, Long> grapplinghookcooldown = new HashMap<String, Long>();
    Plugin plugin;

    public GrapplingHook(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void GrapplingHookAir(PlayerFishEvent event) {
        int cooldownTime = 2;
        String prefix = this.plugin.getConfig().getString("grapplinghook.prefix");
        String nopermtext = this.plugin.getConfig().getString("grapplinghook.nopermtext");
        int airvelocity = this.plugin.getConfig().getInt("grapplinghook.velocity.air.onairvelocity");
        int airyvelocity = this.plugin.getConfig().getInt("grapplinghook.velocity.air.onairsety");
        String cooldowntext = plugin.getConfig().getString("grapplinghook.cooldowntext");
        Player player = event.getPlayer();
        if (!event.getPlayer().getWorld().getName().equals("world")) {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Kancayı kullanarak etrafta gezebilirsiniz.")) {
                if (event.getState().equals(PlayerFishEvent.State.REEL_IN)) {
                    if (player.hasPermission("use.hook")) {
                        if (grapplinghookcooldown.containsKey(player.getName())) {
                            int secondsLeft = (int) (((grapplinghookcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                            if (secondsLeft > 0) {
                                // Still cooling down

                                player.sendMessage(prefix + cooldowntext);
                                return;
                            }
                        }
                        Location playerLocation = player.getLocation();
                        player.setVelocity(playerLocation.getDirection().multiply(airvelocity).setY(airyvelocity));
                        grapplinghookcooldown.put(player.getName(), System.currentTimeMillis());
                    } else {
                        player.sendMessage(prefix + nopermtext);
                    }
                }
            }
        }
    }


    @EventHandler
    public void GrapplingHookLand(PlayerFishEvent event) {
        String prefix = plugin.getConfig().getString("grapplinghook.prefix");
        int cooldownTime = 2;
        String cooldowntext = plugin.getConfig().getString("grapplinghook.cooldowntext");
        String nopermtext = plugin.getConfig().getString("grapplinghook.nopermtext");
        double landvelocity = plugin.getConfig().getDouble("grapplinghook.velocity.land.onlandvelocity");
        int landvelocityx = plugin.getConfig().getInt("grapplinghook.velocity.land.onlandchangeadd.x");
        double landvelocityy = plugin.getConfig().getDouble("grapplinghook.velocity.land.onlandchangeadd.y");
        int landvelocityz = plugin.getConfig().getInt("grapplinghook.velocity.land.onlandchangeadd.z");
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        player.getInventory().getItemInMainHand();
        if (!event.getPlayer().getWorld().getName().equals("world")) {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Kancayı kullanarak etrafta gezebilirsiniz.")) {
                if (event.getState().equals(PlayerFishEvent.State.IN_GROUND)) {
                    if (player.hasPermission("use.hook")) {

                        if (grapplinghookcooldown.containsKey(player.getName())) {
                            int secondsLeft = (int) (((grapplinghookcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                            if (secondsLeft > 0) {
                                // Still cooling down

                                player.sendMessage(prefix + cooldowntext);

                                return;
                            }
                        }

                        Location playerLocation = player.getLocation();
                        Location hookLocation = event.getHook().getLocation();
                        Location change = hookLocation.subtract(playerLocation);
                        Location newlocation = change.add(landvelocityx, landvelocityy, landvelocityz);
                        player.setVelocity(newlocation.toVector().multiply(landvelocity));

                        grapplinghookcooldown.put(player.getName(), System.currentTimeMillis());
                    } else {
                        player.sendMessage(prefix + nopermtext);
                    }
                }
            }
        }
    }

}
