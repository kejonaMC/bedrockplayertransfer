package dev.projectg.bedrockplayertransfer.forms;

import dev.projectg.bedrockplayermanager.CheckJavaOrFloodPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransferForm {

    public void packetBuilder(Player player){

        UUID uuid = player.getUniqueId();
        List<String> names = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
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
                                UUID targetPlayer = Objects.requireNonNull(Bukkit.getPlayer(name)).getUniqueId();
                                boolean isTargetFloodgatePlayer = CheckJavaOrFloodPlayer.isFloodgatePlayer(targetPlayer);
                                if (isTargetFloodgatePlayer) {
                                    new ConfirmationForm().confirmation(targetPlayer,serverip,serverport);
                                }
                                else{
                                    player.sendMessage("You can only transfer bedrock players");
                                }
                            }));
        }
    }
}