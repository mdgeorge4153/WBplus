package com.mdgeorge.wb.events;


public class EventEraserMove implements AnimationEvent
{
	private final long	time;
	private final float	width;
	private final float	x, y;

	public EventEraserMove(long time, float x, float y, float width)
	{
		this.time  = time;
		this.x     = x;
		this.y     = y;
		this.width = width;
	}

	@Override
	public <R, E extends Exception> R visit(AnimationEventVisitor<R, E> visitor)
	        throws E
	{
		return visitor.visitEraserMove(this);
	}

	@Override
	public long getTime()
	{
		return this.time;
	}

	public float getWidth()
	{
		return this.width;
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
