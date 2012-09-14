package com.mdgeorge.wb.events;

public class EventColor implements AnimationEvent {
	
	private final int  color;
	private final long time;
	
	public EventColor(long time, int color)
	{
		this.color = color;
		this.time  = time;
	}

	@Override
	public long getTime()
	{
		return this.time;
	}

	public int getColor()
	{
		return this.color;
	}

	@Override
	public <R, E extends Exception>
	R visit(AnimationEventVisitor<R, E> visitor)
	throws E
	{
		return visitor.visitColor(this);
	}
}
