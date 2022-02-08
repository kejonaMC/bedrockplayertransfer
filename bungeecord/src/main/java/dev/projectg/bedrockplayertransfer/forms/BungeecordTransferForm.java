package dev.projectg.bedrockplayertransfer.forms;

import dev.projectg.bedrockplayertransfer.BungeecordBedrockPlayerTransfer;
import dev.projectg.bedrockplayerManager.CheckJavaOrFloodPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class BungeecordTransferForm {


    public void packetBuilder(ProxiedPlayer player){

        UUID uuid = player.getUniqueId();
        List<String> names = ProxyServer.getInstance().getPlayers().stream().map(ProxiedPlayer::getName).collect(Collectors.toList());
        String[] playerList = names.toArray(new String[0]);
        boolean isFloodgatePlayer = CheckJavaOrFloodPlayer.isFloodgatePlayer(uuid);
        if (isFloodgatePlayer) {
            FloodgatePlayer fPlayer = FloodgateApi.getInstance().getPlayer(uuid);
            fPlayer.sendForm(
                    CustomForm.builder()
                            .title("Transfer Player")
                            .dropdown("Select Player", playerList)
                            .input("Server IP")
                            .input("Server Port")
                            .responseHandler((form, responseData) -> {
                                CustomFormResponse response = form.parseResponse(responseData);
                                if (!response.isCorrect()) {
                                    return;
                                }
                                int clickedIndex = response.getDropdown(0);
                                String serverip = response.getInput(1);
                                int serverport = Integer.parseInt(Objects.requireNonNull(response.getInput(2)));
                                String name = names.get(clickedIndex);
                                UUID targetPlayer = BungeecordBedrockPlayerTransfer.getPlugin().getProxy().getPlayer(name).getUniqueId();
                                boolean isTargetFloodgatePlayer = CheckJavaOrFloodPlayer.isFloodgatePlayer(targetPlayer);
                                if (isTargetFloodgatePlayer) {
                                    new ConfirmationForm().confirmation(targetPlayer,serverip,serverport);
                                }
                                else{
                                    player.sendMessage(new TextComponent("You can only transfer bedrock players"));
                                }
                            }));
        }
    }
}