package com.mdgeorge.wb;

import android.os.SystemClock;

/**
 * <p>This class handles translating timestamps from the
 * {@link SystemClock.uptimeMillis()} base to animation time. 
 * <em>Animation time</em> is defined as the elapsed non-paused time since the
 * clock's creation.</p>
 * 
 * <p>All of the methods take a time argument, and it is assumed that the values
 * passed in represent times in non-decreasing wall clock time.  If they are
 * not, then we assume that the uptimeMillis clock has wrapped around, and we
 * adjust accordingly.  This has the following implications:<ul>
 *   <li>the returns from {@link getRelativeTime()} will be non-decreasing.</li>
 *   <li>passing in times out of order will cause nonsensical results</li>
 *   </ul>
 * Since there is no way to tell, we assume that the wrap happened halfway
 * between the wall-clock times of the most recent events.</p>
 * 
 * <p>Here is an example showing how it all works:
 * <table>
 *   <tr> <th> Event  </th> <th> Real Time</th>     <th>uptimeMillis Time</th> <th>animation Time</th> <th> Description </th> </tr>
 *   
 *   <tr> <td> create </td> <td> 12:00+   0ms </td> <td> 10 </td> <td>  0 </td>
 *        <td><p>The animation time is always zero when the clock is created.</p></td>
 *        </tr>
 *   
 *   <tr> <td>   1    </td> <td> 12:00+  10ms </td> <td> 20 </td> <td> 10 </td>
 *        <td><p>10 ms have elapsed according to the input uptimeMillis, so the
 *               animation time steps to 10</p></td>
 *        </tr>
 *   
 *   <tr> <td>   2    </td> <td> 12:00+  20ms </td> <td> 30 </td> <td> 20 </td>
 *        <td><p>similar to the last event</p></td>
 *        </tr>
 *        
 *   <tr> <td>   3    </td> <td> 12:00+  80ms </td> <td> 10 </td> <td> 40 </td>
 *        <td><p>The updateMillis input has gone down, so we infer that the
 *               clock has wrapped back to zero.  We know that 10ms have passed
 *               since the clock looped, so we assume that 10 ms also
 *               elapsed <em>before</em> the clock was reset.  Therefore we
 *               conclude that 20ms has elapsed.  Note that this is inaccurate:
 *               according to the real time, 60ms has elapsed.  But we can't do
 *               better.</p></td>
 *        </tr>
 *   
 *   <tr> <td> pause  </td> <td> 12:00+  90ms </td> <td> 20 </td> <td> 50 </td>
 *        <td><p>The clock is paused.</p></td>
 *        </tr>
 *        
 *   <tr> <td>   4    </td> <td> 12:00+ 100ms </td> <td> 30 </td> <td> 50 </td>
 *        <td><p>Since the clock is paused, all subsequent events happen at the
 *               same animation time.</p></td>
 *        </tr>
 *               
 *   <tr> <td> start  </td> <td> 12:00+ 120ms </td> <td> 50 </td> <td> 50 </td>
 *        <td><p>The clock is restarted.</p></td>
 *        </tr>
 *        
 *   <tr> <td>   5    </td> <td> 12:00+ 130ms </td> <td> 60 </td> <td> 60 </td>
 *        <td><p>10 more ms have elapsed since the clock was restarted, so the
 *               animation time advances by 10 ms.</p></td>
 *        </tr>
 *   </table> 
 * </p>
 * 
 * @author mdgeorge
 */
public class AnimationClock
{
	////////////////////////////////////////////////////////////////////////////
	// private fields //////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	/** whether the animation is paused. */
	private boolean paused;
	
	/** the (animation) time of the most recent pause event. */
	private long    pauseTime;
	
	/** the (uptimeMillis) time of the most recent pause event. */
	private long    pauseAbsTime;
	
	/** the most recent uptimeMillis base timestamp encountered. */
	private long    lastTime;
	
	/** start time in uptimeMillis base plus the total duration of pauses. */
	private long    tweakedStartTime;
	
	////////////////////////////////////////////////////////////////////////////
	// constructors ////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public AnimationClock(long startTime, boolean paused) {
		this.paused           = paused;
		this.lastTime         = startTime;
		this.tweakedStartTime = startTime;
		
		if (this.paused) {
			this.pauseAbsTime = startTime;
			this.pauseTime    = 0;
		}
	}
	
	public AnimationClock(long startTime) {
		this(startTime, true);
	}
	
	public AnimationClock() {
		this(SystemClock.uptimeMillis());
	}
	
	public AnimationClock(boolean paused)	{
		this(SystemClock.uptimeMillis(), true);
	}
	
	public boolean isPaused() {
		return this.paused;
    }
	
	////////////////////////////////////////////////////////////////////////////
	// public time management methods //////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * start the animation clock at the given time.
	 * 
	 * @param time a timestamp in the {@link SystemClock.uptimeMillis()} base.
	 */
	public void startClock(long time)	{
		fixWrap(time);
		this.paused            = false;
		this.tweakedStartTime += time - pauseAbsTime;
	}
	
	/** pause the animation clock at the given time.
	 *
	 * @param time a timestamp in the {@link SystemClock.uptimeMillis()} base.
	 */
	public void pauseClock(long time) {
		fixWrap(time);
		this.paused       = true;
		this.pauseAbsTime = time;
		this.pauseTime    = getRelativeTime(time);
	}
	
	/**
	 * <p>Convert time from the {@link SystemClock.uptimeMillis()} base to the
	 * animation time since the start of the animation, in milliseconds. If
	 * the clock is paused, then this will be the time of the most recent
	 * call to pauseClock(), otherwise it will be the time since the most
	 * recent call to startClock().</p>
	 * 
	 * @param  time
	 *     A timestamp in {@link SystemClock.uptimeMillis()} base.
	 * @return
	 *     The animation time in milliseconds.
	 */
	public long getRelativeTime(long time)
	{
		fixWrap(time);
		
		if (this.paused)
			return this.pauseTime;
		else
			return time - tweakedStartTime;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// private helper methods //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Update the state to handle time wrapping.  After the call,
	 * this.startTime <= this.pauseAbsTime <= time, and those three values are
	 * on a comparable time scale.
	 */
	private void fixWrap(long newTime) {
		if (newTime < lastTime) {
			// clock has wrapped around.  That means that at least lastTime ms
			// have been subtracted, but possibly more.  As stated above, we
			// assume that the clock reset happened halfway between the last
			// tick and the current one; this means that newTime ms elapsed
			// between the last tick and the clock reset.
			long delta = this.lastTime + newTime;
			this.tweakedStartTime -= delta;
			this.pauseAbsTime     -= delta;
		}
		
		this.lastTime = newTime;
	}

}
