package com.mdgeorge.wb.events;

public abstract class AnimationEvent {

	////////////////////////////////////////////////////////////////////////////
	// public interface ////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Visitor method.  Call the appropriate visitType method, passing this. 
	 */
	public abstract <R,E extends Exception> R visit(AnimationEventVisitor<R,E> visitor) throws E;
	
	/**
	 * Return the timestamp for this event in milliseconds since the beginning
	 * of the  animation.
	 */
	public final long getTime() {
		return this.time;
	}


	////////////////////////////////////////////////////////////////////////////
	// private / protected interface //////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	/** The timestamp of this event; see getTime() */
	private final long time; 
	
	/** create a new instance at time <code>time</code> */ 
	protected AnimationEvent(long time) {
		this.time = time;
	}
	
}
