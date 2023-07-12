package de.igelstudios.game.events;

import de.igelstudios.game.GameManager;
import de.igelstudios.game.Main;
import de.igelstudios.game.ServerInit;

public class SpeedEvent extends Event{

    @Override
    public int getTime() {
        return 1000;
    }

    @Override
    public String getName() {
        return "event.speed";
    }

    @Override
    public void execute() {
        ServerInit.getManager().setSpeed(GameManager.DEFAULT_SPEED / 2);
    }


}
