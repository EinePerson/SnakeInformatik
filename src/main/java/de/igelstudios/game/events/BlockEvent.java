package de.igelstudios.game.events;

public class BlockEvent extends Event{
    @Override
    public int getTime() {
        return 1500;
    }

    @Override
    public String getName() {
        return "blockevent";
    }

    @Override
    public void execute() {

    }
}
