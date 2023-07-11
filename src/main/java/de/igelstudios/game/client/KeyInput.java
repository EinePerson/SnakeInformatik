package de.igelstudios.game.client;

import de.igelstudios.game.Main;
import de.igelstudios.game.snake.Direction;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.igelengine.client.keys.KeyHandler;
import de.igelstudios.igelengine.client.keys.KeyListener;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.startup.KeyInitializer;

import static org.lwjgl.glfw.GLFW.*;

public class KeyInput implements KeyListener {
    private static KeyInput instance;

    private KeyInput(){

    }

    public void init(KeyInitializer initializer){
        initializer.add(GLFW_KEY_W,"up");
        initializer.add(GLFW_KEY_A,"left");
        initializer.add(GLFW_KEY_S,"down");
        initializer.add(GLFW_KEY_D,"right");
        initializer.add(this);
    }

    public static KeyInput get(){
        if(instance == null)instance = new KeyInput();
        return instance;
    }

    @KeyHandler("up")
    public void up(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake == null)return;
        if(snake.getDirection() != Direction.UP && snake.getDirection() != Direction.DOWN)send(Direction.UP);
    }

    @KeyHandler("left")
    public void left(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake == null)return;
        if(snake.getDirection() != Direction.LEFT && snake.getDirection() != Direction.RIGHT)send(Direction.LEFT);
    }

    @KeyHandler("down")
    public void down(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake == null)return;
        if(snake.getDirection() != Direction.DOWN && snake.getDirection() != Direction.UP)send(Direction.DOWN);
    }

    @KeyHandler("right")
    public void right(){
        Snake snake = Main.getManager().getSnake(Main.getUuid());
        if(snake == null)return;
        if(snake.getDirection() != Direction.RIGHT && snake.getDirection() != Direction.LEFT)send(Direction.RIGHT);
    }

    public void send(Direction dir){
        PacketByteBuf buf = PacketByteBuf.create();
        buf.writeEnum(dir);
        Client.send2Server("DirectionChange",buf);
    }
}
