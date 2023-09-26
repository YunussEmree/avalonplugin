package org.blestit.avaloncore;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.blestit.avaloncore.Dragon.spawn;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import static org.blestit.avaloncore.Dragon.spawn.*;

import static me.clip.placeholderapi.PlaceholderAPI.setPlaceholders;
import static org.blestit.avaloncore.Dragon.spawn.killthedragon;
import static org.blestit.avaloncore.Statics.afterrestartcommands.yayfix;

public final class AvalonCore extends JavaPlugin {

    public Permission permission;
    public Economy economy;

    private ProtocolManager protocolManager;
    public void onLoad() {
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new Events(this),this);
        Bukkit.getPluginManager().registerEvents(new spawn(this),this);

        ItemManager.init();

        yayfix(this);

        new PAPI(this).register();

        protocolManager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.WORLD_PARTICLES) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                if (event.getPacketType() != PacketType.Play.Server.WORLD_PARTICLES) {
                    return;
                }

                if (packet.getNewParticles().read(0).getParticle() == Particle.DAMAGE_INDICATOR) {
                    event.setCancelled(true);
                }
            }
        });

        System.out.println(ChatColor.GREEN + "[AVALON] Eklenti Aktif!");


        getCommand("givesaklambactool").setExecutor(new CommandHandler());
        getCommand("givegrapplinghook").setExecutor(new CommandHandler());
        getCommand("ejderfix").setExecutor(new CommandHandler());

        this.saveDefaultConfig();

        this.setupPermissions();
        this.setupEconomy();
        this.registerAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        killthedragon();
    }
    public void registerAll() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new DeathMoney(this), this);
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = this.getServer().getServicesManager().getRegistration(Permission.class);
        if (permissionProvider != null) {
            this.permission = (Permission)permissionProvider.getProvider();
        }

        return this.permission != null;
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            this.economy = (Economy)economyProvider.getProvider();
        }

        return this.economy != null;
    }

    private void ecorestart(){
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                ConsoleCommandSender cs = Bukkit.getConsoleSender();
                Bukkit.dispatchCommand(cs, "ecoarmor reload");
                Bukkit.dispatchCommand(cs, "ecoitems reload");
                //getServer().getPluginManager().isPluginEnabled("EcoItems");
            }
        }, 600);
    }

    class PAPI extends PlaceholderExpansion {

        AvalonCore plugin;

        public PAPI(AvalonCore plugin) {
            this.plugin = plugin;
        }

        @Override
        public String getIdentifier() {
            return "avalon";
        }

        @Override
        public String getAuthor() {
            return "blestit";
        }

        @Override
        public String getVersion() {
            return this.getVersion();
        }

        @Override
        public String onRequest(OfflinePlayer p, String identifier) {
            OfflinePlayer ag = Bukkit.getPlayer("AvalonGuard");
            if (identifier.equals("dragonkillkoruyucu")) {
                return setPlaceholders(ag, "%betonquest_default:point.ejderkoruyucu.amount%");
            }
            if (identifier.equals("dragonkillyasli")) {
                return setPlaceholders(ag, "%betonquest_default:point.ejderyasli.amount%");
            }
            if (identifier.equals("dragonkillgenc")) {
                return setPlaceholders(ag, "%betonquest_default:point.ejdergenc.amount%");
            }
            if (identifier.equals("dragonkillbilge")) {
                return setPlaceholders(ag, "%betonquest_default:point.ejderbilge.amount%");
            }
            if (identifier.equals("dragonkillguvenilmez")) {
                return setPlaceholders(ag, "%betonquest_default:point.ejderguvenilmez.amount%");
            }
            if (identifier.equals("dragonkillguclu")) {
                return setPlaceholders(ag, "%betonquest_default:point.ejderguclu.amount%");
            }
            if (identifier.equals("dragonkillkilgharrah")) {
                return setPlaceholders(ag, "%betonquest_default:point.ejderkilgharrah.amount%");
            }
            if (identifier.equals("dragonPlayerDamage")) {
                if (!hasarMapi.containsKey(p.getName())) return String.valueOf(0);
                return String.valueOf(Math.round(hasarMapi.get(p.getName())));
            }
            if (identifier.equals("dragoninstanthealth")) {
                return String.valueOf(Math.round(dragonAnlikCan));
            }
            if (identifier.equals("dragonmaxhealth")) {
                return String.valueOf(bizimDragonHealth);
            }
            if (identifier.equals("zindankat")) {
                if (p.getPlayer() != null && p.getPlayer().getLocation().getWorld() != null && p.getPlayer().getLocation().getWorld().getName().equals("zindan")) {

                    Player player = p.getPlayer();
                    World world = Bukkit.getServer().getWorld("zindan");
                    assert world != null;
                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                    RegionManager regions = container.get(BukkitAdapter.adapt(world));
                    assert regions != null;
                    ProtectedRegion kat1 = regions.getRegion("zindankat1");
                    assert kat1 != null;
                    if (kat1.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "1";
                    }
                    ProtectedRegion kat2 = regions.getRegion("zindankat2");
                    assert kat2 != null;
                    if (kat2.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "2";
                    }
                    ProtectedRegion kat3 = regions.getRegion("zindankat3");
                    assert kat3 != null;
                    if (kat3.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "3";
                    }
                    ProtectedRegion kat4 = regions.getRegion("zindankat4");
                    assert kat4 != null;
                    if (kat4.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "4";
                    }
                    ProtectedRegion kat5 = regions.getRegion("zindankat5");
                    assert kat5 != null;
                    if (kat5.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "5";
                    }
                    ProtectedRegion kat6 = regions.getRegion("zindankat6");
                    assert kat6 != null;
                    if (kat6.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "6";
                    }
                    ProtectedRegion kat7 = regions.getRegion("zindankat7");
                    assert kat7 != null;
                    if (kat7.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "7";
                    }
                    ProtectedRegion kat8 = regions.getRegion("zindankat8");
                    assert kat8 != null;
                    if (kat8.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "8";
                    }
                    ProtectedRegion kat9 = regions.getRegion("zindankat9");
                    assert kat9 != null;
                    if (kat9.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "9";
                    }
                    ProtectedRegion kat10 = regions.getRegion("zindankat10");
                    assert kat10 != null;
                    if (kat10.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "10";
                    }
                    ProtectedRegion kat11 = regions.getRegion("zindankat11");
                    assert kat11 != null;
                    if (kat11.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "11";
                    }
                    ProtectedRegion kat12 = regions.getRegion("zindankat12");
                    assert kat12 != null;
                    if (kat12.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "12";
                    }
                    ProtectedRegion kat13 = regions.getRegion("zindankat13");
                    assert kat13 != null;
                    if (kat13.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "13";
                    }
                    ProtectedRegion kat14 = regions.getRegion("zindankat14");
                    assert kat14 != null;
                    if (kat14.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "14";
                    }
                    ProtectedRegion kat15 = regions.getRegion("zindankat15");
                    assert kat15 != null;
                    if (kat15.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "15";
                    }
                    ProtectedRegion kat16 = regions.getRegion("zindankat16");
                    assert kat16 != null;
                    if (kat16.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "16";
                    }
                    ProtectedRegion kat17 = regions.getRegion("zindankat17");
                    assert kat17 != null;
                    if (kat17.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "17";
                    }
                    ProtectedRegion kat18 = regions.getRegion("zindankat18");
                    assert kat18 != null;
                    if (kat18.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "18";
                    }
                    ProtectedRegion kat19 = regions.getRegion("zindankat19");
                    assert kat19 != null;
                    if (kat19.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "19";
                    }
                    ProtectedRegion kat20 = regions.getRegion("zindankat20");
                    assert kat20 != null;
                    if (kat20.contains(BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))) {
                        return "20";
                    }
                }
            }
            return null;
        }
    }
}
