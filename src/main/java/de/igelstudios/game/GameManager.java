package de.igelstudios.game;

import de.igelstudios.ServerMain;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.game.snake.SnakeSegment;
import de.igelstudios.igelengine.common.networking.Package;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.server.Server;
import de.igelstudios.igelengine.common.util.Tickable;

import java.util.*;

public class GameManager implements Tickable {
    private Map<UUID,Snake> snakes;
    public static final int DEFAULT_SPEED = 20;
    private int speed;
    private int i;

    public GameManager(){
        snakes = new HashMap<>();
    }

    @Override
    public void tick() {
        if(i >= speed){
            snakes.values().forEach(Snake::move);
            i = 0;
        }
        if(ServerMain.getInstance()!= null) {
            Map<UUID, Snake> netSnakes = new HashMap<>();
            snakes.forEach(((uuid, snake) -> {
                if (snake.dirty()) netSnakes.put(uuid, snake);
            }));
            if (!snakes.isEmpty() && !netSnakes.isEmpty()) {
                for (UUID uuid : ServerMain.getInstance().getEngine().getPlayers().keySet()) {
                    PacketByteBuf buf = PacketByteBuf.create();
                    buf.writeUUID(uuid);
                    buf.writeByte(netSnakes.size());
                    for (UUID uuid1 : netSnakes.keySet()) {
                        buf.writeUUID(uuid1);
                        buf.writeEnum(netSnakes.get(uuid1).getDirection());
                        buf.writeByte(netSnakes.get(uuid1).getSegments().size());
                        netSnakes.get(uuid1).getSegments().forEach(snakeSegment -> buf.writeVec2f(snakeSegment.getPos()));
                    }
                    Server.send2Client(ServerMain.getInstance().getEngine().get(uuid),"Snakes",buf);
                }
            }
        }
        i++;
    }

    public void addSnake(UUID uuid,Snake snake){
        snakes.put(uuid,snake);
        System.out.println(this.snakes);
    }

    public Snake getSnake(UUID uuid){
        return snakes.get(uuid);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void resetSpeed(){
        setSpeed(DEFAULT_SPEED);
    }

    public Map<UUID, Snake> getSnakes() {
        return new HashMap<>(snakes);
    }

    public void add(Map<UUID,Snake> snakes){
        snakes.forEach(this::addSnake);
        System.out.println(this.snakes);
    }
}
