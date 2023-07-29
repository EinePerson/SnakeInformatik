package de.igelstudios.game.snake;

import org.joml.Vector2f;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public void add(Vector2f vec){
        switch (this) {
            case DOWN -> vec.y--;
            case UP -> vec.y++;
            case LEFT -> vec.x--;
            case RIGHT -> vec.x++;
        }
    }

    public Vector2f move(){
        Vector2f vec = new Vector2f(0,0);
        switch (this) {
            case DOWN -> vec.y--;
            case UP -> vec.y++;
            case LEFT -> vec.x--;
            case RIGHT -> vec.x++;
        }
        return vec;
    }

    public int getRot(){
        return switch (this) {
            case UP -> 0;
            case LEFT -> 1;
            case DOWN -> 2;
            case RIGHT -> 3;
        };
    }
}
