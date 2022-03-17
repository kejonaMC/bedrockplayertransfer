package dev.projectg.bedrockplayertransfer;

import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

public class FloodgateHandler {

    /**
     * Determines if a player is from Bedrock
     * @param uuid the UUID to determine
     * @return true if the player is from Bedrock
     */
    public static boolean isFloodgatePlayer(UUID uuid) {
        return FloodgateApi.getInstance().isFloodgatePlayer(uuid);
    }
}