package de.igelstudios.game.events;

import de.igelstudios.ServerMain;
import de.igelstudios.game.ServerInit;
import de.igelstudios.igelengine.common.networking.PacketByteBuf;
import de.igelstudios.igelengine.common.networking.server.Server;
import de.igelstudios.igelengine.common.util.Tickable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EventSystem implements Tickable {
    private static final List<Event> events = new ArrayList<>();
    private static boolean init = false;

    public static void add(Event e){
        if(init)throw new RuntimeException("Events have to be added before the Class is created");
        events.add(e);
    }
    private int ticks;
    private int tickCount;
    private int activeEvent = -1;

    public EventSystem(){
        init = true;
    }

    @Override
    public void tick() {
        if(activeEvent == -1 && tickCount >= 2*ticks){
            activeEvent = new Random().nextInt(events.size());
            tickCount = 0;
            PacketByteBuf buf = PacketByteBuf.create();
            buf.writeString(events.get(activeEvent).getName());
            ServerInit.getManager().getSnakes().keySet().forEach(uuid -> Server.send2Client(ServerMain.getInstance().getEngine().get(uuid),"Event",buf));
            ticks = events.get(activeEvent).getTime();
        }
        else if(activeEvent != -1 && tickCount <= ticks){
            events.get(activeEvent).execute();
            tickCount ++;
        }
        else {
            activeEvent = -1;
            tickCount ++;
        }
    }
}
