package com.mdgeorge.tests;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.mdgeorge.wb.R;
import com.mdgeorge.wb.ui.ShiftingGroup;

public class AnimationTest extends Activity {
	private ShiftingGroup parent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        
        this.parent = (ShiftingGroup) findViewById(R.id.parent);
    }
    
    
    public void addPage(View view) {
    	parent.shiftAndAdd(createPage(), parent.getChildCount()-2);
    }
    
    
    public View createPage() {
    	ImageView result = new ImageView(this);
    	result.setImageResource(R.drawable.dummy_page);
    	result.setAdjustViewBounds(true);
    	return result;
    }

}
