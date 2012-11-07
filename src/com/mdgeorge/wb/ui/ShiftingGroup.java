package com.mdgeorge.wb.ui;

import com.mdgeorge.wb.R;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ShiftingGroup extends LinearLayout
{
	private Animator slide;
	
	public ShiftingGroup(Context ctx) {
		super(ctx);
		this.initialize(ctx);
	}
	
	public ShiftingGroup(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		this.initialize(ctx);
	}
	
	public ShiftingGroup(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);
		this.initialize(ctx);
	}

	
	private void initialize(Context ctx) {
        this.slide = AnimatorInflater.loadAnimator(ctx, R.animator.slideout);
		/*
		LayoutTransition transition = new LayoutTransition();
		
        transition.setAnimator(LayoutTransition.APPEARING,        slide);
        transition.setAnimator(LayoutTransition.DISAPPEARING,     slide);
        
        transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);
        transition.setStartDelay(LayoutTransition.APPEARING, 0);
        
        this.setLayoutTransition(transition);
        */
	}
	
	/**
	 * Removes the first element of this view, and adds the new view in the
	 * specified position.  For example, [a, b, c, d].shiftAndAdd(e, 1) would
	 * yield [b, e, c, d].  Animates the added view's translation and scale from
	 * their current values to 0 and 1 respectively.
	 * 
	 * @param view
	 *            the new view to add.
	 * @param position
	 *            the index of the added view in the final collection
	 */
	public void shiftAndAdd(View view, int position) {
		float contentShift = this.getChildAt(0).getWidth();
		
		
		Animator a = slide.clone();
		a.setTarget(this.getChildAt(0));
		a.start();
		this.removeViewAt(0);
	}
}
