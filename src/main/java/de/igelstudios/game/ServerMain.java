package de.igelstudios.game;

import de.igelstudios.game.events.EventSystem;
import de.igelstudios.igelengine.common.util.PlayerFactory;
import de.igelstudios.igelengine.server.ServerEngine;

public class ServerMain {
    private static GameManager manager;

    public static void main(String[] args){
        ServerEngine engine = new ServerEngine(new Main.SimpleHandler());
        PlayerFactory.setPlayerClass(Player.class,true);
        engine.addTickable(new EventSystem());
        manager = new GameManager();
        engine.addTickable(manager);
        engine.start();
    }
}
