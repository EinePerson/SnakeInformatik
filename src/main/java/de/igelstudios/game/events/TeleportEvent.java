package de.igelstudios.game.events;

import de.igelstudios.game.Main;
import de.igelstudios.game.snake.Snake;

import java.util.Random;

public class TeleportEvent extends Event{
    private boolean teleported;
    @Override
    public int getTime() {
        teleported = false;
        return 500;
    }

    @Override
    public String getName() {
        return "teleportevent";
    }

    @Override
    public void execute() {
        //TODO apply map size
        if (teleported){
            return;
        }
        for (Snake snake : Main.getManager().getSnakes().values()) {
            int x = new Random().nextInt(-40,41);
            int y = new Random().nextInt(-23,23);
            snake.getSegments().forEach(snakeSegment -> snakeSegment.move(x, y));

        }
    }
}
