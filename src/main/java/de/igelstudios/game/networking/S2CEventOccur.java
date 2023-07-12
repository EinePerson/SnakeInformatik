package de.igelstudios.game.networking;

import de.igelstudios.game.GameManager;
import de.igelstudios.game.Main;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.lang.Text;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.networking.client.ClientHandler;

public class S2CEventOccur {
    public static void receive(Client client, PacketByteBuf buf) {
        String s = buf.readString();
        if(s.equals("event.speed")) Main.getManager().setSpeed(GameManager.DEFAULT_SPEED / 2);
        Renderer.get().render(Text.translatable(s),40,30,100);
    }
}
