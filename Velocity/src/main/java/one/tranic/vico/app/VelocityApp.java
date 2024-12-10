package one.tranic.vico.app;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;

@Plugin(
        id = "vico",
        name = "VicoLib",
        version = BuildConstants.VERSION,
        description = "Sharing program code dependency library",
        url = "https://tranic.one",
        authors = {"404"}
)
public class VelocityApp {
    private final VelocityMetrics.Factory metricsFactory;
    private VelocityMetrics metrics;

    @Inject
    public VelocityApp(VelocityMetrics.Factory metricsFactory) {
        this.metricsFactory = metricsFactory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        metrics = metricsFactory.make(this, 23954);
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        if (metrics != null) {
            metrics.shutdown();
        }
    }
}
