package one.tranic.vico.lib.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.CompletableFuture;

public class SpigotPlayer implements PluginPlayerImpl {

    public SpigotPlayer() {
    }

    @Override
    public boolean teleport(Player player, Location location) {
        return player.teleport(location);
    }

    @Override
    public java.util.concurrent.CompletableFuture<Boolean> teleportAsync(Player player, Location location) {
        return CompletableFuture.supplyAsync(() -> teleport(player,location));
    }
}
