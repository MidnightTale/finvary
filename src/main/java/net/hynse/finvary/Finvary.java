package net.hynse.finvary;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Cod;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Finvary extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.COD) {
            Cod cod = (Cod) event.getEntity();
            //double randomHealth = getRandomHealth();
            //cod.setHealth(randomHealth);
            if (cod.getAttribute(Attribute.GENERIC_SCALE) != null) {
                cod.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(getRandomSize()
                );
            }
        }
    }

//    private double getRandomHealth() {
//        Random random = new Random();
//        return random.nextDouble() * 5 + 5;
//    }
    private double getRandomSize() {
        Random random = new Random();
        return random.nextDouble() * 5+5;
    }
}
