package com.mdgeorge.wb.events;

public class EventEraserStart implements AnimationEvent
{

	private final long	time;
	private final float	x, y;

	public EventEraserStart(long time, float x, float y)
	{
		this.time = time;
		this.x    = x;
		this.y    = y;
	}

	@Override
	public <R, E extends Exception>
	R visit(AnimationEventVisitor<R, E> visitor)
	throws E
	{
		return visitor.visitEraserStart(this);
	}

	@Override
	public long getTime()
	{
		return this.time;
	}

	public float getX()
	{
		return this.x;
	}

	public float getY()
	{
		return this.y;
	}
}
