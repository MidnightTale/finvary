package net.hynse.finvary;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
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
        Entity entity = event.getEntity();
        if (entity instanceof Cod || entity instanceof Salmon ||
                entity instanceof TropicalFish || entity instanceof PufferFish) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                double newSize = getRandomSize(0.5, 2.3);
                livingEntity.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(newSize);
                double newHealth = newSize * 6;
                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newHealth);
                livingEntity.setHealth(newHealth);
            }
        }
    }
    private double getRandomSize(double minSize, double maxSize) {
        Random random = new Random();
        return minSize + random.nextDouble() * (maxSize - minSize);
    }
}

