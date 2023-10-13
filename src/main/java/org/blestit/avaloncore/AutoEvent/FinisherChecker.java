package org.blestit.avaloncore.AutoEvent;

import jdk.jfr.Label;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FinisherChecker implements Listener {

    String firstplayer;
    String SecondPlayer;
    String ThirdPlayer;
    String FourthPlayer;
    String FifthPlayer;

    int counter = 1;


	public void PlateActive(PlayerInteractEvent event) {
		if (event.isCancelled()) return;
		if (event.getAction() != Action.PHYSICAL) return;
		Block pressurePlate = event.getClickedBlock();
		if (pressurePlate == null) return;
		if (pressurePlate.getType() != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) return;
		if (!event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) return;

        switch (counter) {
            case (1):
                { firstplayer = event.getPlayer().getName();
                    break;
                }
            case (2):
            { SecondPlayer = event.getPlayer().getName();
                break;
            }
            case (3):
            { ThirdPlayer = event.getPlayer().getName();
                break;
            }
            case (4):
            { FourthPlayer = event.getPlayer().getName();
                break;
            }
            case (5):
            { FifthPlayer = event.getPlayer().getName();
                break;
            }
        }

        counter++;
	}



}
