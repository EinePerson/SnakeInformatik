package de.igelstudios.game.map;

import de.igelstudios.game.snake.Snake;
import de.igelstudios.game.snake.SnakeSegment;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private int sizeX;
    private int sizeY;

    private List<MapObject> mapObjects;

    private static Map instance;

    public Map(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        mapObjects = new ArrayList<>();
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
        if(snake.getSegments().get(0).getPos().x < 0 && snake.getSegments().get(0).getPos().x > sizeX && snake.getSegments().get(0).getPos().y < 0
                && snake.getSegments().get(0).getPos().y > sizeY){
            return true;
        }
        return b;
    }

    public int checkCollision(int i, List<Snake> snakes){
        if(checkCollision(snakes.get(i))){
            return 2;
        }
        List<Vector2i> collisions = new ArrayList<>();
        snakes.forEach(snake -> snake.getSegments().forEach(snakeSegment -> collisions.add(new Vector2i((int) snakeSegment.getPos().x, (int) snakeSegment.getPos().y))));
        return checkCollision(snakes.get(i), collisions)?1:0;
    }

    public List<MapObject> getMapObjects() {
        return mapObjects;
    }
}
