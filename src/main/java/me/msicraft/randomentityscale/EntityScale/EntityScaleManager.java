package me.msicraft.randomentityscale.EntityScale;

import me.msicraft.randomentityscale.EntityScale.Data.Scale;
import me.msicraft.randomentityscale.EntityScale.Data.SyncModule;
import me.msicraft.randomentityscale.RandomEntityScale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class EntityScaleManager {

    private final RandomEntityScale plugin;
    private final Scale scale;
    private final SyncModule syncModule;

    private final List<EntityType> blackList = new ArrayList<>();
    private boolean reverseBlackList = false;

    private final List<CreatureSpawnEvent.SpawnReason> disableSpawnReasonList = new ArrayList<>();

    public EntityScaleManager(RandomEntityScale plugin) {
        this.plugin = plugin;
        this.scale = new Scale(plugin);
        this.syncModule = new SyncModule(plugin);
    }

    public void reloadVariables() {
        FileConfiguration config = plugin.getConfig();

        this.reverseBlackList = config.getBoolean("BlackList.Reverse");
        List<String> blackListTemp = config.getStringList("BlackList.List");

        for(String s : blackListTemp) {
            try {
                EntityType entityType = EntityType.valueOf(s.toUpperCase());
                blackList.add(entityType);
            } catch (IllegalArgumentException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + RandomEntityScale.PREFIX + " Invalid entityType: " + s);
            }
        }

        List<String> spawnReasonTemp = config.getStringList("DisableSpawnReason");

        for(String s : spawnReasonTemp) {
            try {
                CreatureSpawnEvent.SpawnReason spawnReason = CreatureSpawnEvent.SpawnReason.valueOf(s.toUpperCase());
                disableSpawnReasonList.add(spawnReason);
            } catch (IllegalArgumentException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + RandomEntityScale.PREFIX + " Invalid spawnReason: " + s);
            }
        }

        scale.updateVariables(plugin);
        syncModule.updateVariables(plugin);
    }

    public boolean isReverseBlackList() {
        return reverseBlackList;
    }

    public Scale getScale() {
        return scale;
    }

    public SyncModule getSyncModule() {
        return syncModule;
    }

    public List<EntityType> getBlackList() {
        return blackList;
    }

    public List<CreatureSpawnEvent.SpawnReason> getDisableSpawnReasonList() {
        return disableSpawnReasonList;
    }
}
