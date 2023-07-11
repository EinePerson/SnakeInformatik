package de.igelstudios.game.networking;

import de.igelstudios.game.map.Map;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;

public class S2CFoodRemove {
    public static void receive(Client client, PacketByteBuf buf) {
        Map.getInstance().removeFood(buf.readByte(),buf.readByte());
    }
}
