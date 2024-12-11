package one.tranic.vico.app;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(
        id = "vico",
        name = "VicoLib",
        version = BuildConstants.VERSION,
        description = "Sharing program code dependency library",
        url = "https://tranic.one",
        authors = {"404"}
)
public class VelocityApp {
    private static VelocityApp instance;
    private final ProxyServer proxy;
    private final VelocityMetrics.Factory metricsFactory;
    private VelocityMetrics metrics;

    @Inject
    public VelocityApp(ProxyServer proxy , VelocityMetrics.Factory metricsFactory) {
        instance = this;

        this.proxy = proxy;
        this.metricsFactory = metricsFactory;
    }

    public ProxyServer getServer() {
        return proxy;
    }

    public static VelocityApp getInstance() {
        return instance;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        metrics = metricsFactory.make(this, 24115);
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        if (metrics != null) {
            metrics.shutdown();
        }
    }
}
