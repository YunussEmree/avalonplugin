package org.blestit.avaloncore.AutoEvent;

import org.blestit.avaloncore.AvalonCore;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StartEvent {

    private final AvalonCore plugin;
    private final boolean enabled;
    private final String time;
    private final List<String> ingameCommands;

    public StartEvent(AvalonCore plugin, boolean enabled, String time, List<String> ingameCommands) {
        this.plugin = plugin;
        this.enabled = enabled;
        this.time = time;
        this.ingameCommands = ingameCommands;

        String timeZone = "Europe/Istanbul";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime localTime = LocalTime.parse(time, formatter);
        ZonedDateTime targetTime = ZonedDateTime.of(LocalDate.now(), localTime, ZoneId.of(timeZone));

        if (enabled) {
            if (targetTime.isBefore(ZonedDateTime.now())) {
                targetTime = targetTime.plusDays(1);
            }
            long millis = targetTime.toInstant().toEpochMilli() - ZonedDateTime.now().toInstant().toEpochMilli();

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (String command : ingameCommands) {
                        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command);
                    }
                }
            }.runTaskTimer(plugin, millis / 50, 20 * 60 * 60 * 24);
        }
    }
}
