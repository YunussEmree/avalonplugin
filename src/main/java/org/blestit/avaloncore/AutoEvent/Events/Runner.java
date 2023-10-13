package org.blestit.avaloncore.AutoEvent.Events;

import org.blestit.avaloncore.AvalonCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.data.type.Piston;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class Runner implements Listener {


    List<Player> playersinevent;


    public void Start(AvalonCore plugin) {

        Collection<? extends Player> players = Objects.requireNonNull(plugin.getServer().getOnlinePlayers());

        for (Player player : players) {
            player.sendTitle("§6§lEtkinlik başlıyor!", "§6/warp kosu", 1, 100, 1);
            player.playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 200, 1);
        }

        Bukkit.broadcastMessage("§6Set kullanmak/İksir kullanmak/Blok bugu yapmak/Morgause kılıcı kullanmak §cyasaktır!!!");

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                for (Player player : players) {
                    player.sendTitle("§6§lEtkinlik başlıyor!", "§6/warp kosu", 1, 100, 1);
                    player.playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 200, 1);
                }
                Bukkit.broadcastMessage("§6Set kullanmak/İksir kullanmak/Blok bugu yapmak/Morgause kılıcı kullanmak §cyasaktır!!!");
            }
        }, 200);


        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                if (plugin.getServer().getWorld("world") == null) {
                    plugin.getLogger().log(Level.WARNING, "Koşu eventi için dünya bulunamadı.");
                    return;
                }


                try {
                    playersinevent = (plugin.getServer().getWorld("world")).getPlayers();
                } catch (NullPointerException e){
                    plugin.getLogger().log(Level.WARNING, "Koşu eventi için oyuncu bulunamadı.");
                    return;

                }


                new BukkitRunnable() {
                    int i = 3;

                    public void run() {
                        if (i < 0) cancel();
                        if (i == 0) {
                            openthedoors(plugin);
                        } else if (i != -1) {
                            for (Player activeplayer : playersinevent) {
                                activeplayer.sendTitle("§c" + i, "", 1, 20, 1);
                            }
                        }
                        i--;
                    }
                }.runTaskTimer(plugin, 10, 20);


            }
        }, 400);

    }

    public static void openthedoors(AvalonCore plugin){
        ArrayList<String> pistons = new ArrayList<>(plugin.getConfig().getStringList("runner.pistons"));
        ArrayList<Location> pistonlocs = new ArrayList<>();
        for (String piston : pistons) {
            String[] kordinatlar = piston.split(" ");
            Location lokasyon = new Location(Bukkit.getWorld("event"), Double.parseDouble(kordinatlar[0]), Double.parseDouble(kordinatlar[1]), Double.parseDouble(kordinatlar[2]));
            pistonlocs.add(lokasyon);
        }

        for(Location piston : pistonlocs){
            Piston gettedpiston = (Piston)piston.getBlock().getBlockData();
            gettedpiston.setExtended(false);
        }
    }

    public static void closethedoors(AvalonCore plugin){
        ArrayList<String> pistons = new ArrayList<>(plugin.getConfig().getStringList("runner.pistons"));
        ArrayList<Location> pistonlocs = new ArrayList<>();

        //PistonHead pistonHead = (PistonHead) Objects.requireNonNull(plugin.getServer().getWorld("event")).getBlockAt(1, 1, 1);

        for (String piston : pistons) {
            String[] kordinatlar = piston.split(" ");
            Location lokasyon = new Location(Bukkit.getWorld("event"), Double.parseDouble(kordinatlar[0]), Double.parseDouble(kordinatlar[1]), Double.parseDouble(kordinatlar[2]));
            pistonlocs.add(lokasyon);
        }

        for(Location piston : pistonlocs){
            Piston gettedpiston = (Piston)piston.getBlock().getBlockData();
            gettedpiston.setExtended(true);
        }
    }


    /*
    	@EventHandler
	public void maybeJump(PlayerInteractEvent event) {
		if (event.isCancelled()) return;
		if (event.getAction() != Action.PHYSICAL) return;
		Player player = event.getPlayer();
		Block pressurePlate = event.getClickedBlock();
		if (pressurePlate == null) return;
		if (pressurePlate.getType() != Material.GOLDEN_PLATE) return;
		// world condition
	}
     */

}
