package de.igelstudios.game.snake;

import de.igelstudios.ClientMain;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.common.scene.SceneObject;
import org.joml.Vector2f;

public class SnakeSegment {
    private Vector2f pos;
    private final SceneObject obj;

    SnakeSegment(Vector2f pos,int type){
        this.pos = pos;
        if(ClientMain.getInstance() != null){
            int i = switch (type) {
                case 0 -> 0;
                case 1 -> 6;
                case 2 -> 7;
                default -> throw new IllegalArgumentException("Type has to be one of 0,1,2 but is: " + type);
            };
            obj = new SceneObject().setTex(ObjectBatch.pool.getID("test2.png")).setUv(i,0);
            Renderer.get().render(obj, pos.x,pos.y);
        }
        else obj = new SceneObject().setPos(pos.x, pos.y);
    }

    public void setType(int type){
        if(ClientMain.getInstance() != null){
            int i = switch (type) {
                case 0 -> 0;
                case 1 -> 6;
                case 2 -> 7;
                default -> throw new IllegalArgumentException("Type has to be one of 0,1,2 but is: " + type);
            };
            obj.setUv(0,i);
        }
    }

    public void setDirection(Direction dir){
        if(ClientMain.getInstance() != null){
            obj.setRotation(dir.getRot());
        }
    }

    SnakeSegment(Vector2f pos){
        this(pos,0);
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

    public void moveTo(Vector2f vec){
        moveTo((int) vec.x, (int) vec.y);
    }

    public void remove(){
        obj.remove();
    }

    @Override
    public String toString() {
        return "SnakeSegment{" +
                "pos=" + pos +
                ", obj=" + obj +
                '}';
    }
}
