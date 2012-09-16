package com.mdgeorge.wb;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPlayback extends Fragment {

	private AnimationClock clock = new AnimationClock();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

	@Override
	public View
	onCreateView ( LayoutInflater inflater
	             , ViewGroup group
	             , Bundle savedInstanceState
	             )
	{
		return inflater.inflate(R.layout.fragment_playback, group, false);
	}
}
