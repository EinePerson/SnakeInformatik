package de.igelstudios.game;

import de.igelstudios.game.events.BlockEvent;
import de.igelstudios.game.events.EventSystem;
import de.igelstudios.game.events.SpeedEvent;
import de.igelstudios.game.events.TeleportEvent;

public class Common {

    public static void init(){
        //EventSystem.add(new BlockEvent());
        EventSystem.add(new SpeedEvent());
        //EventSystem.add(new TeleportEvent());
    }
}
