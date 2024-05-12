package net.hynse.finvary;

import io.github.retrooper.packetevents.util.folia.FoliaScheduler;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Random;

public final class Finvary extends JavaPlugin implements Listener {
    public static Finvary instance;
    @Override
    public void onEnable() {

        instance = this;
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Cod || entity instanceof Salmon ||
                entity instanceof TropicalFish || entity instanceof PufferFish) {
                FoliaScheduler.getRegionScheduler().runDelayed(instance, entity.getLocation(), (o) -> {
                    modifyEntityAttributes((LivingEntity) entity);
                    },1);
        }
    }

    private void modifyEntityAttributes(LivingEntity livingEntity) {
        double newSize = getRandomSize();
        Objects.requireNonNull(livingEntity.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(newSize);
        double newHealth = newSize * 6;
        Objects.requireNonNull(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(newHealth);
        livingEntity.setHealth(newHealth);
    }

    private double getRandomSize() {
        Random random = new Random();
        return 0.3 + random.nextDouble() * (1.6 - 0.7);
    }
}
