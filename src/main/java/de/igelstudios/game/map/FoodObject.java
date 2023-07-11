package de.igelstudios.game.map;

import de.igelstudios.ClientMain;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.common.scene.SceneObject;

public class FoodObject {
    private Food food;
    private final SceneObject obj;

    public FoodObject(Food food,int x,int y){
        this.food = food;
        if(ClientMain.getInstance() != null) {
            obj = new SceneObject().setTex(ObjectBatch.pool.getID(Food.TEXTURE_FILE)).setUv(food.getU(), food.getV());
            Renderer.get().render(obj, x, y);
        }else obj = new SceneObject().setPos(x,y);
    }

    public void remove(){
        obj.remove();
    }

    public int getX(){
        return (int) obj.getPos().x;
    }

    public int getY(){
        return (int) obj.getPos().y;
    }

    public Food getFood() {
        return food;
    }
}
