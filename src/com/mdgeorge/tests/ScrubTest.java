package com.mdgeorge.tests;

import com.mdgeorge.wb.EventDrawer;
import com.mdgeorge.wb.FragmentDraw;
import com.mdgeorge.wb.R;
import com.mdgeorge.wb.events.AnimationEvent;
import com.mdgeorge.wb.events.EventEraserMove;
import com.mdgeorge.wb.events.EventEraserStart;
import com.mdgeorge.wb.events.EventPenMove;
import com.mdgeorge.wb.events.EventPenStart;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.FloatMath;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

public class ScrubTest extends Activity implements SeekBar.OnSeekBarChangeListener {

	private AnimationEvent[] stream;
	private SeekBar          seeker;
	private DrawingArea      da;
	private Paint            paint;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrub_test);

        seeker = (SeekBar) findViewById(R.id.seekBar1);
        seeker.setOnSeekBarChangeListener(this);

        FrameLayout f = (FrameLayout) findViewById(R.id.drawingFrame);
        da            = new DrawingArea();
        f.addView(da);
        
        this.stream = new AnimationEvent[6000];
        for (int i = 0; i < 6000; i++) {
        	
        	// time in current stroke, between 0.0 and 2*PI
    		float t = (float) Math.PI * 2 * (i % 100) / 100;
    		
    		// stroke number, between 0 and 6
    		int   n = (i / 100) % 6;
    		
    		// 0 for draw, 1 for erase
    		boolean drawing = (n / 3 == 0);
    		
    		// which substroke
    		int substroke = n % 3;
    		
    		if (drawing) {
    			int x = (int) (150 * t);
    			int y = (int) (100 * (FloatMath.sin(t) + substroke) + 200);
    			if (t == 0)
    				this.stream[i] = new EventPenStart(i, x, y);
    			else
    				this.stream[i] = new EventPenMove(i, x, y, 10);
    		}
    		else {
    			 int x = (int) ( 150 * (t + substroke) / 3);
    			 int y = (int) (-200 * FloatMath.cos(t) + 200);
    			 
    			 if (t == 0)
    				 this.stream[i] = new EventEraserStart(i, x, y);
    			 else
    				 this.stream[i] = new EventEraserMove(i, x, y, 120);
    		}
        }
        
        this.paint = new Paint();
    }

    private void drawUntil(Canvas c, int time) {
    	c.drawColor(Color.WHITE);
    	EventDrawer d = new EventDrawer(c);
    	for (int i = 0; i < time; i ++)
    		stream[i].visit(d);
    	c.drawText(Integer.toString(time), 50, 50, paint);
    }

	@Override
    public void onProgressChanged(SeekBar seekBar, int progress,
            boolean fromUser)
    {
		da.invalidate();
    }

	@Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
    }

	@Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
    }
	
	////////////////////////////////////////////////////////////////////////////
	// View class //////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public class DrawingArea extends View
	{
		public DrawingArea()
		{
			super(ScrubTest.this);
		}

		@Override
		public void onDraw(Canvas canvas)
		{
			drawUntil(canvas, seeker.getProgress());
		}
	}
}
