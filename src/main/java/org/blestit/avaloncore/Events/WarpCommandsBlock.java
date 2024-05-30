package org.blestit.avaloncore.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class WarpCommandsBlock implements Listener {


    @EventHandler(priority = EventPriority.MONITOR)
    public void oncommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        String command = event.getMessage();


        if (command.equalsIgnoreCase("/warp sualti")) {
            if (!player.hasPermission("suadasi.izin"))
                event.setCancelled(true);
        }
        if (command.equalsIgnoreCase("/kit")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Şu komudu kullanmayı dene: /kits");
        }

        if (command.startsWith("/warps ")) event.setCancelled(true);
        if (command.startsWith("/warp zindankat")) event.setCancelled(true);

        if (command.contains("warp")) {
            if (!player.isOp()) {
                if (command.equalsIgnoreCase("/warp ciftlik")) {
                    if (!player.hasPermission("warp.vip")) {
                        event.setCancelled(true);
                    }
                }
                if (command.equalsIgnoreCase("/warp mantarcolu")) {
                    if (!player.hasPermission("warp.vip") || !player.hasPermission("warp.vip1")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp orman")) {
                    if (!player.hasPermission("warp.vip")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp ciftlik")) {
                    if (!player.hasPermission("warp.vip")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp maden")) {
                    if (!player.hasPermission("warp.vip") && !player.hasPermission("warp.vip1")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp orumcekyuvasi")) {
                    if (!player.hasPermission("warp.vip") && !player.hasPermission("warp.vip1")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp nether")) {
                    if (!player.hasPermission("warp.vip") && !player.hasPermission("warp.vip1") && !player.hasPermission("warp.vip2")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp end")) {
                    if (!player.hasPermission("warp.vip") && !player.hasPermission("warp.vip1") && !player.hasPermission("warp.vip2")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp sualti")) {
                    if (!player.hasPermission("warp.vip") && !player.hasPermission("warp.vip1") && !player.hasPermission("warp.vip2") && !player.hasPermission("warp.vip3")) {
                        event.setCancelled(true);
                    }
                }


                if (command.equalsIgnoreCase("/warp dragonroom")) {
                    if (!player.hasPermission("provanasvip")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp magmaboss")) {
                    if (!player.hasPermission("provanasvip")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp questroom")) {
                    if (!player.hasPermission("provanasvip")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp buzmap")) {
                    if (!player.hasPermission("buzmap.giris")) {
                        event.setCancelled(true);
                    }
                }

                if (command.equalsIgnoreCase("/warp firelands")) {
                    if (!player.hasPermission("provanasvip")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }



}
