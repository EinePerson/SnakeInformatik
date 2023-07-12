package de.igelstudios.game.map;

import de.igelstudios.ClientMain;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.common.scene.SceneObject;

/**
 * @author Laurin
 */
public class MapObject {
    private int x,y;
    private final SceneObject obj;

    public MapObject(int x, int y){
        this.x = x;
        this.y = y;
        if(ClientMain.getInstance() != null) {
            obj = new SceneObject().setTex(ObjectBatch.pool.getID("test2.png")).setUv(1, 0).setPos(x,y);
            Renderer.get().render(obj, x, y);
        }else obj = new SceneObject().setPos(x,y);
    }

    public void remove(){
        obj.remove();
    }

    public void delete(){
        obj.remove();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
