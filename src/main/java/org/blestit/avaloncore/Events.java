package org.blestit.avaloncore;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;


import java.util.*;

import static org.blestit.avaloncore.Dragon.spawn.ejdertp;

public class Events implements Listener {


        public static HashMap<String, Long> itemswapcooldown = new HashMap<String, Long>();
        public static HashMap<String, Long> grapplinghookcooldown = new HashMap<String, Long>();
        public static HashMap<String, Long> saklambactoolcooldown = new HashMap<String, Long>();


        AvalonCore plugin;

    public Events(AvalonCore plugin) {
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

        // @EventHandler
        // public void onShoot(EntityShootBowEvent event) {
        //     var damager = event.getEntity();
//
        //     if (damager instanceof Player) {
        //         Player player = (Player) damager;
//
        //         if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§cEnvanterinizde prizmarin varsa prizmarin")) {
        //             if (player.getInventory().containsAtLeast(new ItemStack(Material.PRISMARINE_SHARD), 1)) {
//
        //                 int hasar = plugin.getConfig().getInt("prismarinebow.prizmarinlihasar");
        //                 int lokasyon1 = plugin.getConfig().getInt("prismarinebow.prizmarinlokasyon.1");
        //                 int lokasyon2 = plugin.getConfig().getInt("prismarinebow.prizmarinlokasyon.2");
        //                 int lokasyon3 = plugin.getConfig().getInt("prismarinebow.prizmarinlokasyon.3");
        //                 int distance1 = plugin.getConfig().getInt("prismarinebow.distance");
        //                 int velocity = plugin.getConfig().getInt("prismarinebow.velocity");
//
//
        //                 ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
        //                 as.setVisible(false);
        //                 Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(velocity));
//
        //                 as.setArms(true);
        //                 as.setGravity(false);
        //                 as.setItemInHand(new ItemStack(Material.PRISMARINE_SHARD));
        //                 as.setRightArmPose(new EulerAngle(Math.toRadians(lokasyon1), Math.toRadians(lokasyon2), Math.toRadians(lokasyon3)));
//
//
        //                 player.getInventory().removeItem(new ItemStack(Material.PRISMARINE_SHARD));
//
        //                 Vector vector = destination.subtract(player.getLocation()).toVector();
//
        //                 new BukkitRunnable() {
//
        //                     int distance = distance1;
        //                     int i = 0;
//
        //                     public void run() {
//
        //                         if (i >= distance) {
        //                             as.remove();
        //                             cancel();
        //                         } else {
        //                             as.teleport(as.getLocation().add(vector.normalize()));
        //                         }
        //                         i++;
//
        //                         for (Entity entity : as.getLocation().getChunk().getEntities()) {
        //                             if (as.getLocation().distanceSquared(entity.getLocation()) < 1) {
        //                                 if (entity != player) {
        //                                     LivingEntity livingentity = (LivingEntity) entity;
        //                                     livingentity.damage(hasar, player);
        //                                     if (as.getTargetBlockExact(1) != null && !as.getTargetBlockExact(1).isPassable()) {
        //                                         if (!as.isDead()) {
        //                                             as.remove();
        //                                         }
        //                                     }
        //                                 }
        //                             }
        //                         }
        //                     }
        //                 }.runTaskTimer(plugin, 1L, 1L);
//
        //                 event.setCancelled(true);
        //             }
        //         }
        //     }
        // }
//
        // @EventHandler
        // public void onEntity(PlayerInteractAtEntityEvent event) {
        //     if (event.getRightClicked() instanceof ArmorStand) {
        //         ArmorStand as = (ArmorStand) event.getRightClicked();
        //         if (as.getItemInHand().getType().equals(Material.PRISMARINE_SHARD)) {
        //             event.setCancelled(true);
        //         }
        //     }
        // }

        @EventHandler
        public void blazecancelfireball(ProjectileLaunchEvent event) {
            Entity blaze = (Entity) event.getEntity().getShooter();
            String worldisim = plugin.getConfig().getString("blazecancel.world");

            if (blaze instanceof Blaze) {
                if (Objects.requireNonNull(event.getLocation().getWorld()).getName().equals(worldisim)) {
                    event.setCancelled(true);
                }
            }
        }


        //İLLEGAL FİX

        //PUŞTLARI BANLAMA V1 (KÖYLÜ TRADE)
        @EventHandler
        public void disablevillagertrade(PlayerInteractAtEntityEvent event) {
            Player player = event.getPlayer();
            if (player.getLocation().getWorld().equals("bskyblock_world")) {
                if (!player.hasPermission("avalon.bypasstradecontrol")) {
                    if (event.getHand() == EquipmentSlot.HAND) {
                        if (event.getRightClicked().getType() == EntityType.VILLAGER || event.getRightClicked().getType() == EntityType.WANDERING_TRADER) {
                            String playername = player.getName();
                            ConsoleCommandSender cs = Bukkit.getConsoleSender();
                            System.out.println(playername + " adlı oyuncu illegal girişiminde bulunduğu için kicklendi. (Illegal trade was blocked by AVALON)");
                            Bukkit.dispatchCommand(cs, "kick " + playername + " -s Köylü ile ticaret yapmanız yasaktır.");
                        }
                    }
                }
            }
        }

        //PUŞTLARI BANLAMA V2 (MERCAN FARM)
        @EventHandler
        public void disablemercanfarm(BlockPlaceEvent event) {
            Player player = event.getPlayer();
            String playername = event.getPlayer().getName();
            Block block = event.getBlockPlaced();
            if (!player.hasPermission("avalon.bypassmercanplacecontrol")) {
                if (Objects.requireNonNull(player.getLocation().getWorld()).getName().equals("bskyblock_world")) {
                    if (block.getType() == Material.HORN_CORAL_BLOCK || block.getType() == Material.BUBBLE_CORAL_BLOCK || block.getType() == Material.FIRE_CORAL_BLOCK || block.getType() == Material.TUBE_CORAL_BLOCK || block.getType() == Material.BRAIN_CORAL_BLOCK) {

                        ConsoleCommandSender cs = Bukkit.getConsoleSender();

                        event.setCancelled(true);

                        event.setCancelled(true);
                        player.sendMessage("§6§lAVALON §b> §cBu bloğu adanıza koymanız yasaktır.");
                    }
                }
            }
        }


        @EventHandler
        public void disablequitmessage(PlayerQuitEvent event){
            event.setQuitMessage("");
        }

        //VIP Renk Sorunu Fix
        @EventHandler
        public void AFK(PlayerJoinEvent event) {

            event.setJoinMessage("");

            Player player = event.getPlayer();
            new BukkitRunnable() {

                public void run() {

                    String world1 = (player.getWorld().getName());
                    if(world1.equals("AvalonMap")) {
                        World world = Bukkit.getServer().getWorld("AvalonMap");
                        assert world != null;
                        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                        RegionManager regions = container.get(BukkitAdapter.adapt(world));
                        assert regions != null;
                        ProtectedRegion region = regions.getRegion("afkrg");
                        assert region != null;
                        if (region.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {

                            World eventdunya = Bukkit.getServer().getWorld("world");
                            Location loc1 = new Location(eventdunya, 1186, 46, -1218);
                            Location loc2 = new Location(eventdunya, 1181, 46, -1218);
                            Location loc3 = new Location(eventdunya, 1181, 46, -1213);
                            Location loc4 = new Location(eventdunya, 1186, 46, -1213);

                            player.sendTitle(ChatColor.RED + "Şu an afk'sın", ChatColor.RED + "Merkez adaya dönmek için /spawn", 1,200,1);
                            double olasilik = (Math.random()*100);
                            if(olasilik < 25 ) player.teleport(loc1);
                            else if(olasilik < 50 ) player.teleport(loc2);
                            else if(olasilik < 75 ) player.teleport(loc3);
                            else player.teleport(loc4);
                        }
                    }

                    cancel();
                }
            }.runTaskTimer(plugin, 1200, 99999);

            String playername = player.getName();

            if (!player.hasPermission("avalon.kalicirenk")) {

                ConsoleCommandSender cs = Bukkit.getConsoleSender();

                Bukkit.dispatchCommand(cs, "chatcolor " + playername + " f");

                System.out.println(playername + " adlı oyuncunun sohbet rengi sıfırlandı.");
            }

            if(!event.getPlayer().hasPlayedBefore()) {
                new BukkitRunnable() {

                    public void run() {
                        List<String> messages = plugin.getConfig().getStringList("firstjoinmessages.messages");

                        int randomnumber = (int) Math.floor(Math.random() * messages.size());
                        int oyuncusayi = (Bukkit.getOfflinePlayers().length);
                        String msg = messages.get(randomnumber);
                        String msgyeni = msg.replace("%player%", player.getName());
                        String msgyeniyeni = msgyeni.replace("&", "§");
                        String msgyeniyeniyeni = msgyeniyeni.replace("%playersize%", String.valueOf(oyuncusayi));
                        Bukkit.broadcastMessage(msgyeniyeniyeni);

                        player.sendMessage("§a|----------------------------------------------|");
                        player.sendMessage("                 §6§lAvalona Hoşgeldin!");
                        player.sendMessage("§e");
                        player.sendMessage("§eBaşlangıçta §c/rehber §eve §c/sss §eyi okuyarak bilgi edinebilirsin.");
                        player.sendMessage("§eOyunla ilgili aklına takılan tüm soruları §crehberlere §esorabilirsin.");
                        player.sendMessage("§eKurallara uygun davranışlar sergilemeniz yararınıza olacaktır §c/kurallar§e.");
                        player.sendMessage("§a|----------------------------------------------|");

                        cancel();
                    }
                }.runTaskTimer(plugin, 50, 99999);
            }
            //esas ban
            if(event.getPlayer().getName().equals("yunus") || event.getPlayer().getName().equals("Treead") || event.getPlayer().getName().equals("esadsad") || event.getPlayer().getName().equals("Yalak123456") || event.getPlayer().getName().equals("NeYaptinLa") || event.getPlayer().getName().equals("plpljlk") || event.getPlayer().getName().equals("Nogitsune")){
                player.kickPlayer("Kara Liste");
            }
        }

        //USBC MANA FIX
        //@EventHandler
        //public void preProcessCommand(PlayerCommandPreprocessEvent event) {
//
        //    Player player = event.getPlayer();
        //    String komut = event.getMessage();
//
        //    if(komut.equalsIgnoreCase("/sv avalon")){
        //        event.setCancelled(true);
        //    }
//
        //    Iterator<String> özellikler = plugin.getConfig().getKeys(true).stream().filter(el -> el.matches("manafix\\.swords\\.[^.]+")).iterator();
        //    for (Iterator<String> it = özellikler; it.hasNext(); ) {
        //        String key = it.next();
        //        if (komut.toLowerCase().contains(plugin.getConfig().getString(key + ".commandname").toLowerCase())) {
        //            if ((player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null) && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(plugin.getConfig().getString(key + ".itemlore"))) {
        //                event.setCancelled(false);
        //            } else event.setCancelled(true);
        //            System.out.println(player + "adlı oyuncunun komutu engellendi. Eğer bunun olmaması gerekiyorsa lütfen Avalon eklentisinin configine eşya lore unu ve komudu yazınız.");
        //            Bukkit.broadcast("§c" + player + "§6adlı oyuncunun §c/" + komut + "§6 komutu engellendi.  §eEğer bunun olmaması gerekiyorsa lütfen Avalon eklentisinin configine eşya lore unu ve komudu yazınız.", "avalon.seewarn");
        //        }
        //    }
        //}


        //Yetki olmadan warp komutlarını kullanmayı kapatma
        @EventHandler
        public void oncommand(PlayerCommandPreprocessEvent event){
            Player player = event.getPlayer();

            String command = event.getMessage();

            if (command.equalsIgnoreCase("/ejderfix")) {
                if(player.hasPermission("avalon.ejdertp")) {
                    ejdertp();
                }
            }
            if(command.equalsIgnoreCase("/kit")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Şu komudu kullanmayı dene: /kits");
            }

            if(command.contains("warp")){
                if(!player.isOp()) {
                    World world = Bukkit.getServer().getWorld("opskymap");
                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                    assert world != null;
                    RegionManager regions = container.get(BukkitAdapter.adapt(world));
                    assert regions != null;




                    //çiftlik adaları
                    if (command.equalsIgnoreCase("/warp ciftlikqwezxc") || command.equalsIgnoreCase("/warp mantarcolucikisqwezxc")) {
                        ProtectedRegion ciftlik = regions.getRegion("ciftlikcikis");
                        ProtectedRegion mantarcolucikis = regions.getRegion("farm2portal");
                        if (!player.hasPermission("avalon.portal.ciftlik") && !(Objects.requireNonNull(mantarcolucikis).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) || Objects.requireNonNull(ciftlik).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())))){
                            event.setCancelled(true);
                        }
                    }
                    if (command.equalsIgnoreCase("/warp mantarcoluqwezxc")) {
                        ProtectedRegion region = regions.getRegion("farm2portalgecis");
                        if (!player.hasPermission("avalon.portal.mantarcolu") && (!Objects.requireNonNull(region).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())))) {
                            event.setCancelled(true);
                        }

                    }

