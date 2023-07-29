package de.igelstudios.game;

import de.igelstudios.ClientMain;
import de.igelstudios.game.client.KeyInput;
import de.igelstudios.game.map.Map;
import de.igelstudios.game.menus.DeathMenu;
import de.igelstudios.game.menus.MainMenu;
import de.igelstudios.game.networking.CNet;
import de.igelstudios.game.snake.Direction;
import de.igelstudios.game.snake.Snake;
import de.igelstudios.game.util.ServerStarter;
import de.igelstudios.igelengine.client.al.Sound;
import de.igelstudios.igelengine.client.al.SoundBuffer;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.client.gui.GUIManager;
import de.igelstudios.igelengine.client.lang.ClientConfig;
import de.igelstudios.igelengine.common.networking.ErrorHandler;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.networking.client.ClientConnectListener;
import de.igelstudios.igelengine.common.scene.SceneObject;
import de.igelstudios.igelengine.common.startup.EngineInitializer;
import de.igelstudios.igelengine.common.startup.KeyInitializer;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.net.SocketException;
import java.util.UUID;

public class Main implements EngineInitializer,ClientConnectListener{
    private static UUID uuid;
    private static GameManager manager;
    private static Client client;

    public static UUID getUuid() {
        return uuid;
    }

    public static void setUuid(UUID uuid) {
        Main.uuid = uuid;
    }

    public static GameManager getManager() {
        return manager;
    }

    @Override
    public void registerKeys(KeyInitializer keyInitializer) {
        KeyInput.get().init(keyInitializer);
    }

    @Override
    public void onInitialize() {
        ServerStarter.create();
        System.out.println(ObjectBatch.pool.get("test2.png").getID());
        System.out.println(ObjectBatch.pool.get("test.png").getID());
        Common.init();
        //new MainMenu();
        manager = new GameManager();
        CNet.register();
        ClientMain.getInstance().getEngine().addTickable(manager);
        //connect("localhost");
        GUIManager.setGUI(new MainMenu());
        new Sound(-1,false,new Vector3f(40,22,0), SoundBuffer.get("ambient.ogg")).play();
    }

    @Override
    public void onEnd() {

    }

    public static void connect(String address){
        new Client(address,new SimpleHandler()).start();
        PacketByteBuf buf = PacketByteBuf.create();
        buf.writeString((String) ClientConfig.getConfig().get("name","User"));
        Client.send2Server("Login", buf);
        GUIManager.removeGui();
    }

    @Override
    public void playerConnect() {

    }

    @Override
    public void playerDisConnect() {
        GUIManager.setGUI(new MainMenu());
        Main.getManager().clear();
        Map.getInstance().removeFood();
        Map.getInstance().removeObjects();
    }

    public static class SimpleHandler implements ErrorHandler{

        @Override
        public void handle(Throwable cause) {
            cause.printStackTrace();
        }
    }
}
