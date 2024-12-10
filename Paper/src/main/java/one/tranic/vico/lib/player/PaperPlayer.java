package one.tranic.vico.lib.player;

import one.tranic.vico.utils.Platform;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class PaperPlayer implements PluginPlayerImpl {
    public PaperPlayer() {
    }

    @Override
    public boolean teleport(Player player, Location location) {
        if (Platform.get() == Platform.Folia || Platform.get() == Platform.ShreddedPaper) {
            player.teleportAsync(location);
            return true;
        }
        return player.teleport(location);
    }

    @Override
    public CompletableFuture<Boolean> teleportAsync(Player player, Location location) {
        return player.teleportAsync(location);
    }
}
