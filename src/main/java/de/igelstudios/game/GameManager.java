package de.igelstudios.game;

import de.igelstudios.game.snake.Snake;
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
        i++;
        Set<UUID> v = snakes.keySet();
    }

    public void addSnake(UUID uuid,Snake snake){
        snakes.put(uuid,snake);
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


}
