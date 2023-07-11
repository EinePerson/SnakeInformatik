package de.igelstudios.game.networking;

import de.igelstudios.game.map.Map;
import de.igelstudios.game.map.MapObject;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;

public class S2CMapObjects {
    public static void receive(Client client, PacketByteBuf buf) {
        Map.getInstance().removeObjects();
        int i = buf.readByte();
        System.out.println(i);
        for (int j = 0; j < i; j++) {
            Map.getInstance().getMapObjects().add(new MapObject(buf.readByte(),buf.readByte()));
        }
    }
}
