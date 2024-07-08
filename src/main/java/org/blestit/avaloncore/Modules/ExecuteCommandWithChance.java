package org.blestit.avaloncore.Modules;

import org.bukkit.Bukkit;
import java.util.Random;

public class ExecuteCommandWithChance {
    public static void executeCommand(String command, String playerName) {
        command = command.replace("%player%", playerName);
        Random random = new Random();
        if (command.contains("%")) {
            try {
                // get the chance value from the command
                int chance = Integer.parseInt(command.split("%")[1]);
                String actualCommand = command.split("%")[0];
                // create a random number between 0 and 100
                double randomNumber = random.nextDouble(100);
                if (randomNumber <= chance) {
                    // If chance is successful, execute the command
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), actualCommand);
                }
            } catch (NumberFormatException e) {
                // If the chance format is invalid, print an error message
                System.err.println("Invalid chance format in command: " + command);
            }
        } else {
            // If the command does not contain a chance value, execute the command
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }
}