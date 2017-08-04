package com.Septyr.David.SeptyrTimer;

/**
 * Timers are the primary basis of the API.
 * 
 * @author David
 */
public class Timer {

	/**
	 * The exact time in nanoseconds at which this timer was created. 1
	 * nanosecond = 1/5,000,0000 game ticks 1 game tick = 20,000,000,000
	 * nanoseconds
	 */
	public Long startTime;

	/**
	 * The namespace that the Timer will use for reference later. Tagging two
	 * Timers with the same name may access both simultaneously.
	 */
	public final String tag;
	/**
	 * The total runtime length in game ticks.
	 */
	public final short ticks;

	public Timer(String tag) {
		this.tag = tag;
		startTime = System.nanoTime();
		ticks = 0;
		
		add();
	}
	
	public Timer(String tag, double seconds) {
		this.tag = tag;
		startTime = System.nanoTime();
		ticks = (short) (Math.max(seconds * 20, 1));

		add();
	}

	public Timer(String tag, short ticks) {
		this.tag = tag;
		startTime = System.nanoTime();
		this.ticks = (short) Math.max(ticks, 1);
		add();
	}

	private void add() {
		SeptyrTimer.timers.add(this);
	}

	public void cancel() {
		SeptyrTimer.timers.remove(this);
	}

	/**
	 * @return how long the Timer has been running in seconds.
	 */
	public double progressInSeconds() {
		return (System.nanoTime() - startTime) * 0.000000001;
	}

	/**
	 * @return how long the Timer has been running in game ticks.
	 */
	public short progressInTicks() {
		return (short) ((System.nanoTime() - startTime) * 0.00000002);
	}

	/**
	 * @return float between 0 and 1 for completion
	 */
	public float completion() {
		if (ticks != (short) 0)
			return (float) progressInTicks() / (float) ticks;
		else
			return 0f;
	}

	/**
	 * @return whether or not the Timer has finished its count
	 */
	public boolean isFinished() {
		if (ticks > 0) {
			if ((System.nanoTime() - startTime) * 0.00000002 >= ticks) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * ticks / 20 - progressInSeconds
	 * 
	 * @return the amount of seconds remaining on the {@link Timer}
	 */
	public double getCountdownInSeconds() {
		return (((double) ticks / 20d) - progressInSeconds());
	}

	/**
	 * ticks = progressInTicks
	 * 
	 * @return the amount of game ticks remaining on the {@link Timer}
	 */
	public short getCountdownInTicks() {
		return (short) (ticks - progressInTicks());
	}

	/**
	 * Resets the count back to 0
	 */
	public void reset() {
		startTime = System.nanoTime();
	}
}
