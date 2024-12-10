package one.tranic.vico.app;

import net.md_5.bungee.api.plugin.Plugin;

public final class BungeeApp extends Plugin {
    private BungeeMetrics metrics;

    @Override
    public void onEnable() {
        metrics = new BungeeMetrics(this, 23953);
    }

    @Override
    public void onDisable() {
        if (metrics != null) {
            metrics.shutdown();
        }
    }
}
