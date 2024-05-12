package net.hynse.finvary;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Cod;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Salmon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Finvary extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register event listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        EntityType entityType = event.getEntityType();
        if (entityType == EntityType.COD || entityType == EntityType.SALMON ||
                entityType == EntityType.TROPICAL_FISH || entityType == EntityType.PUFFERFISH) {
            Cod cod = (Cod) event.getEntity();

            // Set new size based on chance
            double newSize = getRandomSize();
            cod.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(newSize);
            double newHealth = newSize * 2;
            cod.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newHealth);
            cod.setHealth(newHealth);
        }
    }
//    // Get spawn location
//    double spawnX = cod.getLocation().getX();
//    double spawnY = cod.getLocation().getY();
//    double spawnZ = cod.getLocation().getZ();
//    getLogger().info("Cod spawned at X: " + spawnX + ", Y: " + spawnY + ", Z: " + spawnZ);
//
//    // Get initial size
//    double initialSize = cod.getAttribute(Attribute.GENERIC_SCALE).getBaseValue();
//    getLogger().info("Initial size: " + initialSize);
    //getLogger().info("New size: " + newSize);

    private double getRandomSize() {
        Random random = new Random();
        double chance = random.nextDouble();

        if (chance <= 0.3) {
            // 30% chance to spawn size in the range 0.55 to 0.75
            return 0.55 + random.nextDouble() * 0.2;
        } else {
            // 70% chance to spawn size in the range 0.75 to 1.7
            return 0.75 + random.nextDouble() * 0.95;
        }
    }
}

