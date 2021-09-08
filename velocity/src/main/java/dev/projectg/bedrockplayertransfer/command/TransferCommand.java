package dev.projectg.bedrockplayertransfer.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import dev.projectg.bedrockplayertransfer.forms.TransferForm;

public class TransferCommand implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        if (source instanceof Player) {
            Player player = (Player) source;
            new TransferForm().packetBuilder(player);
        }
    }
}