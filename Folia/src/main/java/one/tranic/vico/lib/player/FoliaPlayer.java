package one.tranic.vico.lib.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class FoliaPlayer implements PluginPlayerImpl {

    public FoliaPlayer() {
    }

    @Override
    public boolean teleport(Player player, Location location) {
        player.teleportAsync(location);
        return true;
    }

    @Override
    public CompletableFuture<Boolean> teleportAsync(Player player, Location location) {
        return player.teleportAsync(location);
    }
}
