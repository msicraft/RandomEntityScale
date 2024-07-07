package me.msicraft.randomentityscale.Command;

import me.msicraft.randomentityscale.RandomEntityScale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private final RandomEntityScale plugin;

    public MainCommand(RandomEntityScale plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("randomentityscale")) {
            String var = args[0];

            if (sender.hasPermission("randomentityscale.command.reload")) {
                if (var.equals("reload")) {
                    plugin.reloadVariables();

                    sender.sendMessage(RandomEntityScale.PREFIX + " Config reloaded");
                }
            } else {
                sender.sendMessage(RandomEntityScale.PREFIX + " You don't have permission to use this command");
            }
        }

        return false;
    }
}
