package dev.projectg.bedrockplayertransfer.command;

import dev.projectg.bedrockplayertransfer.Constants;
import dev.projectg.bedrockplayertransfer.forms.BungeecordTransferForm;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TransferCommand extends Command {

    public TransferCommand() {
        super("transfer", Constants.PERMISSION);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            new BungeecordTransferForm().packetBuilder(player);
        }
    }
}