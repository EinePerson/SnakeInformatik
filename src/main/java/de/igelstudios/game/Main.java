package de.igelstudios.game;

import de.igelstudios.ClientMain;
import de.igelstudios.game.client.KeyInput;
import de.igelstudios.game.menues.MainMenue;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.common.networking.ErrorHandler;
import de.igelstudios.igelengine.common.scene.SceneObject;

import java.util.UUID;

public class Main {
    private static UUID uuid;
    private static GameManager manager;

    public static void main(String[] args){
        ClientMain main = new ClientMain(Main.class);

        //.get().init(ClientMain.getInstance().getEngine().getInput());
        new MainMenue();
        manager = new GameManager();
        main.getEngine().addTickable(manager);
        main.start();
    }

    public static UUID getUuid() {
        return uuid;
    }

    public static GameManager getManager() {
        return manager;
    }

    public static class SimpleHandler implements ErrorHandler{

        @Override
        public void handle(Throwable cause) {
            cause.printStackTrace();
        }
    }
}
