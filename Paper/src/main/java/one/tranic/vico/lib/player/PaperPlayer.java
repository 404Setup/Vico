package one.tranic.vico.lib.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class PaperPlayer implements PluginPlayerImpl {
    public PaperPlayer() {
    }

    @Override
    public boolean teleport(Player player, Location location) {
        return player.teleport(location);
    }

    @Override
    public CompletableFuture<Boolean> teleportAsync(Player player, Location location) {
        return player.teleportAsync(location);
    }
}
