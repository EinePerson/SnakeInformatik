package de.igelstudios.game.snake;

import de.igelstudios.ClientMain;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.common.scene.SceneObject;
import org.joml.Vector2f;

public class SnakeSegment {
    private Vector2f pos;
    private SceneObject obj;

    SnakeSegment(Vector2f pos){
        this.pos = pos;
        if(ClientMain.getInstance() != null){
            System.out.println(Thread.currentThread().getName());
            obj = new SceneObject().setTex(ObjectBatch.pool.getID("test2.png")).setUv(0,0).setPos(pos.x,pos.y);
        }
        else obj = new SceneObject().setPos(pos.x, pos.y);
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
        obj.setPos(pos.x,pos.y);
    }

    public void move(int x,int y){
        pos.x += x;
        pos.y += y;
        obj.setPos(pos.x,pos.y);
    }

    public void move(Vector2f vec){
        pos.x += vec.x;
        pos.y += vec.y;
        obj.setPos(pos.x,pos.y);
    }

    public void moveTo(int x, int y){
        pos.x = x;
        pos.y = y;
        obj.setPos(x, y);
    }
}
