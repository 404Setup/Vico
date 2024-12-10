package one.tranic.vico.lib.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface PluginPlayerImpl {
    boolean teleport(Player player, Location location);

    java.util.concurrent.CompletableFuture<Boolean> teleportAsync(Player player, Location location);
}
