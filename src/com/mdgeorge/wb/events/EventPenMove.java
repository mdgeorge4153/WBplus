package com.mdgeorge.wb.events;

public final class EventPenMove extends AnimationEvent {

	private final float x, y;
	private final float width;
	
	public EventPenMove(long time, float x, float y, float width)
	{
		super(time);
		this.x     = x;
		this.y     = y;
		this.width = width;
	}
	
	@Override
	public <R, E extends Exception>
	R visit(AnimationEventVisitor<R, E> visitor)
	throws E
	{
		return visitor.visitPenMove(this);
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public float getWidth() {
		return this.width;
	}

}
