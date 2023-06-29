package de.igelstudios.game.map;

import de.igelstudios.igelengine.common.scene.SceneObject;

public class MapObject extends SceneObject {
    private int x,y;

    public MapObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
