package com.mdgeorge.tests;

import android.os.Bundle;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.mdgeorge.wb.NotImplementedException;
import com.mdgeorge.wb.R;

public class AnimationTest extends Activity {
	private ViewGroup parent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        this.parent = (ViewGroup) findViewById(R.id.parent);
        this.parent.setLayoutTransition(new LayoutTransition());
    }
    
    
    public void addPage(View view) {
    	View result = createPage();
    	this.parent.addView(result, 2);
    	this.parent.removeViewAt(0);
    }
    
    
    public View createPage() {
    	Button result = new Button(this);
    	result.setText("moo");
    	result.setGravity(Gravity.CENTER);
    	return result;
    }

}
