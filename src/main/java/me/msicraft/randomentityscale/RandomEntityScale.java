package me.msicraft.randomentityscale;

import me.msicraft.randomentityscale.Command.MainCommand;
import me.msicraft.randomentityscale.EntityScale.EntityScaleManager;
import me.msicraft.randomentityscale.EntityScale.Event.EntityRelatedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class RandomEntityScale extends JavaPlugin {

    private static RandomEntityScale plugin;

    public static RandomEntityScale getPlugin() {
        return plugin;
    }

    public static final String PREFIX = ChatColor.GREEN + "[RandomEntityScale]";

    private EntityScaleManager entityScaleManager;

    @Override
    public void onEnable() {
        plugin = this;
        createConfigFiles();

        this.entityScaleManager = new EntityScaleManager(this);

        eventRegister();
        commandRegister();
        reloadVariables();

        getServer().getConsoleSender().sendMessage(PREFIX + " plugin has been enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(PREFIX + ChatColor.RED + " plugin has been disabled");
    }

    public void reloadVariables() {
        reloadConfig();
        entityScaleManager.reloadVariables();
    }

    private void eventRegister() {
        Bukkit.getPluginManager().registerEvents(new EntityRelatedEvent(this), this);
    }

    private void commandRegister() {
        Bukkit.getPluginCommand("randomentityscale").setExecutor(new MainCommand(this));
    }

    private void createConfigFiles() {
        File configf = new File(getDataFolder(), "config.yml");
        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public EntityScaleManager getEntityScaleManager() {
        return entityScaleManager;
    }
}
