package de.igelstudios.game.snake;

import de.igelstudios.ClientMain;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSegment> segments;
    private Direction queued;
    private Direction direction;

    public void queue(Direction direction){
        this.direction = direction;
    }

    public Snake(){
        segments = new ArrayList<>();
    }

    public List<SnakeSegment> getSegments() {
        return segments;
    }

    public void move(){
        for (int i = segments.size() - 1; i >= 0; i--) {
            if(i == 0){
                if(queued != null){
                    direction = queued;
                    queued = null;
                }
                segments.get(i).setPos(direction.move());
                ClientMain.getInstance().getEngine().getScene().getCam().getPos().x = segments.get(i).getPos().x;
                ClientMain.getInstance().getEngine().getScene().getCam().getPos().y = segments.get(i).getPos().y;
                ClientMain.getInstance().getEngine().getScene().getCam().adjust();
            }else segments.get(i).setPos(segments.get(i - 1).getPos());

        }
    }

    public Direction getDirection() {
        return direction;
    }
}
