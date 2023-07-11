package de.igelstudios.game.networking;

import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.lang.Text;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.networking.client.ClientHandler;

public class S2CEventOccur {
    public static void receive(Client client, PacketByteBuf buf) {
        Renderer.get().render(Text.translatable(buf.readString()),40,30,100);
    }
}
