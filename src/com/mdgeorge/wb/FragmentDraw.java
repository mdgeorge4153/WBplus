package com.mdgeorge.wb;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mdgeorge.wb.events.*;

public class FragmentDraw extends Fragment
{
	////////////////////////////////////////////////////////////////////////////
	// private fields //////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	private final AnimationClock       clock     = new AnimationClock();
	private       DrawingArea          view;
	private final List<AnimationEvent> animation = new Vector<AnimationEvent>();
	
	////////////////////////////////////////////////////////////////////////////
	// Public callback methods /////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View
	onCreateView ( LayoutInflater inflater
	             , ViewGroup group
	             , Bundle savedInstanceState
	             )
	{
		return inflater.inflate(R.layout.fragment_record, group, false);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.fragment_draw, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		long time = SystemClock.uptimeMillis();
		
		/*
		switch(item.getItemId())
		{
			case R.id.button_black:
				this.view.addAnimationEvent(new EventColor(time, Color.BLACK));
				return true;
			case R.id.button_blue:
				this.view.addAnimationEvent(new EventColor(time, Color.BLUE));
				return true;
			case R.id.button_green:
				this.view.addAnimationEvent(new EventColor(time, Color.GREEN));
				return true;
			case R.id.button_record_pause:
				if (this.clock.isPaused()) {
					this.clock.startClock(time);
				}
				else {
					this.clock.pauseClock(time);
				}
				return true;
		}
		*/
		
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// View class //////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public class DrawingArea extends View
	{
		private Canvas      canvas;
		private Bitmap      buffer;
		private EventDrawer drawer;
		
		public DrawingArea()
		{
			super(FragmentDraw.this.getActivity());
			this.canvas = new Canvas();
			this.drawer = new EventDrawer(canvas);
		}

		@Override
		public void onDraw(Canvas canvas)
		{
			canvas.drawBitmap(this.buffer,0,0,null);
		}
		
		@Override
		public void onSizeChanged(int w, int h, int oldw, int oldh)
		{
			super.onSizeChanged(w, h, oldw, oldh);
            
			this.buffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			this.canvas.setBitmap(this.buffer);
			this.canvas.drawColor(Color.WHITE);
			Log.i("foo","size: " + w + " " + h);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event)
		{
			long  time     = clock.getRelativeTime(event.getEventTime());
			float x        = event.getX();
			float y        = event.getY();
			float width    = 50*event.getToolMajor();
			float pressure = 8*event.getPressure();

			invalidate();
			
			switch(event.getToolType(event.getActionIndex()))
			{
				case MotionEvent.TOOL_TYPE_STYLUS: switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						// stylus down
						addAnimationEvent(new EventPenStart(time,x,y));
						return true;
					case MotionEvent.ACTION_MOVE:
						// stylus move
						addAnimationEvent(new EventPenMove(time,x,y,pressure));
						return true;
				}
				case MotionEvent.TOOL_TYPE_FINGER: switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						// finger down
						addAnimationEvent(new EventEraserStart(time,x,y));
						return true;
					case MotionEvent.ACTION_MOVE:
						// finger move
						// this.canvas.drawCircle(x, y, width, paint);
						addAnimationEvent(new EventEraserMove(time,x,y,width));
						return true;
				}
			}
			
			return false;
		}

		
		private void addAnimationEvent(AnimationEvent event)
		{
			event.visit(this.drawer);
			FragmentDraw.this.animation.add(event);
		}

		
	}
}
