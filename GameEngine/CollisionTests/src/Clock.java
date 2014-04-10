
public class Clock {
	
	/* Stores the previous and current time */
	private long ptime;
	private long ctime;
	
	/* Store the time passed since the last call to update */
	private long tick;
	
	/* Store the time passed since the clock started */
	private long totalTime;
	
	/* Number of time the clock ticked since start */
	private int tick_nb;
	
	/* CTOR */
	public Clock() {
		tick = 0;
		totalTime = 0;
		ptime = 0;
		ctime = 0;
		
		tick_nb = 0;
	}
	
	/* Update the clock */
	public void update() {
		ptime = ctime;
		ctime = System.currentTimeMillis();
		
		tick = ctime - ptime;
		totalTime += tick;
		
		++tick_nb;
	}
	
	/* Reset the clock */
	public void reset() {
		ptime = 0;
		ctime = 0;
		tick = 0;
		totalTime = 0;
		tick_nb = 0;
	}
	
	public long getPreviousTime() {
		return ptime;
	}
	
	public long getCurrentTime() {
		return ctime;
	}
	
	public long getTick() {
		return tick;
	}
	
	public long getTickNumber() {
		return tick_nb;
	}
	
	public long getTotalTime() {
		return totalTime;
	}
}
