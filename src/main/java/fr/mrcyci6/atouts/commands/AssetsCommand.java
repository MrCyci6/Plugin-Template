package fr.mrcyci6.atouts.commands;

import fr.mrcyci6.atouts.AssetPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class AssetsCommand implements CommandExecutor {

    private AssetPlugin plugin;
    private FileConfiguration config;

    public AssetsCommand(AssetPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        return false;
    }
}
