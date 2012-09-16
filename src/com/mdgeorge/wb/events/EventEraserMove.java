package com.mdgeorge.wb.events;


public final class EventEraserMove extends AnimationEvent
{
	private final float	width;
	private final float	x, y;

	public EventEraserMove(long time, float x, float y, float width)
	{
		super(time);
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
