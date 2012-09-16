package com.mdgeorge.wb;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class PageView extends View
{
	private Drawable drawable;
	
	public PageView(Context context)
    {
		super(context);
    }
	
	public PageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		this.drawable = getResources().getDrawable(R.drawable.dummy_page);
	}
	
	public PageView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		
		TypedArray array = context.obtainStyledAttributes(
				attrs,
		        R.styleable.com_mdgeorge_wb_PageView,
		        defStyle,
		        0
		        );

		this.drawable = array.getDrawable(
				R.styleable.com_mdgeorge_wb_PageView_android_src
				);
		
		array.recycle();
	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		
		
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}
