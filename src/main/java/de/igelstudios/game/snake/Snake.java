package de.igelstudios.game.snake;

import de.igelstudios.ClientMain;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.scene.SceneObject;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final int FOOD_TO_EXTENSION = 100;
    private List<SnakeSegment> segments;
    private Direction queued;
    private Direction direction;
    private boolean dirty;
    private int food;
    private boolean queuedSeg;

    public void queue(Direction direction){
        this.direction = direction;
        dirty = true;
    }

    public Snake(){
        segments = new ArrayList<>();
    }

    public List<SnakeSegment> getSegments() {
        return segments;
    }

    public void move(){
        for (int i = segments.size() - 1; i >= 0; i--) {
            if(i == 0 || (i == segments.size() - 1 && queuedSeg)){
                if(i == segments.size() - 1 && queuedSeg){
                    segments.add(new SnakeSegment(segments.get(i).getPos()));
                    if(i != 0) segments.get(i).moveTo(segments.get(i - 1).getPos());
                    queuedSeg = false;
                    dirty = true;
                }
                if(i == 0) {
                    if (queued != null) {
                        direction = queued;
                        queued = null;
                        dirty = true;
                    }
                    segments.get(i).move(direction.move());
                }
            }else segments.get(i).moveTo(segments.get(i - 1).getPos());

        }
    }

    public void addFood(int food){
        this.food += food;
        if(this.food >= FOOD_TO_EXTENSION){
            this.food -= FOOD_TO_EXTENSION;
            //this.food = this.food - FOOD_TO_EXTENSION;
            this.queuedSeg = true;
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

    public void remove(){
        segments.forEach(SnakeSegment::remove);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "segments=" + segments +
                ", queued=" + queued +
                ", direction=" + direction +
                ", dirty=" + dirty +
                '}';
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
            if(!(segments.size() > 0))throw new IllegalArgumentException("The amount of segments in a snake has to be greater 0");
            Snake snake = new Snake();
            if(dir == null)dir = Direction.RIGHT;
            snake.direction = this.dir;
            snake.segments = this.segments;
            snake.dirty = true;
            return snake;
        }

    }
}
