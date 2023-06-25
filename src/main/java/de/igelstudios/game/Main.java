package de.igelstudios.game;

import de.igelstudios.ClientMain;
import de.igelstudios.game.client.KeyInput;
import de.igelstudios.igelengine.common.networking.ErrorHandler;

import java.util.UUID;

public class Main {
    private static UUID uuid;
    private static GameManager manager;

    public static void main(String[] args){
        ClientMain main = new ClientMain(Main.class);

        KeyInput.get().init(ClientMain.getInstance().getEngine().getInput());
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
