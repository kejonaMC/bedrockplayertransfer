package dev.projectg.bedrockplayertransfer;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.projectg.bedrockplayertransfer.command.TransferCommand;

@Plugin(id = "bedrockpackettransfer", name = "BedrockPacketTransfer", version = "1.0", description = "Transfer a Bedrock player to an other server.", authors = {"ProjectG"},
            dependencies = {@Dependency(id = "geyser")})

    public class BedrockPlayerTransfer {

    public ProxyServer server;
    private static BedrockPlayerTransfer plugin;

    @Inject
    public BedrockPlayerTransfer(ProxyServer server) {
        BedrockPlayerTransfer.plugin = this;
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialization(final ProxyInitializeEvent event) {
        server.getCommandManager().register("transfer", new TransferCommand());
    }

    public ProxyServer getProxyServer() {
        return server;
    }

    public static BedrockPlayerTransfer getPlugin() {
        return plugin;
    }
}