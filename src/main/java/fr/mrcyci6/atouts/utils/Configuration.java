package fr.mrcyci6.atouts.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private final FileConfiguration config;
    private final List<String> helpAdmin;

    public Configuration(FileConfiguration config) {
        this.config = config;
        this.helpAdmin = new ArrayList<>();
        this.setupHelpAdmin();
    }

    private void setupHelpAdmin() {
        this.config.getStringList("assets-help-admin").forEach(line -> {
            this.helpAdmin.add(ColoredText.getText(line));
        });
    }

    public List<String> getHelpAdmin() {
        return helpAdmin;
    }
}