package de.igelstudios.game.client;

import de.igelstudios.ClientMain;
import de.igelstudios.game.Main;
import de.igelstudios.game.snake.Direction;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.igelengine.client.keys.HIDInput;
import de.igelstudios.igelengine.client.keys.KeyHandler;
import de.igelstudios.igelengine.client.keys.KeyListener;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class KeyInput implements KeyListener {
    private static KeyInput instance;

    private KeyInput(){

    }

    public void init(HIDInput input){
        input.registerKey(GLFW_KEY_W,"up");
        input.registerKey(GLFW_KEY_A,"left");
        input.registerKey(GLFW_KEY_S,"down");
        input.registerKey(GLFW_KEY_D,"right");
        input.registerKeyListener(this);
    }

    public static KeyInput get(){
        if(instance == null)instance = new KeyInput();
        return instance;
    }

    @KeyHandler("up")
    public void up(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake.getDirection() != Direction.UP && snake.getDirection() != Direction.DOWN)snake.queue(Direction.UP);
    }

    @KeyHandler("left")
    public void left(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake.getDirection() != Direction.LEFT && snake.getDirection() != Direction.RIGHT)snake.queue(Direction.LEFT);
    }

    @KeyHandler("down")
    public void down(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake.getDirection() != Direction.DOWN && snake.getDirection() != Direction.UP)snake.queue(Direction.DOWN);
    }

    @KeyHandler("right")
    public void right(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake.getDirection() != Direction.RIGHT && snake.getDirection() != Direction.LEFT)snake.queue(Direction.RIGHT);
    }
}
