package fr.mrcyci6.atouts.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import fr.mrcyci6.atouts.AssetPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class DatabaseManager {
    private final FileConfiguration config;
    private final AssetPlugin plugin;
    private Connection connection;

    public DatabaseManager(AssetPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;

        ConfigurationSection dbConfig = this.config.getConfigurationSection("database");
        String mode = dbConfig.getString("storage-mode");
        String host = dbConfig.getString("host");
        int port = dbConfig.getInt("port");
        String database = dbConfig.getString("database");
        String user = dbConfig.getString("user");
        String password = dbConfig.getString("password");
        try {
            if (mode.equalsIgnoreCase("mysql")) {
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
            } else if (mode.equalsIgnoreCase("mariadb")) {
                Class.forName("org.mariadb.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mariadb://" + host + ":" + port + "/" + database, user, password);
            } else {
                Class.forName("org.sqlite.JDBC");
                this.connection = DriverManager.getConnection("jdbc:sqlite://" + this.plugin.getDataFolder().getAbsolutePath() + "/storage.db");
            }
            createTable(this.connection);
            this.plugin.sendLog("&aConnected to the database.");
        } catch (SQLException e) {
            this.plugin.sendLog("&cError while connecting to the database.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            this.plugin.sendLog("&cError while connecting to the database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void createTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `assets` (" +
                    "`username` VARCHAR(255) NOT NULL, " +
                    "`speed` INT(255) NOT NULL, " +
                    "`strength` INT(255) NOT NULL, " +
                    "`fire_resistance` INT(255) NOT NULL, " +
                    "`haste` INT(255) NOT NULL, " +
                    "`jumpboost` INT(255) NOT NULL, " +
                    "`nohunger` INT(255) NOT NULL, " +
                    "`nofall` INT(255) NOT NULL, " +
                    "`nightvision` INT(255) NOT NULL, " +
                    "`waterbreathing` INT(255) NOT NULL, " +
                    "`sangsue` INT(255) NOT NULL, " +
                    "`norod` INT(255) NOT NULL, " +
                    "PRIMARY KEY (`username`)" +
                    ")";
            statement.executeUpdate(sql);
            statement.close();
            this.plugin.sendLog("&aDatabase initialized.");
        } catch (SQLException e) {
            this.plugin.sendLog("&cError while initializing database tables.");
            e.printStackTrace();
        }
    }
}