package dev.projectg.bedrockplayertransfer;


import dev.projectg.bedrockplayerManager.Configuration;
import dev.projectg.bedrockplayertransfer.command.TransferCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeecordBedrockPlayerTransfer extends Plugin {

    private static BungeecordBedrockPlayerTransfer plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Configuration config = Configuration.create(this.getDataFolder().toPath());
        this.getProxy().getPluginManager().registerCommand(this, new TransferCommand());
    }
    public static BungeecordBedrockPlayerTransfer getPlugin() {
        return plugin;
    }
}