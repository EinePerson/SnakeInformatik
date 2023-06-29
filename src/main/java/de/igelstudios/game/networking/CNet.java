package de.igelstudios.game.networking;

import de.igelstudios.igelengine.common.networking.client.Client;

public class CNet {

    public static void register(){
        Client.registerServer2ClientHandler("Snakes",S2CSnakes::receive);
    }
}
