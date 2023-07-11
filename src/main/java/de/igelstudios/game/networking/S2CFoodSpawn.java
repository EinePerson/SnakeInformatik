package de.igelstudios.game.networking;

import de.igelstudios.game.map.Food;
import de.igelstudios.game.map.Map;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.networking.client.ClientHandler;

public class S2CFoodSpawn {
    public static void receive(Client client, PacketByteBuf buf) {
        Map.getInstance().setFood(buf.readEnum(Food.class),buf.readByte(),buf.readByte());
    }
}
