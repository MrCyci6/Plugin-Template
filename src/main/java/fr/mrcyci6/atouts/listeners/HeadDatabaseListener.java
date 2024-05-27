package fr.mrcyci6.atouts.listeners;

import fr.mrcyci6.atouts.AssetPlugin;
import me.arcaniax.hdb.api.DatabaseLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class HeadDatabaseListener implements Listener {

    private AssetPlugin plugin;

    public HeadDatabaseListener(AssetPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onHeadDatabaseLoad(DatabaseLoadEvent event) {
        this.plugin.getLogger().info("§aHeadDatabase loaded, starting plugin...");
        this.plugin.enable();
    }
}
