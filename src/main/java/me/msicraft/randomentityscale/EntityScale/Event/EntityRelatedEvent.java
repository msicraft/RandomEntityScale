package me.msicraft.randomentityscale.EntityScale.Event;

import me.msicraft.randomentityscale.EntityScale.Data.Scale;
import me.msicraft.randomentityscale.EntityScale.Data.SyncModule;
import me.msicraft.randomentityscale.EntityScale.EntityScaleManager;
import me.msicraft.randomentityscale.RandomEntityScale;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;

public class EntityRelatedEvent implements Listener {

    private final RandomEntityScale plugin;

    public EntityRelatedEvent(RandomEntityScale plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        EntityScaleManager entityScaleManager = plugin.getEntityScaleManager();
        Scale scale = entityScaleManager.getScale();
        if (scale.isEnabled()) {
            List<CreatureSpawnEvent.SpawnReason> disableSpawnReason = entityScaleManager.getDisableSpawnReasonList();
            if (disableSpawnReason.contains(e.getSpawnReason())) {
                return;
            }
            SyncModule syncModule = entityScaleManager.getSyncModule();

            LivingEntity livingEntity = e.getEntity();

            List<EntityType> blackList = entityScaleManager.getBlackList();
            if (entityScaleManager.isReverseBlackList()) {
                if (blackList.contains(livingEntity.getType())) { //whitelist
                    scale.apply(livingEntity);
                    syncModule.apply(livingEntity);
                }
            } else {
                if (blackList.contains(livingEntity.getType())) { //blacklist
                    return;
                }
                scale.apply(livingEntity);
                syncModule.apply(livingEntity);
            }
        }
    }

}
