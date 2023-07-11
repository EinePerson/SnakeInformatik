package de.igelstudios.game;

import ch.qos.logback.core.net.server.Client;
import ch.qos.logback.core.net.server.ServerListener;
import de.igelstudios.ServerMain;
import de.igelstudios.game.map.FoodObject;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.game.snake.SnakeSegment;
import de.igelstudios.igelengine.common.networking.Package;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.ClientNet;
import de.igelstudios.igelengine.common.networking.server.ConnectionListener;
import de.igelstudios.igelengine.common.networking.server.Server;
import de.igelstudios.igelengine.common.util.Tickable;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.io.IOException;
import java.util.*;

public class GameManager implements Tickable, ConnectionListener {
    private Map<UUID,Snake> snakes;
    public static final int DEFAULT_SPEED = 10;
    private static final int DEFAULT_FOOD_SPAWN = 100;
    private int speed;
    private int i;
    private int j;

    public GameManager(){
        speed = DEFAULT_SPEED;
        snakes = new HashMap<>();
        new de.igelstudios.game.map.Map(30,30);
    }

    @Override
    public void tick() {
        if(i >= speed){
            snakes.values().forEach(Snake::move);
            i =- speed;
        }

        i++;
        if(ServerMain.getInstance() == null)return;

        Map<UUID, Snake> netSnakes = new HashMap<>();
        snakes.forEach(((uuid, snake) -> {
            if (snake.dirty()) netSnakes.put(uuid, snake);
        }));
        if (!snakes.isEmpty() && !netSnakes.isEmpty()) {
            for (UUID uuid : ServerMain.getInstance().getEngine().getPlayers().keySet()) {
                send2Player(netSnakes,uuid);
            }
        }

        List<Snake> snakeList = snakes.values().stream().toList();
        int k = 0;
        List<UUID> toRemove = new ArrayList<>();
        for (UUID uuid : snakes.keySet()) {
            int j = de.igelstudios.game.map.Map.getInstance().checkCollision(k,snakeList);
            if(j != 0) {
                snakes.keySet().forEach(uuid1 -> sendDeath2Player(uuid,uuid1));
                toRemove.add(uuid);
            }
            k++;
        }

        toRemove.forEach(snakes::remove);

        List<FoodObject> foods = new ArrayList<>();
        for (UUID uuid : snakes.keySet()) {
            for (FoodObject foodObject : de.igelstudios.game.map.Map.getInstance().getFoodObjects()) {
                Vector2f pos = snakes.get(uuid).getSegments().get(0).getPos();
                if(foodObject.getX() == ((int) pos.x) && foodObject.getY() == ((int) pos.y)){
                    snakes.get(uuid).addFood(foodObject.getFood().getFood());
                    foods.add(foodObject);
                    break;
                }
            }
        }

        foods.forEach(foodObject -> {
            snakes.keySet().forEach(uuid -> send2Player(uuid,foodObject.getX(), foodObject.getY()));
            de.igelstudios.game.map.Map.getInstance().removeFood(foodObject);
        });

        if(j >= DEFAULT_FOOD_SPAWN){
            FoodObject obj = de.igelstudios.game.map.Map.getInstance().spawnFood();
            j -= DEFAULT_FOOD_SPAWN;
            snakes.keySet().forEach(uuid -> send2Player(uuid,obj));
        }
        j++;
    }

    public static void sendDeath2Player(UUID death,UUID receiver){
        PacketByteBuf buf = PacketByteBuf.create();
        buf.writeUUID(death);
        Server.send2Client(ServerMain.getInstance().getEngine().get(receiver),"Die",buf);
    }

    public static void send2Player(Map<UUID, Snake> snakes,UUID pUUID){
        PacketByteBuf buf = PacketByteBuf.create();
        buf.writeUUID(pUUID);
        buf.writeByte(snakes.size());
        for (UUID uuid : snakes.keySet()) {
            buf.writeUUID(uuid);
            buf.writeEnum(snakes.get(uuid).getDirection());
            buf.writeByte(snakes.get(uuid).getSegments().size());
            snakes.get(uuid).getSegments().forEach(snakeSegment ->{
                buf.writeVec2f(snakeSegment.getPos());
            });
        }
        Server.send2Client(ServerMain.getInstance().getEngine().get(pUUID),"Snakes",buf);
    }

    public static void send2Player(UUID uuid,int x,int y){
        PacketByteBuf buf = PacketByteBuf.create();
        buf.writeByte(x);
        buf.writeByte(y);
        Server.send2Client(ServerMain.getInstance().getEngine().get(uuid),"FoodR",buf);
    }
    public static void send2Player(UUID uuid,FoodObject obj){
        PacketByteBuf buf = PacketByteBuf.create();
        buf.writeEnum(obj.getFood());
        buf.writeByte(obj.getX());
        buf.writeByte(obj.getY());
        Server.send2Client(ServerMain.getInstance().getEngine().get(uuid),"FoodS",buf);
    }

    public void addSnake(UUID uuid,Snake snake){
        snakes.put(uuid,snake);
    }

    public Snake getSnake(UUID uuid){
        return snakes.get(uuid);
    }

    public void replace(UUID uuid,Snake snake){
        if(snakes.get(uuid) != null) snakes.get(uuid).getSegments().forEach(SnakeSegment::remove);
        snakes.put(uuid,snake);
    }

    public void remove(UUID uuid){
        if(snakes.get(uuid) != null) snakes.get(uuid).getSegments().forEach(SnakeSegment::remove);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void resetSpeed(){
        setSpeed(DEFAULT_SPEED);
    }

    public Map<UUID, Snake> getSnakes() {
        return snakes;
    }

    public void add(Map<UUID,Snake> snakes){
        snakes.forEach(this::addSnake);
    }

    @Override
    public void playerConnect(ClientNet player) {

    }

    @Override
    public void playerDisConnect(ClientNet player) {
        ServerInit.getManager().getSnakes().remove(player.getUUID());
    }

    public void clear(){
        snakes.values().forEach(Snake::remove);
        snakes.clear();
    }
}
