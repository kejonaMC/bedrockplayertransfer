package dev.projectg.bedrockplayertransfer;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.projectg.bedrockplayerManager.Configuration;
import dev.projectg.bedrockplayertransfer.command.TransferCommand;

import java.nio.file.Path;

@Plugin(id = "bedrockpackettransfer", name = "BedrockPacketTransfer", version = "1.0", description = "Transfer a Bedrock player to an other server.", authors = {"ProjectG"},
            dependencies = {@Dependency(id = "geyser")})

    public class VelocityBedrockPlayerTransfer {

    public ProxyServer server;
    private static VelocityBedrockPlayerTransfer plugin;

    @Inject
    public VelocityBedrockPlayerTransfer(ProxyServer server, @DataDirectory final Path folder) {
        VelocityBedrockPlayerTransfer.plugin = this;
        this.server = server;
        Configuration config = Configuration.create(folder.getRoot());
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