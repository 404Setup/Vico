package one.tranic.vico.app;

import net.md_5.bungee.api.plugin.Plugin;

public final class BungeeApp extends Plugin {
    private static BungeeApp instance;
    private BungeeMetrics metrics;


    @Override
    public void onEnable() {
        instance = this;

        metrics = new BungeeMetrics(this, 24114);
    }

    public static BungeeApp getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        if (metrics != null) {
            metrics.shutdown();
        }
    }
}
