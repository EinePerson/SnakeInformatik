package de.igelstudios.game.networking;

import de.igelstudios.game.Main;
import de.igelstudios.game.snake.Direction;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.scene.SceneObject;
import org.joml.Vector2f;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class S2CSnakes {
    public static void receive(Client client, PacketByteBuf buf) {
        UUID uuid = buf.readUUID();
        Main.setUuid(uuid);
        Map<UUID, Snake> snakes = new HashMap<>();
        int k = buf.readByte();
        for (byte i = 0; i < k; i++) {
            UUID pUUID = buf.readUUID();
            Main.getManager().remove(pUUID);
            Snake.Creator snake = Snake.create();
            snake.setDir(buf.readEnum(Direction.class));
            int l = buf.readByte();
            for (byte j = 0; j < l; j++) {
                snake.add(buf.readVec2f());
            }
            snakes.put(pUUID, snake.make());
        }
        Main.getManager().add(snakes);
    }
}
