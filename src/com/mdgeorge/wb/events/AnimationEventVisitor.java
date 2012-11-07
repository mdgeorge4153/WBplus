package com.mdgeorge.wb.events;

public interface AnimationEventVisitor <R,E extends Exception> {
	public R visitAudio       (EventAudio       event) throws E;
	public R visitPenStart    (EventPenStart    event) throws E;
	public R visitPenMove     (EventPenMove     event) throws E;
	public R visitEraserStart (EventEraserStart event) throws E;
	public R visitEraserMove  (EventEraserMove  event) throws E;
	public R visitColor       (EventColor       event) throws E;
	public R visitPage        (EventPage        event) throws E;
}