                    //maden adaları
                    if (command.equalsIgnoreCase("/warp maden1qwezxc") || command.equalsIgnoreCase("/warp maden2cikisqwezxc")) {
                        ProtectedRegion maden1 = regions.getRegion("maden1gecisportal");
                        ProtectedRegion maden2cikis = regions.getRegion("maden2portal");
                        if (!player.hasPermission("avalon.portal.altinmaden") && (!Objects.requireNonNull(maden1).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) || !Objects.requireNonNull(maden2cikis).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))))   event.setCancelled(true);

                    }
                    if (command.equalsIgnoreCase("/warp maden2qwezxc")) {
                        ProtectedRegion region = regions.getRegion("maden2gecis");
                        if (!player.hasPermission("avalon.portal.derinmagara") && (!Objects.requireNonNull(region).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))))   event.setCancelled(true);

                    }

                    //orman adası
                    if (command.equalsIgnoreCase("/warp ormanqwezxc")) {
                        ProtectedRegion region = regions.getRegion("ormangiris");
                        if (!player.hasPermission("avalon.portal.orman") && (!Objects.requireNonNull(region).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))))  event.setCancelled(true);

                    }

                    //combat adaları
                    if (command.equalsIgnoreCase("/warp orumcekyuvasiqwezxc") || command.equalsIgnoreCase("/warp nethercikisqwezxc") || command.equalsIgnoreCase("/warp endcikisqwezxc")) {
                        ProtectedRegion spawnportal = regions.getRegion("zombi");
                        ProtectedRegion endcikis = regions.getRegion("endportal");
                        ProtectedRegion nethercikis = regions.getRegion("netherportal");
                        if (!player.hasPermission("avalon.portal.orumcekyuvasi") && (!Objects.requireNonNull(spawnportal).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) || !Objects.requireNonNull(endcikis).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) || !Objects.requireNonNull(nethercikis).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))))     event.setCancelled(true);

                    }
                    if (command.equalsIgnoreCase("/warp netherqwezxc")) {
                        ProtectedRegion region = regions.getRegion("nethercikis");
                        if (!player.hasPermission("avalon.portal.nether") && (!Objects.requireNonNull(region).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))))   event.setCancelled(true);

                    }
                    if (command.equalsIgnoreCase("/warp endqwezxc")) {
                        ProtectedRegion region = regions.getRegion("endgiris");
                        if (!player.hasPermission("avalon.portal.end") && (!Objects.requireNonNull(region).contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())))) event.setCancelled(true);

                    }
