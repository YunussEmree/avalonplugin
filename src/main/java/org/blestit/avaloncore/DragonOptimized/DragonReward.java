package org.blestit.avaloncore.DragonOptimized;

import org.blestit.avaloncore.AvalonCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.blestit.avaloncore.DragonOptimized.Dragon.*;
import static org.blestit.avaloncore.DragonOptimized.DragonAltar.*;
import static org.blestit.avaloncore.DragonOptimized.DragonManager.teleportDragon;
import static org.blestit.avaloncore.DragonOptimized.DragonSkillManager.thunder;

public class DragonReward implements Listener {

    public static Player dragonDamager;
    public static BossBar dragonBossBar = Bukkit.createBossBar("§5§lEjderha", BarColor.PINK, BarStyle.SOLID, BarFlag.DARKEN_SKY);

    public static double dragonhp;
    public String lastHitter;
    static Plugin plugin;

    public static HashMap<String, Double> hasarMapi = new HashMap<>();
    public static HashMap<String, Integer> weightMapi = new HashMap<String, Integer>();


    public DragonReward(AvalonCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDragonDamaged(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof EnderDragon) {

            //yayla hasar
            if (event.getDamager() instanceof Arrow) {
                Projectile proj = (Projectile) event.getDamager();
                dragonDamager = (Player) proj.getShooter();

                if (dragon != null && !dragon.isDead()) {


                    dragonBossBar.addPlayer(dragonDamager);
                    dragonBossBar.setTitle(Displayname + " - " + Math.round(((EnderDragon) event.getEntity()).getHealth() - event.getFinalDamage()));
                    dragonBossBar.setProgress(Math.max(((EnderDragon) event.getEntity()).getHealth() / Health,0));

                    dragonhp = dragon.getHealth();

                    lastHitter = dragonDamager.getName();

                    if (hasarMapi.get(dragonDamager.getName()) == null) {
                        hasarMapi.put(dragonDamager.getName(), event.getFinalDamage());
                    } else {
                        hasarMapi.put(dragonDamager.getName(), hasarMapi.get(dragonDamager.getName()) + event.getFinalDamage());
                    }

                }
            }

            //kılıçla hasar
            if (event.getDamager() instanceof Player) {
                dragonDamager = (Player) event.getDamager();


                if (dragon != null && !dragon.isDead()) {

                    dragonBossBar.addPlayer(dragonDamager);
                    dragonBossBar.setTitle(Displayname + " - " + Math.round(((EnderDragon) event.getEntity()).getHealth() - event.getFinalDamage()));
                    dragonBossBar.setProgress(Math.max(((EnderDragon) event.getEntity()).getHealth() / Health,0));

                    dragonhp = dragon.getHealth();

                    if (event.getDamager() instanceof Player) {
                        Player p = (Player) event.getDamager();

                        lastHitter = p.getName();

                        if (hasarMapi.get(p.getName()) == null) {
                            hasarMapi.put(p.getName(), event.getFinalDamage());
                        } else {
                            hasarMapi.put(p.getName(), hasarMapi.get(p.getName()) + event.getFinalDamage());
                        }

                    }
                }
            }
        }
    }

    public static void createstatictik() {

        Set<Map.Entry<String, Double>> set = hasarMapi.entrySet();

        int i = 1;
        String sıralamaMesajı = "";
        Iterator<Map.Entry<String, Double>> sıralıArray = (set.stream().sorted((newEl, oldEl) -> (int) Math.round((oldEl.getValue() - newEl.getValue()))).iterator());

        for (Iterator<Map.Entry<String, Double>> it = sıralıArray; it.hasNext(); ) {
            Map.Entry<String, Double> element = it.next();
            sıralamaMesajı += ("          §6§l" + i + ". §c§l" + element.getKey() + ": " + Math.round(element.getValue()) + "\n");
            i++;
            if (i == 4) break;
        }
        i = 0;

        Bukkit.broadcastMessage("§a▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n§5  " + "        " + dragon.getCustomName() + " mağlup edildi." + "\n \n  " + "            §e§lHASAR SIRALAMASI\n§c§l \n" + sıralamaMesajı + "\n ");
        for (String hasarVeren : hasarMapi.keySet()) {
            Player p = Bukkit.getPlayer(hasarVeren);

        }

        Bukkit.broadcastMessage("§5 \n§a§a▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");

    }

