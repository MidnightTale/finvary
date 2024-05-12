package net.hynse.finvary;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Cod;
import org.bukkit.entity.EntityType;
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
        if (event.getEntityType() == EntityType.COD) {
            Cod cod = (Cod) event.getEntity();

            // Get spawn location
            double spawnX = cod.getLocation().getX();
            double spawnY = cod.getLocation().getY();
            double spawnZ = cod.getLocation().getZ();
            getLogger().info("Cod spawned at X: " + spawnX + ", Y: " + spawnY + ", Z: " + spawnZ);

            // Get initial size
            double initialSize = cod.getAttribute(Attribute.GENERIC_SCALE).getBaseValue();
            getLogger().info("Initial size: " + initialSize);

            // Set new size
            double newSize = getRandomSize();
            cod.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(newSize);
            getLogger().info("New size: " + newSize);
        }
    }

    private double getRandomSize() {
        Random random = new Random();
        return random.nextDouble() * 5 + 5;
    }
}
