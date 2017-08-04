package com.Septyr.David.SeptyrTimer;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SeptyrTimer extends JavaPlugin {
	static final Logger logger = Logger.getLogger("Minecraft");
	public Long startTime;
	
	public static Set<Timer> timers = new HashSet<Timer>();
	static Set<Timer> usedTimers = new HashSet<Timer>();
	
	static TickEvent tickEvent = new TickEvent();
	
	static Runnable runnable = new BukkitRunnable() {
		@Override
		public void run() {
			for (Timer t : timers) {
				if (t.isFinished()) {
					TimerEvent event = new TimerEvent(t);
					Bukkit.getServer().getPluginManager().callEvent(event);
					usedTimers.add(t);
				}
			}
			
			for (Timer t : usedTimers) {
				timers.remove(t);
			}
			usedTimers.clear();
			
			Bukkit.getServer().getPluginManager().callEvent(tickEvent);
		}
	};
	
	@Override
	public void onEnable() {
		startTime = System.nanoTime();
		logger.log(Level.INFO, getDescription().getName() + " v"
				+ getDescription().getVersion() + " enabled!");
		((BukkitRunnable) runnable).runTaskTimerAsynchronously(this, 0, 0);
	}
	
	@Override
	public void onDisable() {
		logger.log(Level.INFO, getDescription().getName() + " v"
				+ getDescription().getVersion() + " disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return true;
	}
	
	
}
