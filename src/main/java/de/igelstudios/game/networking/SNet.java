package de.igelstudios.game.networking;

import de.igelstudios.igelengine.common.networking.server.Server;

public class SNet {

    public static void register(){
        Server.registerClient2ServerHandler("Login",C2SLogIn::receive);
        Server.registerClient2ServerHandler("DirectionChange",C2SDirection::receive);
    }
}
