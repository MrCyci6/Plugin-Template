package fr.mrcyci6.atouts.expansions;

import com.avaje.ebean.validation.NotNull;
import fr.mrcyci6.atouts.AssetPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    private final AssetPlugin plugin;
    private final FileConfiguration config;

    public PlaceholderAPIExpansion(AssetPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "assets";
    }

    @Override
    public @NotNull String getAuthor() {
        return "MrCyci6";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    public String onPlaceholderRequest(Player p, String params) {

        if(p == null) {
            return "&cX";
        } else if(params.contains("status_")) {
            String asset = params.replace("status_", "");
            return "";
        } else if(params.contains("toggle_status_")) {
            String asset = params.replace("toggle_status_", "");
            return "";
        } else {
            return "&cX";
        }

    }
}
