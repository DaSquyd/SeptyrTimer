package com.Septyr.David.SeptyrTimer;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class TimerEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
    private Timer timer;
	
    public TimerEvent(Timer timer) {
        this.timer = timer;
    }
    
    public Timer getTimer() {
    	return timer;
    }
    
    public HandlerList getHandlers() {
    	return handlers;
    }

    public static HandlerList getHandlerList() {
    	return handlers;
    }
}
