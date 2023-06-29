package de.igelstudios.game;

import de.igelstudios.ServerMain;
import de.igelstudios.game.events.EventSystem;
import de.igelstudios.game.networking.SNet;
import de.igelstudios.igelengine.common.startup.ServerInitializer;
import de.igelstudios.igelengine.server.ServerEngine;

public class ServerInit implements ServerInitializer {
    private static GameManager manager;

    public static void main(String[] args){
        ServerEngine engine = new ServerEngine(new Main.SimpleHandler());

    }

    public static GameManager getManager() {
        return manager;
    }

    @Override
    public void onInitialize() {
        Common.init();
        SNet.register();
        ServerMain.getInstance().getEngine().addTickable(new EventSystem());
        manager = new GameManager();
        ServerMain.getInstance().getEngine().addTickable(manager);
    }

    @Override
    public void onEnd() {

    }
}
