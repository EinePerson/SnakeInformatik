package de.igelstudios.game.snake;

import de.igelstudios.ClientMain;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSegment> segments;
    private Direction queued;
    private Direction direction;
    private boolean dirty;

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
                    dirty = true;
                }
                segments.get(i).setPos(direction.move());
                if(ClientMain.getInstance() != null) {
                    ClientMain.getInstance().getEngine().getScene().getCam().getPos().x = segments.get(i).getPos().x;
                    ClientMain.getInstance().getEngine().getScene().getCam().getPos().y = segments.get(i).getPos().y;
                    ClientMain.getInstance().getEngine().getScene().getCam().adjust();
                }
            }else segments.get(i).setPos(segments.get(i - 1).getPos());

        }
    }

    public boolean dirty() {
        if(dirty) {
            dirty = false;
            return true;
        }
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public static Creator create(){
        return new Creator();
    }

    public void move(int x,int y){
        segments.forEach((snakeSegment -> snakeSegment.move(x,y)));
        if(ClientMain.getInstance() != null) {
            ClientMain.getInstance().getEngine().getScene().getCam().getPos().x = segments.get(0).getPos().x;
            ClientMain.getInstance().getEngine().getScene().getCam().getPos().y = segments.get(0).getPos().y;
            ClientMain.getInstance().getEngine().getScene().getCam().adjust();
        }
    }

    public static class Creator{
        private Creator(){
            segments = new ArrayList<>();
        }
        private Direction dir;
        private List<SnakeSegment> segments;

        public void setDir(Direction dir) {
            this.dir = dir;
        }

        public void add(Vector2f pos){
            segments.add(new SnakeSegment(pos));
        }
        public Snake make(){
            Snake snake = new Snake();
            if(dir == null)dir = Direction.RIGHT;
            snake.direction = this.dir;
            snake.segments = this.segments;
            snake.dirty = true;
            return snake;
        }

    }
}
