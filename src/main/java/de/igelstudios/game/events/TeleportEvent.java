package de.igelstudios.game.events;

import de.igelstudios.game.Main;
import de.igelstudios.game.ServerInit;
import de.igelstudios.game.map.Map;
import de.igelstudios.game.snake.Snake;
import org.joml.Vector2f;

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
        return "event.teleport";
    }

    @Override
    public void execute() {
        if (teleported){
            return;
        }
        for (Snake snake : ServerInit.getManager().getSnakes().values()) {
            for (int i = 0; i < 100; i++) {
                int x = new Random().nextInt(0, Map.getInstance().getSizeX());
                int y = new Random().nextInt(0, Map.getInstance().getSizeY());
                int deltaX = (int) (snake.getSegments().get(0).getPos().x - x);
                int deltaY = (int) (snake.getSegments().get(0).getPos().y - y);
                Snake.Creator creator = Snake.create();
                snake.getSegments().forEach(snakeSegment -> creator.add(new Vector2f(snakeSegment.getPos().x + deltaX, snakeSegment.getPos().y + deltaY)));
                if (Map.getInstance().checkCollision(creator.make())) {
                    snake.getSegments().forEach(snakeSegment -> snakeSegment.moveTo((int) (snakeSegment.getPos().x + deltaX), (int) (snakeSegment.getPos().y + deltaY)));
                    break;
                }
            }
        }
        teleported = true;
    }
}
