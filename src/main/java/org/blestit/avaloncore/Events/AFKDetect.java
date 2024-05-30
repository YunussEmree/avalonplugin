package org.blestit.avaloncore.Events;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AFKDetect implements Listener {

    Plugin plugin;

    public AFKDetect(Plugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void AFK(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {

            public void run() {

                String world1 = (player.getWorld().getName());
                if (world1.equals("AvalonMap")) {
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

                        player.sendTitle(ChatColor.RED + "Şu an afk'sın", ChatColor.RED + "Merkez adaya dönmek için /spawn", 1, 200, 1);
                        double olasilik = (Math.random() * 100);
                        if (olasilik < 25) player.teleport(loc1);
                        else if (olasilik < 50) player.teleport(loc2);
                        else if (olasilik < 75) player.teleport(loc3);
                        else player.teleport(loc4);
                    }
                }
                cancel();
            }
        }.runTaskTimer(plugin, 1200, 99999);


        String playername = player.getName();

        //fixing chatcolor for old vip players
        if (!player.hasPermission("avalon.kalicirenk")) {
            ConsoleCommandSender cs = Bukkit.getConsoleSender();
            Bukkit.dispatchCommand(cs, "chatcolor " + playername + " f");
            System.out.println(playername + " adlı oyuncunun sohbet rengi sıfırlandı.");
        }


        if (!event.getPlayer().hasPlayedBefore()) {
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
        if (event.getPlayer().getName().equals("yunus") || event.getPlayer().getName().equals("Treead") || event.getPlayer().getName().equals("esadsad") || event.getPlayer().getName().equals("Yalak123456") || event.getPlayer().getName().equals("NeYaptinLa") || event.getPlayer().getName().equals("plpljlk")) {
            player.kickPlayer("Kara Liste");
        }
    }


}
