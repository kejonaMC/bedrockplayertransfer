package dev.projectg.bedrockplayertransfer;

import dev.projectg.bedrockplayerManager.Configuration;
import dev.projectg.bedrockplayertransfer.command.TransferCommand;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public final class SpigotBedrockPlayerTransfer extends JavaPlugin {

    public boolean loadSpigot = false;
    public static SpigotBedrockPlayerTransfer plugin;
    public Logger logger;
    public List<String> ipAdresses;

    @Override
    public void onEnable() {
        loadSpigot = true;
        plugin = this;
        logger = getLogger();
        Configuration config = Configuration.create(this.getDataFolder().toPath());
        ipAdresses = config.getipAdressList();
        Objects.requireNonNull(this.getCommand("transfer")).setExecutor(new TransferCommand());
        // Plugin startup logic

    }
    public static SpigotBedrockPlayerTransfer getPlugin() {
        return plugin;
    }
}