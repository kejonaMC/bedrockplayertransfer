package dev.projectg.bedrockplayertransfer;

import com.nukkitx.protocol.bedrock.packet.TransferPacket;
import org.geysermc.geyser.GeyserImpl;
import org.geysermc.geyser.session.GeyserSession;

import java.util.Objects;
import java.util.UUID;

public class TransferPacketBuilder {

    public void sendPacket(String ip, int port, UUID uuid ){
        GeyserSession session = GeyserImpl.getInstance().connectionByUuid(uuid);
        Objects.requireNonNull(session);
        TransferPacket packet = new TransferPacket();
        packet.setAddress(ip);
        packet.setPort(port);
        session.sendUpstreamPacket(packet);
    }
}