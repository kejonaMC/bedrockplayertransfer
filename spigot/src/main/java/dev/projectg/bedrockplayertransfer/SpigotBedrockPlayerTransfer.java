package dev.projectg.bedrockplayertransfer;

import dev.projectg.bedrockplayertransfer.command.TransferCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class SpigotBedrockPlayerTransfer extends JavaPlugin {

    public boolean loadSpigot = false;

    public static SpigotBedrockPlayerTransfer plugin;
    public Logger logger;

    @Override
    public void onEnable() {
        loadSpigot = true;
        plugin = this;
        logger = getLogger();
        Objects.requireNonNull(this.getCommand("transfer")).setExecutor(new TransferCommand());
        // Plugin startup logic

    }
    public static SpigotBedrockPlayerTransfer getPlugin() {
        return plugin;
    }
}