/*
                //özel odalar (sonradan kullanılabilir)
                if (command.equalsIgnoreCase("/warp dragonroom")) {
                    if(!player.hasPermission("avalon.portal.dragonroom")) event.setCancelled(true);
                }
                if (command.equalsIgnoreCase("/warp magmaboss")) {
                    if(!player.hasPermission("avalon.portal.magmaboss")) event.setCancelled(true);
                }
                if (command.equalsIgnoreCase("/warp questroom")) {
                    if(!player.hasPermission("avalon.portal.questroom")) event.setCancelled(true);
                }
*/
                    //derin mağara katları
                    if(command.equalsIgnoreCase("/warp altinmadenqwezxc") || command.equalsIgnoreCase("/warp lapismadenqwezxc") || command.equals("/warp kiziltasmadenqwezxc") || command.equals("/warp zumrutmadenqwezxc") || command.equals("/warp elmasmadenqwezxc") || command.equals("/warp obsidyenmadenqwezxc")){
                        ProtectedRegion region = regions.getRegion("maden2");
                        assert region != null;
                        if (!region.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) event.setCancelled(true);
                    }

                    //buz map
                    if (command.equalsIgnoreCase("/warp buzmap6545643524")) {
                        ProtectedRegion region = regions.getRegion("buzportal");
                        assert region != null;
                        if (!region.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) event.setCancelled(true);

                    }

                }
            }

        }

        //2 el ile güç bugunu kaldırma
        //@EventHandler
        //public void onbugdamage(EntityDamageByEntityEvent event) {
        //    if (event.getDamager() instanceof Player){
        //        Player player = (Player) event.getDamager();
        //        if(player.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD || player.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD || player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD || player.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD || player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD || player.getInventory().getItemInMainHand().getType() == Material.BOW || player.getInventory().getItemInMainHand().getType() == Material.GHAST_TEAR || player.getInventory().getItemInMainHand().getType() == Material.EMERALD) {
        //            if (player.getInventory().getItemInOffHand().getType() == Material.WOODEN_SWORD || player.getInventory().getItemInOffHand().getType() == Material.STONE_SWORD || player.getInventory().getItemInOffHand().getType() == Material.GOLDEN_SWORD || player.getInventory().getItemInOffHand().getType() == Material.IRON_SWORD || player.getInventory().getItemInOffHand().getType() == Material.DIAMOND_SWORD || player.getInventory().getItemInOffHand().getType() == Material.BOW || player.getInventory().getItemInOffHand().getType() == Material.GHAST_TEAR || player.getInventory().getItemInOffHand().getType() == Material.EMERALD) {
        //                event.setCancelled(true);
        //            player.sendMessage(ChatColor.RED + "İki elinizi aynı anda kullanamazsınız.");
        //        }
        //        }
