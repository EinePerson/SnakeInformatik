package de.igelstudios.game.events;

import de.igelstudios.game.map.Map;
import de.igelstudios.game.map.MapObject;

import java.util.Random;

public class BlockEvent extends Event{

    private boolean generated = false;
    @Override
    public int getTime() {
        generated = false;
        return 1500;
    }

    @Override
    public String getName() {
        return "blockevent";
    }

    @Override
    public void execute() {
        if(generated){
            return;
        }
        Map.getInstance().clear();
        for (int i = 0; i < Map.getInstance().getSizeX() * Map.getInstance().getSizeY() / 30; i++) {
            int x = new Random().nextInt(0, Map.getInstance().getSizeX());
            int y = new Random().nextInt(0, Map.getInstance().getSizeY());
            Map.getInstance().getMapObjects().add(new MapObject(x, y));
        }
        Map.getInstance().update2C();
        generated = true;
    }
}
