package de.igelstudios.game.networking;

import de.igelstudios.ClientMain;
import de.igelstudios.game.GameManager;
import de.igelstudios.game.Main;
import de.igelstudios.game.map.Map;
import de.igelstudios.game.menus.DeathMenu;
import de.igelstudios.igelengine.client.gui.GUIManager;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.networking.client.ClientHandler;

import java.util.UUID;

public class S2CSnakeDie{

    public static void receive(Client client, PacketByteBuf buf) {
        UUID uuid = buf.readUUID();
        Main.getManager().remove(uuid);
        if (Main.getUuid().equals(uuid)) {
            GUIManager.getInstance().setGui(new DeathMenu());
            Main.getManager().clear();
            Map.getInstance().removeFood();
            Map.getInstance().removeObjects();
        }
    }
}
