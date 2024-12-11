# VicoLib
Provides a reusable API for easy development

## Update Tool
Support: Github Release, Hangar, Modrinth, Spigot, Spiget

## MessageSender
Easy to use Kyori API operations

```Java
// You cannot use this method to create new instances everywhere.
//It should only be called once during the lifetime of your plugin.
VicoImpl plugin = VicoAPI.getVicoPlugin(); 

plugin.getMessageSender().sendMessage("message", sender);
plugin.getMessageSender().close(); // Called when the plugin is closed
```

## Player teleport
It is just a very simple API and does not work with ProxyServer.

```Java
// You cannot use this method to create new instances everywhere.
//It should only be called once during the lifetime of your plugin.
VicoImpl plugin = VicoAPI.getVicoPlugin();

// In folia, it always returns true
plugin.getPluginPlayer().teleport(player, location);
CompletableFuture<Boolean> result = plugin.getPluginPlayer().teleportAsync(player, location);
```

## Scheduler
Vico has two types of schedulers, namely Proxy scheduler and PluginServer scheduler.

### Proxy Scheduler

```Java
VicoAPI.getProxyScheduler(proxyServer).runAsyncOnProxy(this, () -> System.out.println("ProxyServer test"));
VicoAPI.getProxyScheduler(proxyServer).runAsyncOnPlugin(this, () -> System.out.println("ProxyServer test"));
```

### Plugin Scheduler

Proper use of PluginSchedulerBuilder can reduce a lot of boilerplate code and reduce the workload of developers, 
but you still need to distribute the tasks reasonably, which is for Folia compatibility considerations.

Except for some broken APIs, it is not that difficult to be compatible, and the standard API is basically stable.

```java
PluginSchedulerBuilder.builder(this).sync().setTask(() -> System.out.println("PluginServer test")).run();

// In Spigot and Paper, this is equivalent to the following code
Bukkit.getScheduler().runTask(this, () -> System.out.println("PluginServer test"));

// In Folia
Bukkit.getGlobalRegionScheduler().runNow(this, (e) -> System.out.println("PluginServer test"));
```
