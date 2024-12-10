package one.tranic.vico.app;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotApp extends JavaPlugin {
    private SpigotMetrics metrics;

    @Override
    public void onEnable() {
        metrics = new SpigotMetrics(this, 24113);
    }

    @Override
    public void onDisable() {
        if (metrics != null) {
            metrics.shutdown();
        }
    }
}
