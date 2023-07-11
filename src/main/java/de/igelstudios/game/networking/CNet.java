package de.igelstudios.game.networking;

import de.igelstudios.igelengine.common.networking.client.Client;

public class CNet {

    public static void register(){
        Client.registerServer2ClientHandler("Snakes",S2CSnakes::receive);
        Client.registerServer2ClientHandler("Die",S2CSnakeDie::receive);
        Client.registerServer2ClientHandler("FoodS",S2CFoodSpawn::receive);
        Client.registerServer2ClientHandler("FoodR",S2CFoodRemove::receive);
        Client.registerServer2ClientHandler("Event",S2CEventOccur::receive);
        Client.registerServer2ClientHandler("MapObjs",S2CMapObjects::receive);
    }
}
