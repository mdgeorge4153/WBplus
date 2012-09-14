package com.mdgeorge.wb.events;

public interface AnimationEvent {
	/**
	 * Visitor method.  Call the appropriate visitType method, passing this. 
	 */
	public <R,E extends Exception> R visit(AnimationEventVisitor<R,E> visitor) throws E;
	
	/**
	 * Return the timestamp for this event in milliseconds since the beginning
	 * of the  animation.
	 */
	public long getTime();
}
