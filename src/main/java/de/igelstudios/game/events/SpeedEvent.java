package de.igelstudios.game.events;

import de.igelstudios.game.Main;

public class SpeedEvent extends Event{

    @Override
    public int getTime() {
        return 1000;
    }

    @Override
    public String getName() {
        return "SpeedEvent";
    }

    @Override
    public void execute() {
        Main.getManager().setSpeed(10);
    }


}
