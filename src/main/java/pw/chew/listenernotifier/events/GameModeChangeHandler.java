package pw.chew.listenernotifier.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import pw.chew.listenernotifier.util.ColorConverter;

public class GameModeChangeHandler implements Listener {
    private final ConfigurationSection config;

    public GameModeChangeHandler(ConfigurationSection config) {
        this.config = config;
    }

    @EventHandler
    public void handleGamemode(PlayerGameModeChangeEvent event) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.hasPermission(getPermissionNode())) {
                String parsed = ColorConverter.convertColorCodesToChatColor(getMessage());
                parsed = parsed.replace("{name}", event.getPlayer().getName()).replace("{gamemode}", event.getNewGameMode().name());
                player.sendMessage(parsed);
            }
        }
    }

    private String getPermissionNode() {
        if(config.getString("node") == null) {
            return "notify.on.gamemode";
        } else {
            return "notify.on." + config.getString("node");
        }
    }

    private String getMessage() {
        if(config.getString("message") == null) {
            return "&bListenerNotifier &7> &a{name} &bhas switched to &a{gamemode}&b!";
        } else {
            return config.getString("message");
        }
    }
}
