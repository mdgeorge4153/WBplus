package com.mdgeorge.wb;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;

import com.mdgeorge.wb.events.*;

public class EventDrawer implements AnimationEventVisitor<Void, RuntimeException> {

	private float  penX,    penY;
	private float  eraserX, eraserY;
	
	private Canvas canvas;
	private Paint  brush;
	private Paint  eraser;
	
	public EventDrawer(Canvas canvas) {
		this(canvas,0,0,0,0,Color.BLACK,0);
	}
	
	public EventDrawer(Canvas canvas,
	                    float penX,    float penY,
	                    float eraserX, float eraserY,
	                    int   color,
	                    long  time)
	{
		this.canvas = canvas;
		
		this.penX = penX;
		this.penY = penY;
		
		this.eraserX = eraserX;
		this.eraserY = eraserY;
		
		this.brush = new Paint();
		this.brush.setColor(color);
		this.brush.setStrokeCap(Cap.ROUND);
		this.brush.setAntiAlias(true);
		
		this.eraser = new Paint();
		this.eraser.setColor(Color.WHITE);
		this.eraser.setStrokeCap(Cap.ROUND);
		this.eraser.setAntiAlias(true);
		
		this.time = time;
	}
	
	
	@Override
	public Void visitPenStart(EventPenStart event) {
		checkTime(event);
		
		this.penX = event.getX();
		this.penY = event.getY();
		return null;
	}

	@Override
	public Void visitPenMove(EventPenMove event) {
		checkTime(event);
		
		this.brush.setStrokeWidth(event.getWidth());
		
		this.canvas.drawLine(this.penX,    this.penY,
		                     event.getX(), event.getY(),
		                     this.brush);
		this.penX = event.getX();
		this.penY = event.getY();
		
		return null;
	}


	@Override
	public Void visitEraserStart(EventEraserStart event) {
		checkTime(event);
		this.eraserX = event.getX();
		this.eraserY = event.getY();
		return null;
	}

	@Override
	public Void visitEraserMove(EventEraserMove event) {
		checkTime(event);
		
		this.eraser.setStrokeWidth(event.getWidth());
		this.canvas.drawLine(this.eraserX, this.eraserY,
		                     event.getX(), event.getY(),
		                     this.eraser);

		this.eraserX = event.getX();
		this.eraserY = event.getY();
		
		return null;
	}

	
	@Override
	public Void visitColor(EventColor event) {
		checkTime(event);
		brush.setColor(event.getColor());
		return null;
	}
	
	@Override
	public Void visitPage(EventPage event) {
		checkTime(event);
		return null;
	}

	
	private long time;
	private void checkTime(AnimationEvent event) {
		assert(event.getTime() >= this.time);
		this.time = event.getTime();
	}
}
