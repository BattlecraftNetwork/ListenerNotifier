package pw.chew.listenernotifier;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pw.chew.listenernotifier.events.GameModeChangeHandler;

public final class ListenerNotifier extends JavaPlugin {

    @Override
    public void onEnable() {
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        // Plugin startup logic
        ConfigurationSection gameMode = config.getConfigurationSection("PlayerGameModeChangeEvent");
        if (gameMode == null) {
            getLogger().warning("Config is outdated! Please regenerate thanks.");
        } else {
            if (gameMode.getBoolean("enabled"))
                getServer().getPluginManager().registerEvents(new GameModeChangeHandler(gameMode), this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
