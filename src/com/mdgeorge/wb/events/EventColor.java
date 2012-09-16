package com.mdgeorge.wb.events;

public final class EventColor extends AnimationEvent {
	
	private final int  color;
	
	public EventColor(long time, int color)
	{
		super(time);
		this.color = color;
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
