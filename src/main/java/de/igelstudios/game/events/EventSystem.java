package de.igelstudios.game.events;

import de.igelstudios.igelengine.common.util.Tickable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventSystem implements Tickable {
    private static final List<Event> events = new ArrayList<>();
    private static boolean init = false;

    public static void add(Event e){
        if(init)throw new RuntimeException("Events have to be added before the Class is created");
        events.add(e);
    }
    private int ticks;
    private int tickCount;
    private int activeEvent;

    public EventSystem(){
        init = true;
    }
    public void tick() {
        if(activeEvent == -1 && tickCount >= 2*ticks){
            int activeEvent = new Random().nextInt(events.size());
            tickCount = 0;
            ticks = events.get(activeEvent).getTime();
        }
        else if(activeEvent != -1 && tickCount <= ticks){
            events.get(activeEvent).execute();
            tickCount ++;
        }
        else {
            activeEvent = -1;
        }
    }
}
