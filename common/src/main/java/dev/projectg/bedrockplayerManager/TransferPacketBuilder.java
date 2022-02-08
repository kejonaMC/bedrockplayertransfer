package dev.projectg.bedrockplayerManager;

import com.nukkitx.protocol.bedrock.packet.TransferPacket;
import org.geysermc.geyser.GeyserImpl;
import org.geysermc.geyser.session.GeyserSession;

import java.util.UUID;

public class TransferPacketBuilder {

    public void createPacket(String ip, int port, UUID uuid ){
        GeyserSession session = GeyserImpl.getInstance().connectionByUuid(uuid);
        TransferPacket packet = new TransferPacket();
        packet.setAddress(ip);
        packet.setPort(port);
        assert session != null;
        session.sendUpstreamPacket(packet);

    }
}