//
        //    }
        //    if (event.getDamager() instanceof Arrow){
        //        Projectile proj = (Projectile) event.getDamager();
        //        Entity player1 = (Entity) proj.getShooter();
        //        if(player1 instanceof Player){
        //            Player player = (Player) proj.getShooter();
//
//
//
        //        assert player != null;
        //        if(player.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD || player.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD || player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD || player.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD || player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD || player.getInventory().getItemInMainHand().getType() == Material.BOW || player.getInventory().getItemInMainHand().getType() == Material.GHAST_TEAR || player.getInventory().getItemInMainHand().getType() == Material.EMERALD) {
        //            if (player.getInventory().getItemInOffHand().getType() == Material.WOODEN_SWORD || player.getInventory().getItemInOffHand().getType() == Material.STONE_SWORD || player.getInventory().getItemInOffHand().getType() == Material.GOLDEN_SWORD || player.getInventory().getItemInOffHand().getType() == Material.IRON_SWORD || player.getInventory().getItemInOffHand().getType() == Material.DIAMOND_SWORD || player.getInventory().getItemInOffHand().getType() == Material.BOW || player.getInventory().getItemInOffHand().getType() == Material.GHAST_TEAR || player.getInventory().getItemInOffHand().getType() == Material.EMERALD) {
        //                event.setCancelled(true);
        //                player.sendMessage(ChatColor.RED + "İki elinizi aynı anda kullanamazsınız.");
        //            }
        //            }
        //        }
        //    }
        //}


        //Oyuncu pvp fix
        //@EventHandler
        //public void arena(EntityDamageByEntityEvent event) {
        //    var entity = event.getEntity();
        //    if (entity instanceof Player) {
        //        Player player = (Player) entity;
        //        String world1 = (event.getEntity().getWorld().getName());
        //        if(world1.equals("opskymap")){
        //        World world = Bukkit.getServer().getWorld("opskymap");
        //        assert world != null;
        //        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        //        RegionManager regions = container.get(BukkitAdapter.adapt(world));
        //        assert regions != null;
        //        ProtectedRegion region = regions.getRegion("arena");
        //        assert region != null;
        //        if (region.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
        //            event.setDamage(event.getDamage() / 2000);
        //        }
        //        }
        //    }
        //}

        @EventHandler
        public void zindanfix1(PlayerFishEvent event){
            Player player = event.getPlayer();
            String worldad = event.getPlayer().getWorld().getName();
            if(worldad.equals("zindan") && (!player.hasPermission("giris.zindan"))){
                ConsoleCommandSender cs = Bukkit.getConsoleSender();
                Bukkit.dispatchCommand(cs, "spawn " + player.getName());
            }
        }


        @EventHandler
        public void zindanfix2(EntityDamageByEntityEvent event){
            if(event.getDamager() instanceof Player){
                Player player = (Player) event.getDamager();
                String worldad = player.getWorld().getName();
                if(worldad.equals("zindan") && (!player.hasPermission("giris.zindan"))){
                    ConsoleCommandSender cs = Bukkit.getConsoleSender();
                    Bukkit.dispatchCommand(cs, "spawn " + player.getName());
                }
            }
        }


        //menu open
        @EventHandler
        public void openmenuwithf(PlayerSwapHandItemsEvent event) {
            int cooldownTime = 2;
            Player player = event.getPlayer();

            if (!player.hasPermission("avalon.fmenu")) {
                if (!(player.getInventory().getItemInMainHand().getType() == Material.SHIELD || player.getInventory().getItemInOffHand().getType() == Material.SHIELD)) {

                    if (itemswapcooldown.containsKey(player.getName())) {
                        int secondsLeft = (int) (((itemswapcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                        if (secondsLeft > 0) {
                            // Still cooling down
                            player.sendMessage(ChatColor.RED + "Biraz yavaşla! Şu kadar süre sonra tekrar dene: " + secondsLeft);

                            return;
                        }
                    }


                    event.setCancelled(true);
                    ConsoleCommandSender cs = Bukkit.getConsoleSender();
                    Bukkit.dispatchCommand(cs, "dm execute " + player.getName() + " [openguimenu] menu");
                    Location loc = player.getLocation();
                    player.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 20, 0);
                    itemswapcooldown.put(player.getName(), System.currentTimeMillis());
                }
            }
            else if(player.isOp()){
                if (!(player.getInventory().getItemInMainHand().getType() == Material.SHIELD || player.getInventory().getItemInOffHand().getType() == Material.SHIELD)) {

                    if (itemswapcooldown.containsKey(player.getName())) {
                        int secondsLeft = (int) (((itemswapcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                        if (secondsLeft > 0) {
                            // Still cooling down
                            player.sendMessage(ChatColor.RED + "Biraz yavaşla! Şu kadar süre sonra tekrar dene: " + secondsLeft);

                            return;
                        }
                    }


                    event.setCancelled(true);
                    ConsoleCommandSender cs = Bukkit.getConsoleSender();
                    Bukkit.dispatchCommand(cs, "dm execute " + player.getName() + " [openguimenu] menu");
                    Location loc = player.getLocation();
                    player.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 20, 0);
                    itemswapcooldown.put(player.getName(), System.currentTimeMillis());
                }
            }
        }
        @EventHandler
        public void selfhitbugbow(EntityDamageByEntityEvent event){
            Entity vuran = event.getDamager();
            Entity vurulan = event.getEntity();

            if(event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
                return;
            }

            Projectile proj = (Projectile) event.getDamager();

            if(!(proj.getShooter() instanceof Player)) {
                return;
            }

            if(vuran instanceof Arrow){
                if(vurulan instanceof Player){
                    Player vuranoyuncu = (Player) proj.getShooter();
                    Player vurulanoyuncu = (Player) event.getEntity();

                    if(vuranoyuncu.getInventory().getItemInMainHand().getType() == Material.BOW){
                        if(vuranoyuncu.getName().equals(vurulanoyuncu.getName())){
                            event.setCancelled(true);
                            event.getDamager().remove();
                        }
                    }
                }
            }
        }
        @EventHandler(priority = EventPriority.HIGHEST)
        public void kdastat(EntityDamageByEntityEvent event){
            Entity vuran = event.getDamager();
            Entity vurulan = event.getEntity();
            ConsoleCommandSender cs = Bukkit.getConsoleSender();

            if(event.isCancelled()){
                return;
            }

            if(vuran instanceof Player){
                if(vurulan instanceof Player){
                    Player vurulanoyuncu = (Player) event.getEntity();
                    Player vuranoyuncu = (Player) event.getDamager();
                    if(vurulanoyuncu.getHealth() - event.getFinalDamage() <= 0){
                        Bukkit.dispatchCommand(cs, "bq point " + vuranoyuncu.getName() + " add default.arenakill 1");
                        Bukkit.dispatchCommand(cs, "bq point " + vurulanoyuncu.getName() + " add default.arenadeath 1");
                    }
                }
            }
            if(vuran instanceof Arrow){
                if(vurulan instanceof Player){
                    Player vurulanoyuncu = (Player) event.getEntity();
                    Projectile proj = (Projectile) event.getDamager();
                    Entity projshooter = (Entity) proj.getShooter();
                    if(projshooter instanceof Player){
                        Player vuranoyuncuokcu = (Player) proj.getShooter();
                        if(vurulanoyuncu.getHealth() - event.getFinalDamage() <= 0){
                            Bukkit.dispatchCommand(cs, "bq point " + vuranoyuncuokcu.getName() + " add default.arenakill 1");
                            Bukkit.dispatchCommand(cs, "bq point " + vurulanoyuncu.getName() + " add default.arenadeath 1");
                        }
                    }
                }
            }
        }
        @EventHandler
        public void phantomnerf(EntitySpawnEvent event){
            World world = event.getEntity().getWorld();
            int chance = plugin.getConfig().getInt("nerfphantom.nerfrate");
            if(world.equals("bskyblock_world")){
                if(event.getEntity() instanceof Phantom){
                    if(Math.random() * 100 <= chance) {

                        event.setCancelled(true);

                    }
                }
            }
        }


        @EventHandler
        public void damagefix(EntityDamageEvent event){
            Entity entity = event.getEntity();
            if(entity instanceof Player){
                if(event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.DamageCause.DROWNING || event.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH){
                    Damageable player = (Player) event.getEntity();

                    if(player.getMaxHealth() >= 600) player.damage(50);
                    if(player.getMaxHealth() >= 450) player.damage(40);
                    if(player.getMaxHealth() >= 300) player.damage(30);
                    if(player.getMaxHealth() >= 150) player.damage(20);
                }
            }
        }
        @EventHandler
        public void kukkipotion(PlayerDeathEvent e){
            Player player = e.getEntity().getPlayer();
            assert player != null;

            if(player.hasPermission("Avalon.kukki")){

                Collection<PotionEffect> effects = player.getPlayer().getActivePotionEffects();

                new BukkitRunnable() {

                    public void run() {
                        if(!player.isDead()) {
                            player.addPotionEffects(effects);
                            cancel();
                        }
                    }
                }.runTaskTimer(plugin, 10, 10);
            }
        }


    public static boolean hasPermission(Player player, String permission)
    {
        Permission p = new Permission(permission, PermissionDefault.FALSE);
        return player.hasPermission(p);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent e) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (e.getMessage().contains(player.getName())) {
                ;
                String lastColor = ChatColor.getLastColors(e.getMessage());
                e.setMessage(e.getMessage().replaceAll(
                        player.getName(), ChatColor.GREEN + player.getName() + (lastColor.isEmpty() ? ChatColor.RESET : lastColor)
                ));
                if (!e.isCancelled()) {
                    Sound pingsound1 = getPingSound();

                    if (pingsound1 == null) {
                        return;
                    }
                    if(!player.isOp()) {
                        if (hasPermission(player, "avalon.ping")) {
                            player.playSound(player.getEyeLocation(), pingsound1, 1, 1);
                        }
                    }
                }
            }
        });
        //helper ping
        if (e.getMessage().contains("Rehber") || e.getMessage().contains("rehber")) {
            if (!e.isCancelled()) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    Sound pingsound1 = getPingSound();


                    if (hasPermission(player, "avalon.rehberping")) {
                        if (!hasPermission(player, "avalon.op")) {
                            player.playSound(player.getEyeLocation(), pingsound1, 1, 1);
                        }
                    }
                });
            }
            //chat click msg
        }
    }

    private Sound getPingSound() {
        String pingsound = plugin.getConfig().getString("PlayerPing.Sound");

        try {
            return Sound.valueOf(pingsound);
        } catch (IllegalArgumentException e) {
            try {
                return Sound.valueOf(pingsound);
            } catch (IllegalArgumentException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }

    @EventHandler
    public void SaklambacTool(PlayerInteractEntityEvent event) {
        int cooldownTime = 4;
        String prefix = plugin.getConfig().getString("saklambactool.text.prefix");
        String ebeledin = plugin.getConfig().getString("saklambactool.text.ebeledin");
        String ebelendin = plugin.getConfig().getString("saklambactool.text.ebelendin");
        String ebeyiebeleyemezsin = plugin.getConfig().getString("saklambactool.text.ebeyiebeleyemezsin");
        String particle = plugin.getConfig().getString("saklambactool.effect.particle");
        String sound = plugin.getConfig().getString("saklambactool.effect.sound");
        String needlore = plugin.getConfig().getString("saklambactool.item.need_lore");
        String ebeefekt = plugin.getConfig().getString("saklambactool.item.ebe_effect");
        String cooldowntext = plugin.getConfig().getString("saklambactool.text.cooldown_text");
        int particlepower = plugin.getConfig().getInt("saklambactool.effect.particlepower");
        String command = plugin.getConfig().getString("saklambactool.item.command");
        String msgcolor = plugin.getConfig().getString("saklambactool.text.msg_color");
        String broadcastmsg = plugin.getConfig().getString("saklambactool.text.broadcast_text");
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        Location location = entity.getLocation();
        World world = entity.getWorld();
        String playername = event.getPlayer().getName();
        String entityname = event.getRightClicked().getName();
        if(event.getHand() == EquipmentSlot.HAND){
            if (event.getRightClicked() instanceof Player) {
                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                        && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(needlore)) {

                    if (saklambactoolcooldown.containsKey(player.getName())) {
                        int secondsLeft = (int) (((saklambactoolcooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000));
                        if (secondsLeft > 0) {
                            // Still cooling down
                            player.sendMessage(ChatColor.RED + "Biraz yavaşla! Şu kadar süre sonra tekrar dene: " + secondsLeft);

                            return;
                        }
                    }

                        assert ebeefekt != null;
                        if (((Player) entity).hasPotionEffect(Objects.requireNonNull(PotionEffectType.getByName(ebeefekt)))) {
                            player.sendMessage(prefix + ebeyiebeleyemezsin);
                        } else {
                            world.spawnParticle(Particle.valueOf(particle), location, particlepower, 0, 0, 0);
                            world.playSound(location, Sound.valueOf(sound), 3, 1);
                            player.sendMessage(prefix + ebeledin);
                            entity.sendMessage(prefix + ebelendin);
                            ((Player) entity).performCommand(command);
                            Bukkit.broadcastMessage(prefix + msgcolor + playername + ", " + entityname + " " + broadcastmsg);

                            saklambactoolcooldown.put(player.getName(), System.currentTimeMillis());
                        }

                }
            }
        }
    }

    @EventHandler
    public void MythicMobeggban(PlayerInteractEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.PIG_SPAWN_EGG)){
            if(!event.getPlayer().getWorld().getName().equalsIgnoreCase("bskyblock_world")){
                event.setCancelled(true);
                Player player = event.getPlayer();
                if(Objects.equals(event.getHand(), EquipmentSlot.HAND)) player.sendMessage("§7[§6Avalon§7] " + ChatColor.RED + "Bu yumurtayı oyuncu adası dışında başka bir yerde kullanamazsınız!");
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void MythicMobdespawn(CreatureSpawnEvent event){

        if(!event.getEntity().getWorld().getName().equalsIgnoreCase("bskyblock_world")) {
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    String mobname = event.getEntity().getCustomName();
                    String mobad = event.getEntity().getName();
                    List<String> fishmobs = plugin.getConfig().getStringList("sumobslist");

                    for (String fishmob : fishmobs) {
                        assert mobname != null;
                        if (mobname.contains(fishmob)) {
                            Damageable mob = event.getEntity();


                            Location loc = new Location(mob.getWorld(), mob.getLocation().getX(), -1000, mob.getLocation().getZ());
                            //If mob dead with no teleport, mob dropping rewards
                            mob.teleport(loc);

                            //mob.setHealth(0);

                            event.setCancelled(true);
                        }
                    }
                }
            }, 5);

        }
    }
}
