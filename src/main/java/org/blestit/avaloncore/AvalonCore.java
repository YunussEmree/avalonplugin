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
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.blestit.avaloncore.AutoEvent.Events.Parkour;
import org.blestit.avaloncore.AutoEvent.Events.Runner;
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

        getCommand("givesaklambactool").setExecutor(new CommandHandler(this));
        getCommand("givegrapplinghook").setExecutor(new CommandHandler(this));
        getCommand("ejderfix").setExecutor(new CommandHandler(this));
        getCommand("kapikapa").setExecutor(new CommandHandler(this));
        getCommand("kapiac").setExecutor(new CommandHandler(this));
        getCommand("eventstart").setExecutor(new CommandHandler(this));





        this.saveDefaultConfig();

        this.setupPermissions();
        this.setupEconomy();
        this.registerAll();

        ecorestart();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        killthedragon();
        Runner.closetherunnerdoors(this);
        Parkour.closetheparkourdoors(this);
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
                Bukkit.dispatchCommand(cs, "ecoskills reload");
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
            return "2.1.6";
        }

        @Override
        public String onRequest(OfflinePlayer p, String identifier) {
            OfflinePlayer ag = Bukkit.getPlayer("AvalonGuard");
            if (identifier.equals("dragonkillkoruyucu")) {
                return setPlaceholders(ag, "%betonquest_Gorevler:point.ejderkoruyucu.amount%");
            }
            if (identifier.equals("dragonkillyasli")) {
                return setPlaceholders(ag, "%betonquest_Gorevler:point.ejderyasli.amount%");
            }
            if (identifier.equals("dragonkillgenc")) {
                return setPlaceholders(ag, "%betonquest_Gorevler:point.ejdergenc.amount%");
            }
            if (identifier.equals("dragonkillbilge")) {
                return setPlaceholders(ag, "%betonquest_Gorevler:point.ejderbilge.amount%");
            }
            if (identifier.equals("dragonkillguvenilmez")) {
                return setPlaceholders(ag, "%betonquest_Gorevler:point.ejderguvenilmez.amount%");
            }
            if (identifier.equals("dragonkillguclu")) {
                return setPlaceholders(ag, "%betonquest_Gorevler:point.ejderguclu.amount%");
            }
            if (identifier.equals("dragonkillkilgharrah")) {
                return setPlaceholders(ag, "%betonquest_Gorevler:point.ejderkilgharrah.amount%");
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

            return null;
        }
    }
}
