package de.igelstudios.game.networking;

import de.igelstudios.ClientMain;
import de.igelstudios.game.GameManager;
import de.igelstudios.game.Main;
import de.igelstudios.game.ServerInit;
import de.igelstudios.game.map.Map;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.ClientNet;
import org.joml.Vector2f;

import java.util.UUID;

public class C2SLogIn{
    public static void receive(ClientNet sender, PacketByteBuf buf) {
        Snake.Creator snake = Snake.create();
        snake.add(new Vector2f(1.0f,1.0f));
        ServerInit.getManager().addSnake(sender.getUUID(),snake.make());
        Map.getInstance().update2C(sender.getUUID());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ServerInit.getSystem().update(sender.getUUID());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GameManager.send2Player(ServerInit.getManager().getSnakes(), sender.getUUID());
    }
}
