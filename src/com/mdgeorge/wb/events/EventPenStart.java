package com.mdgeorge.wb.events;

public final class EventPenStart extends AnimationEvent {

	private final float x,y;

	public EventPenStart(final long time, final float x, final float y)
	{
		super(time);
		this.x    = x;
		this.y    = y;
	}
	
	@Override
	public <R, E extends Exception>
	R visit(AnimationEventVisitor<R, E> visitor) throws E {
		return visitor.visitPenStart(this);
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

}
