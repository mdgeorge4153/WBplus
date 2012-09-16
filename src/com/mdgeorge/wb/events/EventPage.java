package com.mdgeorge.wb.events;

public final class EventPage extends AnimationEvent
{
	private final int page;
	
	public EventPage(long time, int page) {
		super(time);
		this.page = page;
	}
	
	@Override
	public <R, E extends Exception> R visit(AnimationEventVisitor<R, E> visitor)
	        throws E
	{
		return visitor.visitPage(this);
	}
	
	public int getPage() {
		return this.page;
	}

}
