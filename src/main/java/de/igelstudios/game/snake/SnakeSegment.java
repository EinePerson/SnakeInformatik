package de.igelstudios.game.snake;

import org.joml.Vector2f;

public class SnakeSegment {
    private Vector2f pos;

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void move(int x,int y){
        pos.x += x;
        pos.y += y;
    }

    public void move(Vector2f vec){
        pos.x += vec.x;
        pos.y += vec.y;
    }
}