    @EventHandler
    public void onkill(EntityDeathEvent event) {
        if (event.getEntity() instanceof EnderDragon && dragon != null && dragon.isDead()) {

            dragonhp = 0;

            dragonBossBar.removeAll();

            removeeyes();

            createstatictik();

            List<Map.Entry<String, Double>> hasarMapiEntries = hasarMapi.entrySet().stream().collect(Collectors.toList());
            hasarMapiEntries.sort((newEnt, oldEnt) -> {
                return Double.compare(oldEnt.getValue(), newEnt.getValue());
            });
            int sira = 1;
            for (Map.Entry<String, Double> el : hasarMapiEntries) {

                String isim = el.getKey();
                Player player = Bukkit.getPlayer(isim);

                if (player != null && player.isOnline()) {
                    if (player.hasPermission("avalon.dragon.extraweight.50")) {
                        if (!weightMapi.containsKey(isim)) {
                            weightMapi.put(isim, 50);
                        } else {
                            weightMapi.put(isim, weightMapi.get(isim) + 50);
                        }
                    }
                }


                if (sira == 1) {


                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 300);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 300);
                    }

                } else if (sira == 2) {


                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 250);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 250);
                    }

                } else if (sira == 3) {


                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 200);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 200);
                    }

                } else if (sira == 4) {


                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 125);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 125);
                    }


                } else if (sira == 5) {


                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 110);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 110);
                    }

                } else if (sira == 6 || sira == 7 || sira == 8) {


                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 100);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 100);
                    }

                } else if (sira == 9 || sira == 10) {


                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 90);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 90);
                    }

                } else if (sira >= 10) {

                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 80);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 80);
                    }

                }
                sira++;
            }

            System.out.println("§c§l[AVALON] Weightmap log: " + weightMapi);
            System.out.println("§c§l[AVALON] Damagemap log: " + hasarMapi);
            System.out.println("§c§l[AVALON] Gözmap log: " + gozmapi);
            AtomicInteger siralama = new AtomicInteger(1);
            hasarMapiEntries.forEach((entry) -> {
                String key = entry.getKey();
                double db = entry.getValue();

                Player player = Bukkit.getPlayer(key);

                CommandSender sender = Bukkit.getConsoleSender();

                int gozsayi = 0;
                if (gozmapi.get(key) != null) {
                    gozsayi = (gozmapi.get(key));
                }

                //koruyucudragon
                if (dragon.getCustomName().equals("§5§lKoruyucu Ejderha")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejderkoruyucu 1");
                    }
                    if (siralama.get() == 2)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU " + key + " 6");
                    if (siralama.get() == 3)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU "+key+" 3");


                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset boots");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }


                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }

                //yaşlıdragon
                if (dragon.getCustomName().equals("§5§lYaşlı Ejderha")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejderyasli 1");
                    }
                    if (siralama.get() == 2) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI " + key + " 6");
                    if (siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI "+key+" 3");

                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset boots ");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }

                //gençdragon
                if (dragon.getCustomName().equals("§5§lGenç Ejderha")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejdergenc 1");
                    }
                    if (siralama.get() == 2) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC " + key + " 6");
                    if (siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC "+key+" 3");

                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gencset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gencset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gencset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gencset boots ");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }

                //bilgedragon
                if (dragon.getCustomName().equals("§5§lBilge Ejderha")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejderbilge 1");
                    }
                    if (siralama.get() == 2) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE " + key + " 6");
                    if (siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE "+key+" 3");

                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset boots");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }


                //güvenilmezdragon
                if (dragon.getCustomName().equals("§5§lGüvenilmez Ejderha")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejderguvenilmez 1");
                    }
                    if (siralama.get() == 2)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ " + key + " 6");
                    if (siralama.get() == 3)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ "+key+" 3");

                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset boots ");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }

                //güçlüdragon
                if (dragon.getCustomName().equals("§5§lGüçlü Ejderha")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejderguclu 1");
                    }
                    if (siralama.get() == 2) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU " + key + " 6");
                    if (siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU "+key+" 3");

                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset boots  1");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }

                //kutsaldragon
                if (dragon.getCustomName().equals("§6§lKutsal Ejderha")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejderkutsal 1");
                    }
                    if (siralama.get() == 2) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL " + key + " 6");
                    if (siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 3");

                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset boots");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 50) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &5Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonepic");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }


                //kilgharrahdragon
                if (dragon.getCustomName().equals("§6§lKilgharrah Ejderhası")) {

                    if (siralama.get() == 1) {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH " + key + " 7");
                        Bukkit.dispatchCommand(sender, "q point " + key + " add Gorevler.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add Gorevler.ejderkilgharrah 1");
                    }
                    if (siralama.get() == 2)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH " + key + " 6");
                    if (siralama.get() == 3)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH " + key + " 5");
                    if (siralama.get() == 4 || siralama.get() == 5)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH " + key + " 4");
                    if (siralama.get() == 6 || siralama.get() == 7)
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH " + key + " 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 3");

                    if (weightMapi.get(key) >= 452) {
                        if ((Math.random() * 100) < 30) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 452);
                        }
                    }

                    if (weightMapi.get(key) >= 451) {
                        if ((Math.random() * 100) < gozsayi * 2) {
                            Bukkit.dispatchCommand(sender, "reforges give " + key + " dragonclaw_1 1");
                            weightMapi.put(key, weightMapi.get(key) - 451);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset boots ");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 50) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderhası&b'nı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderhası&b'nı keserek &5Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonepic");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 100) {
                        int endbuygozsayi = (int) Math.floor(weightMapi.get(key) / 100);
                        Bukkit.dispatchCommand(sender, "mi give TOOL ENDER_PEARL " + key + " " + endbuygozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endbuygozsayi * 100));
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }
                siralama.getAndIncrement();
                // Selam
            });

            hasarMapi.clear();
            gozmapi.clear();
            weightMapi.clear();

        }

    }


}
