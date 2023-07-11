package de.igelstudios.game.networking;

import de.igelstudios.game.ServerInit;
import de.igelstudios.game.snake.Direction;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.ClientNet;

public class C2SDirection {
    public static void receive(ClientNet sender, PacketByteBuf buf) {
        if(ServerInit.getManager().getSnake(sender.getUUID()) != null) ServerInit.getManager().getSnake(sender.getUUID()).queue(buf.readEnum(Direction.class));
    }
}
