package de.igelstudios.game.networking;

import de.igelstudios.game.Main;
import de.igelstudios.game.snake.Direction;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class S2CSnakes {
    public static void receive(Client client, PacketByteBuf buf) {
        UUID uuid = buf.readUUID();
        Main.setUuid(uuid);
        Map<UUID, Snake> snakes = new HashMap<>();
        for (byte i = 0; i < buf.readByte(); i++) {
            UUID pUUID = buf.readUUID();
            Snake.Creator snake = Snake.create();
            snake.setDir(buf.readEnum(Direction.class));
            for (byte j = 0; j < buf.readByte(); j++) {
                snake.add(buf.readVec2f());
            }
            snakes.put(pUUID,snake.make());
        }
        Main.getManager().add(snakes);
        System.out.println("B");
    }
}
