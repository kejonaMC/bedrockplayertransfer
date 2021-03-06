package dev.projectg.bedrockplayertransfer;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.projectg.bedrockplayertransfer.command.TransferCommand;

@Plugin(id = "bedrockplayertransfer", name = "BedrockPlayerTransfer", version = "2.0", description = "Transfer a Bedrock player to an other server.", authors = {"Jens, Konicai"},
            dependencies = {@Dependency(id = "geyser")})

    public class VelocityBedrockPlayerTransfer {

    public ProxyServer server;
    private static VelocityBedrockPlayerTransfer plugin;

    @Inject
    public VelocityBedrockPlayerTransfer(ProxyServer server) {
        VelocityBedrockPlayerTransfer.plugin = this;
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialization(final ProxyInitializeEvent event) {
        server.getCommandManager().register("transfer", new TransferCommand());
    }

    public ProxyServer getProxyServer() {
        return server;
    }

    public static VelocityBedrockPlayerTransfer getPlugin() {
        return plugin;
    }
}