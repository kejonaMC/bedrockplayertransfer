package dev.projectg.bedrockplayermanager;

import com.nukkitx.protocol.bedrock.packet.TransferPacket;
import org.geysermc.connector.GeyserConnector;
import org.geysermc.connector.network.session.GeyserSession;

import java.util.UUID;

public class TransferPacketBuilder {

    public void createPacket(String ip, int port, UUID uuid ){
        GeyserSession session = GeyserConnector.getInstance().getPlayerByUuid(uuid);
        TransferPacket packet = new TransferPacket();
        packet.setAddress(ip);
        packet.setPort(port);
        session.sendUpstreamPacket(packet);

    }
}