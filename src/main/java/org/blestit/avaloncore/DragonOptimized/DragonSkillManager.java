package org.blestit.avaloncore.DragonOptimized;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

import java.util.List;

import static org.blestit.avaloncore.DragonOptimized.Dragon.SkillDamage;

public class DragonSkillManager implements Listener {

    public static void thunder(EnderDragon dragon) {
        Location dragonLocation = dragon.getLocation();
        World world = dragonLocation.getWorld();
        List<Entity> entities = dragon.getNearbyEntities(50, 50, 50);

        for(Entity entity : entities){
            if(entity instanceof Player){
                Player player = (Player) entity;
                player.damage(SkillDamage);
                world.strikeLightning(player.getLocation());
            }
        }
    }
    public static void thunderStorm(EnderDragon dragon) {
        Location dragonLocation = dragon.getLocation();
        World world = dragonLocation.getWorld();
        List<Entity> entities = dragon.getNearbyEntities(50, 50, 50);

        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.damage(SkillDamage);
                world.strikeLightning(player.getLocation());

                // Set the weather to storm at the player's location
                player.getWorld().setStorm(true);

                // Create a BukkitRunnable to strike lightning at random locations around the player
                new BukkitRunnable() {
                    int counter = 0; // Counter to track the number of lightning strikes
                    Random random = new Random();

                    @Override
                    public void run() {
                        if (counter < 10) { // Strike lightning 10 times
                            // Generate random coordinates within 10 blocks of the player
                            double x = player.getLocation().getX() + (random.nextDouble() * 20) - 10;
                            double z = player.getLocation().getZ() + (random.nextDouble() * 20) - 10;

                            // Strike lightning at the generated coordinates
                            world.strikeLightning(new Location(world, x, player.getLocation().getY(), z));

                            counter++;
                        } else {
                            // After striking lightning 10 times, stop the storm and cancel the task
                            player.getWorld().setStorm(false);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Bukkit.getPluginManager().getPlugin("YourPluginName"), 0L, 20L); // Run the task every second (20 ticks = 1 second)
            }
        }
    }
    public static void iceBreath(EnderDragon dragon) {
        Location dragonLocation = dragon.getLocation();
        List<Entity> entities = dragon.getNearbyEntities(50, 50, 50);

        for(Entity entity : entities){
            if(entity instanceof Player){
                Player player = (Player) entity;
                // Apply the freezing effect to the player
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 3));
                player.sendMessage("You've been frozen by the dragon's ice breath!");
            }
        }
    }
    public static void iceStorm(EnderDragon dragon) {
        Location dragonLocation = dragon.getLocation();
        World world = dragonLocation.getWorld();
        List<Entity> entities = dragon.getNearbyEntities(50, 50, 50);

        for(Entity entity : entities){
            if(entity instanceof Player){
                Player player = (Player) entity;
                // Apply the freezing effect to the player
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 3));
                player.sendMessage("You've been frozen by the dragon's ice storm!");

                // Set the weather to snow at the player's location
                player.getWorld().setStorm(true);
                player.getWorld().setWeatherDuration(200); // The duration is in ticks (200 ticks = 10 seconds)

                // Create a BukkitRunnable to apply slowness and damage to all players within the storm
                new BukkitRunnable() {
                    int counter = 0; // Counter to track the duration of the storm

                    @Override
                    public void run() {
                        if (counter < 10) { // Apply effects for 10 seconds
                            // Apply slowness and damage to all players within a radius of 50 blocks
                            for (Player p : player.getWorld().getPlayers()) {
                                if (p.getLocation().distance(player.getLocation()) <= 50) {
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1));
                                    p.damage(1.0);
                                }
                            }

                            counter++;
                        } else {
                            // After 10 seconds, stop the storm and cancel the task
                            player.getWorld().setStorm(false);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Bukkit.getPluginManager().getPlugin("YourPluginName"), 0L, 20L); // Run the task every second (20 ticks = 1 second)
            }
        }
    }
    public static void heal(EnderDragon dragon) {
        double currentHealth = dragon.getHealth();
        double maxHealth = dragon.getMaxHealth();

        // If the dragon's health is not full
        if (currentHealth < maxHealth) {
            // Heal the dragon by 5% of its max health
            double healAmount = maxHealth * 0.05;
            double newHealth = currentHealth + healAmount;

            // Make sure the new health does not exceed the max health
            if (newHealth > maxHealth) {
                newHealth = maxHealth;
            }

            // Set the new health
            dragon.setHealth(newHealth);
        }
    }
    public static void teleport(EnderDragon dragon) {
        Random random = new Random();
        Location dragonLocation = dragon.getLocation();

        // Generate random coordinates within 50 blocks of the dragon
        double x = dragonLocation.getX() + (random.nextDouble() * 100) - 50;
        double y = dragonLocation.getY() + (random.nextDouble() * 100) - 50;
        double z = dragonLocation.getZ() + (random.nextDouble() * 100) - 50;

        // Create a new location with these coordinates
        Location newLocation = new Location(dragonLocation.getWorld(), x, y, z);

        // Teleport the dragon to the new location
        dragon.teleport(newLocation);
    }
    public static void fireball(EnderDragon dragon) {
        List<Entity> entities = dragon.getNearbyEntities(50, 50, 50);
        Random random = new Random();

        // Filter the list to only include players
        List<Player> players = entities.stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .toList();

        // If there are no players within the radius, return
        if (players.isEmpty()) {
            return;
        }

        // Select a random player from the list
        Player target = players.get(random.nextInt(players.size()));

        // Create a fireball and set its direction towards the player
        Fireball fireball = dragon.getWorld().spawn(dragon.getLocation(), Fireball.class);
        Vector direction = target.getLocation().subtract(dragon.getLocation()).toVector();
        fireball.setDirection(direction);

        // Set the fireball's size (the yield is the explosion power of the fireball)
        fireball.setYield(4.0F); // This can be adjusted as needed
    }
    public static void fireballStorm(EnderDragon dragon) {
        List<Entity> entities = dragon.getNearbyEntities(50, 50, 50);
        Random random = new Random();

        // Filter the list to only include players
        List<Player> players = entities.stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .toList();

        // If there are no players within the radius, return
        if (players.isEmpty()) {
            return;
        }

        // Determine the number of fireballs to shoot (between 20 and 30)
        int numFireballs = 20 + random.nextInt(11);

        for (int i = 0; i < numFireballs; i++) {
            // Select a random player from the list
            Player target = players.get(random.nextInt(players.size()));

            // Create a fireball and set its direction towards the player
            Fireball fireball = dragon.getWorld().spawn(dragon.getLocation(), Fireball.class);
            Vector direction = target.getLocation().subtract(dragon.getLocation()).toVector();
            fireball.setDirection(direction);

            // Set the fireball's size (the yield is the explosion power of the fireball)
            fireball.setYield(4.0F); // This can be adjusted as needed
        }
    }


    public static void summonPhantoms(EnderDragon dragon) {
        double phantomHealth = dragon.getMaxHealth() * 0.05;
        List<Entity> entities = dragon.getNearbyEntities(50, 50, 50);
        Random random = new Random();

        // Filter the list to only include players
        List<Player> players = entities.stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .toList();

        // If there are no players within the radius, return
        if (players.isEmpty()) {
            return;
        }

        // Determine the number of phantoms to summon (between 5 and 10)
        int numPhantoms = 5 + random.nextInt(6);

        for (int i = 0; i < numPhantoms; i++) {
            // Select a random player from the list
            Player target = players.get(random.nextInt(players.size()));

            // Summon a phantom at the player's location and set its health
            Phantom phantom = target.getWorld().spawn(target.getLocation(), Phantom.class);
            phantom.setMaxHealth(phantomHealth);
            phantom.setHealth(phantomHealth);

            // Set the phantom's target to the player
            phantom.setTarget(target);

        }
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Phantom && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Phantom phantom = (Phantom) event.getDamager();

            // Calculate the knockback direction
            Vector knockbackDirection = player.getLocation().toVector().subtract(phantom.getLocation().toVector()).normalize();

            // Set the knockback
            player.setVelocity(knockbackDirection.multiply(10));
        }
    }




}


