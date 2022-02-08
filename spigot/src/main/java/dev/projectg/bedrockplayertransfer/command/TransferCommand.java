package dev.projectg.bedrockplayertransfer.command;

import dev.projectg.bedrockplayertransfer.forms.SpigotTransferForm;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TransferCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "permission.command.error");
            return true;
        }
        Player player = (Player) sender;
        try {
            if (command.getName().equalsIgnoreCase("transfer") && player.hasPermission("bedrockplayertransfer.transfer")) {
                new SpigotTransferForm().packetBuilder(player);
            }
        } catch (Exception e) {
            sender.sendMessage("Sorry something went wrong with the transfer form");
        }
        return false;
    }
}