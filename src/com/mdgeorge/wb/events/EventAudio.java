package com.mdgeorge.wb.events;

public class EventAudio extends AnimationEvent
{
	private byte[] contents;
	private int    contentLength;
	private long   duration;
	
	public EventAudio(long time)
    {
		super(time);
    }

	public void setContents(byte[] contents, int length)
	{
		this.contents      = contents;
		this.contentLength = length;
	}
	
	
	public byte[] getContents()
	{
		return this.contents;
	}
	
	@Override
    public <R, E extends Exception>
	R visit(AnimationEventVisitor<R, E> visitor)
    throws E
    {
		return visitor.visitAudio(this);
    }
}
