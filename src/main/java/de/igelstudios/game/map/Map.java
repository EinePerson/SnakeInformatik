package de.igelstudios.game.map;

import de.igelstudios.ServerMain;
import de.igelstudios.game.ServerInit;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.game.snake.SnakeSegment;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.server.Server;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * @author Laurin
 */
public class Map {
    private int sizeX;
    private int sizeY;

    private List<MapObject> mapObjects;
    private List<FoodObject> foodObjects;

    private static Map instance;

    public Map(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        mapObjects = new ArrayList<>();
        foodObjects = new ArrayList<>();
        instance = this;
    }

    public static Map getInstance(){
        return instance;
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public boolean checkCollision(Snake snake){
        List<Vector2i> positions = new ArrayList<>();
        mapObjects.forEach(mapObject -> positions.add(new Vector2i(mapObject.getX(), mapObject.getY())));
        return checkCollision(snake, positions);
    }

    public boolean checkCollision(Snake snake, List<Vector2i> positions){
        boolean b = false;
        for (SnakeSegment segment : snake.getSegments()) {
            if(positions.contains(new Vector2i((int) segment.getPos().x, (int) segment.getPos().y))){
                b = true;
                break;
            }

        }
        if(b)return true;
        return snake.getSegments().get(0).getPos().x < 0 || snake.getSegments().get(0).getPos().x > sizeX || snake.getSegments().get(0).getPos().y < 0
                || snake.getSegments().get(0).getPos().y > sizeY;
    }

    public FoodObject spawnFood(){
        FoodObject obj = new FoodObject(Food.getRandom(),new Random().nextInt(sizeX),new Random().nextInt(sizeY));
        foodObjects.add(obj);
        return obj;
    }

    /**
     * Checks the collision of a snake
     * @param i the index of the Snake
     * @param snakes the List containing every snake
     * @return 2 for collision with map,1 for collision with snake and 0 for no collision
     */
    public int checkCollision(int i, List<Snake> snakes){
        if(checkCollision(snakes.get(i))){
            return 2;
        }
        List<Vector2i> collisions = new ArrayList<>();
        for (int j = 0; j < snakes.size(); j++) {
            if(j == i)continue;
            Snake snake = snakes.get(j);
            for (int k = 0; k < snake.getSegments().size(); k++) {
                if(k == 0)continue;
                SnakeSegment snakeSegment = snake.getSegments().get(k);
                collisions.add(new Vector2i((int) snakeSegment.getPos().x, (int) snakeSegment.getPos().y));
            }
        }
        return checkCollision(snakes.get(i), collisions)?1:0;
    }

    public void setFood(Food food,int x,int y){
        foodObjects.add( new FoodObject(food,x,y));
    }

    public void removeFood(int x,int y){
        for (int i = 0; i < foodObjects.size(); i++) {
            if(foodObjects.get(i).getX() == x && foodObjects.get(i).getY() == y){
                foodObjects.get(i).remove();
                foodObjects.remove(i);
                break;
            }
        }
    }

    public void removeFood(){
        foodObjects.forEach(FoodObject::remove);
        foodObjects.clear();
    }

    public void removeObjects(){
        mapObjects.forEach(MapObject::remove);
        mapObjects.clear();
    }

    public void removeFood(FoodObject obj){
        obj.remove();
        foodObjects.remove(obj);
    }

    public List<FoodObject> getFoodObjects() {
        return foodObjects;
    }

    public List<MapObject> getMapObjects() {
        return mapObjects;
    }


    public void clear(){
        mapObjects.forEach(MapObject::delete);
        mapObjects.clear();
    }

    public void update2C(){
        ServerInit.getManager().getSnakes().keySet().forEach(Map.getInstance()::update2C);
    }

    public void update2C(UUID p){
        PacketByteBuf buf = PacketByteBuf.create();
        buf.writeByte(mapObjects.size());
        mapObjects.forEach(mapObject -> {
            buf.writeByte(mapObject.getX());
            buf.writeByte(mapObject.getY());

        });
        Server.send2Client(ServerMain.getInstance().getEngine().get(p), "MapObjs",buf);
    }
}
