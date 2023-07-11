package de.igelstudios.game;

import de.igelstudios.igelengine.common.networking.client.ClientNet;

import java.util.UUID;

public class Player implements ClientNet {
    private UUID uuid;

    public Player(UUID uuid){
        this.uuid = uuid;
    }
    public Player(){

    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
}
