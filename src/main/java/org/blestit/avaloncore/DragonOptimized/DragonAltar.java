package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.blestit.avaloncore.DragonOptimized.Dragon.dragon;
import static org.blestit.avaloncore.DragonOptimized.DragonReward.weightMapi;

public class DragonAltar implements Listener {



    Plugin plugin;
    public static ArrayList<Location> altars;
    public DragonAltar(Plugin plugin) {
        this.plugin = plugin;
    }


    public static HashMap<String, Integer> gozmapi = new HashMap<String, Integer>();



    @EventHandler
    public void onplaceeye(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().name().equals(Material.END_PORTAL_FRAME.name())) {
            if (event.getHand() == EquipmentSlot.HAND) {
                World world = event.getPlayer().getWorld();
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§5Ejderha Çağırma Gözü") && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§7Bunu Ejderhanın Yuvasındaki Ender Altarı'nda") && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§7Ender Ejderhalarını çağırmak için kullanın!")) {
                    Double locx = event.getClickedBlock().getLocation().getBlock().getLocation().getX();
                    Double locy = event.getClickedBlock().getLocation().getBlock().getLocation().getY();
                    Double locz = event.getClickedBlock().getLocation().getBlock().getLocation().getZ();
                    ArrayList<String> altarlar = new ArrayList<>(plugin.getConfig().getStringList("dragon.altarlar"));
                    ArrayList<Location> altarBloklar = new ArrayList<>();
                    for (String altar : altarlar) {
                        String[] kordinatlar = altar.split(" ");
                        Location lokasyon = new Location(world, Double.parseDouble(kordinatlar[0]), Double.parseDouble(kordinatlar[1]), Double.parseDouble(kordinatlar[2]));
                        altarBloklar.add(lokasyon);
                    }
                    if (altarBloklar.stream().anyMatch(altarBlok -> Double.compare(altarBlok.getX(), locx) == 0 && Double.compare(altarBlok.getY(), locy) == 0 && Double.compare(altarBlok.getZ(), locz) == 0 && !(((EndPortalFrame) altarBlok.getBlock().getBlockData()).hasEye()))) {
                        Block enderPortalBlock = new Location(world, locx, locy, locz).getBlock();
                        EndPortalFrame enderPortal = (EndPortalFrame) enderPortalBlock.getBlockData();
                        enderPortal.setEye(true);
                        enderPortalBlock.setBlockData(enderPortal);
                        Player player = event.getPlayer();
                        String playername = event.getPlayer().getName();
                        int tamOlanlar = altarBloklar.stream().filter(altarBlok -> ((EndPortalFrame) altarBlok.getBlock().getBlockData()).hasEye()).toArray().length;
                        Bukkit.broadcastMessage("§d" + playername + " §5bir ejderha çağırma gözü yerleştirdi." + "§d (" + tamOlanlar + "/8)");
                        ConsoleCommandSender cs = Bukkit.getConsoleSender();
                        dispatchCommand("q point "+playername+" add Gorevler.ecgkoyma 1");
                        event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                        world.playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDER_DRAGON, 5, 5);
                        altars = altarBloklar;
                        Set<Map.Entry<String, Integer>> set = gozmapi.entrySet();
                        if(!gozmapi.containsKey(event.getPlayer().getName())){
                            gozmapi.put(event.getPlayer().getName(), 1);

                        }else{
                            gozmapi.put(event.getPlayer().getName(), gozmapi.get(event.getPlayer().getName()) + 1);

                        }
                        Set<Map.Entry<String, Integer>> set1 = weightMapi.entrySet();
                        if(!weightMapi.containsKey(event.getPlayer().getName())){
                            weightMapi.put(event.getPlayer().getName(), 100);
                        } else {
                            weightMapi.put(event.getPlayer().getName(), weightMapi.get(event.getPlayer().getName()) + 100);
                        }



                        if (altarBloklar.stream().allMatch(altarBlok -> ((EndPortalFrame) altarBlok.getBlock().getBlockData()).hasEye())) {
                            Double dragonlocx = plugin.getConfig().getDouble("dragon.dragonloc.x");
                            Double dragonlocy = plugin.getConfig().getDouble("dragon.dragonloc.y");
                            Double dragonlocz = plugin.getConfig().getDouble("dragon.dragonloc.z");


                            Location dragonloc = new Location(world, dragonlocx, dragonlocy, dragonlocz);


                            //EJDERHA ÇAĞIRMA YAZILARI
                            Bukkit.broadcastMessage("§5Bir Ejderha Ortaya Çıkıyor...");
                            System.out.println(event.getPlayer().getName() + " adlı oyuncu bir ejderha çağırdı.");

                            new BukkitRunnable() {
                                int i = 5;
                                public void run() {
                                    if (i < 0) cancel();
                                    if (i == 0) {
                                        DragonTypeSelection dragonTypeSelection = new DragonTypeSelection(plugin);
                                        String type = dragonTypeSelection.selectDragonType();

                                        int hp = plugin.getConfig().getInt("dragons." + type + ".Health");
                                        int damage = plugin.getConfig().getInt("dragons." + type + ".Damage");
                                        int speed = plugin.getConfig().getInt("dragons." + type + ".Speed");
                                        int armor = plugin.getConfig().getInt("dragons." + type + ".Armor");
                                        int skillDamage = plugin.getConfig().getInt("dragons." + type + ".SkillDamage");
                                        int skillCooldown = plugin.getConfig().getInt("dragons." + type + ".SkillCooldown");
                                        String displayname = plugin.getConfig().getString("dragons." + type + ".Display_name");
                                        Location loc = new Location(world, dragonlocx, dragonlocy, dragonlocz);

                                        System.out.println("Ejderha ismi: " + displayname);

                                        dragon = new Dragon(hp,damage, speed, armor, skillDamage, skillCooldown, loc, displayname);


                                        Bukkit.broadcastMessage("§dEjderha türü: " + displayname);
                                    } else if (i != -1) {
                                        Bukkit.broadcastMessage("§5Ejderha Doğuyor... §d" + i);
                                        world.playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDER_DRAGON, 50, 50);
                                    }
                                    i--;
                                }
                            }.runTaskTimer(plugin, 10, 20);
                        }
                    }
                }
            }
        }
    }


    public static void removeeyes(){
        for (Location altar : altars) {
            EndPortalFrame frame = (EndPortalFrame) altar.getBlock().getBlockData();
            frame.setEye(false);
            altar.getBlock().setBlockData(frame);
        }
    }

    private void dispatchCommand(String command) {
        ConsoleCommandSender cs = Bukkit.getConsoleSender();
        Bukkit.dispatchCommand(cs, command);
    }


}
