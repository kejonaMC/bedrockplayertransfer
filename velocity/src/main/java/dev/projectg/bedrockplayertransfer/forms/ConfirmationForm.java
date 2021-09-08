package dev.projectg.bedrockplayertransfer.forms;

import com.velocitypowered.api.proxy.Player;
import dev.projectg.bedrockplayermanager.CheckJavaOrFloodPlayer;
import dev.projectg.bedrockplayermanager.TransferPacketBuilder;
import dev.projectg.bedrockplayertransfer.BedrockPlayerTransfer;
import net.kyori.adventure.text.Component;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.util.Optional;
import java.util.UUID;

public class ConfirmationForm {
    public void confirmation(UUID target, String ip, int port) {

        boolean isFloodgatePlayer = CheckJavaOrFloodPlayer.isFloodgatePlayer(target);
        if (isFloodgatePlayer) {
            FloodgatePlayer fPlayer = FloodgateApi.getInstance().getPlayer(target);
            fPlayer.sendForm(
                    SimpleForm.builder()
                            .title("Would you like to connect to " + ip + "?")
                            .button("Yes")
                            .button("No")
                            .responseHandler((form, responseData) -> {
                                SimpleFormResponse response = form.parseResponse(responseData);
                                if (!response.isCorrect()) {
                                    // player closed the form or returned invalid info (see FormResponse)
                                    return;
                                }
                                if (response.getClickedButtonId() == 0) {
                                    // clicked Yes
                                    new TransferPacketBuilder().createPacket(ip,port,target);

                                    if (response.getClickedButtonId() == 1) {
                                        // clicked No
                                        Optional<Player> getplayer = BedrockPlayerTransfer.getPlugin().getProxyServer().getPlayer(target);
                                        getplayer.ifPresent(player -> player.sendMessage(Component.text("You declined server transferring")));
                                    }
                                }
                            }));
        }
    }
}