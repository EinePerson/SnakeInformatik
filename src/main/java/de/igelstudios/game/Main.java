package de.igelstudios.game;

import de.igelstudios.ClientMain;
import de.igelstudios.game.client.KeyInput;
import de.igelstudios.game.menus.MainMenu;
import de.igelstudios.game.networking.CNet;
import de.igelstudios.igelengine.common.networking.ErrorHandler;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.startup.EngineInitializer;
import de.igelstudios.igelengine.common.startup.KeyInitializer;

import java.util.UUID;

public class Main implements EngineInitializer {
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
        Common.init();
        new MainMenu();
        manager = new GameManager();
        CNet.register();
        ClientMain.getInstance().getEngine().addTickable(manager);
        connect("localhost");
    }

    @Override
    public void onEnd() {

    }

    public static void connect(String address){
        new Client(address,new SimpleHandler()).start();
        Client.send2Server("Login", PacketByteBuf.create());
    }

    public static class SimpleHandler implements ErrorHandler{

        @Override
        public void handle(Throwable cause) {
            cause.printStackTrace();
        }
    }
}
