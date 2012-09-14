package com.mdgeorge.wb.events;

public class EventPenStart implements AnimationEvent {

	private final long  time;
	private final float x,y;

	public EventPenStart(final long time, final float x, final float y)
	{
		this.time = time;
		this.x    = x;
		this.y    = y;
	}
	
	@Override
	public <R, E extends Exception>
	R visit(AnimationEventVisitor<R, E> visitor) throws E {
		return visitor.visitPenStart(this);
	}

	@Override
	public long getTime() {
		return this.time;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

}
