package com.mdgeorge.wb.ui;

import com.mdgeorge.wb.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class FixedAspectFrame extends FrameLayout
{
	private int width;
	private int height;

	public FixedAspectFrame(Context context)
	{
		super(context);
		this.width  = 1;
		this.height = 1;
	}
	
	public FixedAspectFrame(Context context, int width, int height)
	{
		super(context);
		this.width  = width;
		this.height = height;
	}

	public FixedAspectFrame(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs);
	}

	public FixedAspectFrame(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context,attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(
				attrs,
		        R.styleable.com_mdgeorge_wb_ui_FixedAspectFrame);
		
		this.width  = a.getInt(R.styleable.com_mdgeorge_wb_ui_FixedAspectFrame_aspectW, 1);
		this.height = a.getInt(R.styleable.com_mdgeorge_wb_ui_FixedAspectFrame_aspectH, 1);
	}
	
	@Override
	public void onMeasure(int specW, int specH)
	{
		//
		// want w / h = this.w / this.h
		//
		int maxW = MeasureSpec.getSize(specW);
		int maxH = MeasureSpec.getSize(specH);
		
		int w, h;
		
		if (this.width * maxH > this.height * maxW) {
			w = maxW;
			h = maxW * this.height / this.width;
		}
		else {
			w = maxH * this.width / this.height;
			h = maxH;
		}
		
		super.onMeasure( MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY)
		               , MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY)
		               );
	}
}
