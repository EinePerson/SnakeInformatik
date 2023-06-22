package de.igelstudios.game;

import de.igelstudios.igelengine.server.ServerEngine;

public class ServerMain {

    public static void main(String[] args){
        new ServerEngine(new Main.SimpleHandler()).start();
    }
}
