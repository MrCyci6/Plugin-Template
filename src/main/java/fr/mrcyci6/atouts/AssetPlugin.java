package fr.mrcyci6.atouts;

import fr.mrcyci6.atouts.commands.AssetsCommand;
import fr.mrcyci6.atouts.expansions.PlaceholderAPIExpansion;
import fr.mrcyci6.atouts.listeners.HeadDatabaseListener;
import fr.mrcyci6.atouts.managers.DatabaseManager;
import fr.mrcyci6.atouts.utils.ColoredText;
import fr.mrcyci6.atouts.utils.Configuration;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AssetPlugin extends JavaPlugin {

    private AssetPlugin instance;
    private String prefix;
    private Configuration configuration;
    private DatabaseManager databaseManager;
    private HeadDatabaseAPI headDatabaseAPI;
    private PlaceholderExpansion placeholdersExpansion;

    @Override
    public void onEnable() {
        instance = this;
        this.prefix = "[" + getName() + "] ";

        /*if (shouldUseHeadDatabaseListener()) {
            sendLog("§cHeadDatabase detected and not yet loaded, waiting for it to start plugin...");
            getServer().getPluginManager().registerEvents(new HeadDatabaseListener(this), this);
        } else {*/
            enable();
        //}
    }

    public void enable() {

        saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        this.configuration = new Configuration(config);

        this.databaseManager = new DatabaseManager(this, config);
        //this.headDatabaseAPI = new HeadDatabaseAPI();

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.placeholdersExpansion = new PlaceholderAPIExpansion(this, config);
            this.placeholdersExpansion.register();
            sendLog("&aPlaceholderAPI charged successfully.");
        } else {
            sendLog("&cPlaceholderAPI is missing.");
        }

        getCommand("atout").setExecutor(new AssetsCommand(this, config));

        PluginManager pm = this.getServer().getPluginManager();
        //pm.registerEvents(new InfoDiscordListener(this, config), this);

        this.sendLog("&aPlugin enabled.");
    }

    @Override
    public void onDisable() {

        this.sendLog("&cPlugin disabled.");
    }

    public AssetPlugin getInstance() {
        return instance;
    }

    public void sendLog(String text) {
        Bukkit.getConsoleSender().sendMessage(ColoredText.getText(prefix + text));
    }

    private boolean shouldUseHeadDatabaseListener() {
        if (!getServer().getPluginManager().isPluginEnabled("HeadDatabase"))
            return false;
        try {
            (headDatabaseAPI = new HeadDatabaseAPI()).getRandomHead();
            return false;
        } catch (IllegalArgumentException ignored) {
            return true;
        }
    }
}
