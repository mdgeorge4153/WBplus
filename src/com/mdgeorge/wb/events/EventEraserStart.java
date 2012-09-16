package com.mdgeorge.wb.events;

public final class EventEraserStart extends AnimationEvent
{

	private final float	x, y;

	public EventEraserStart(long time, float x, float y)
	{
		super(time);
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

	public float getX()
	{
		return this.x;
	}

	public float getY()
	{
		return this.y;
	}
}
