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
        setHasOptionsMenu(true);
    }

	@Override
	public View
	onCreateView ( LayoutInflater inflater
	             , ViewGroup group
	             , Bundle savedInstanceState
	             )
	{
		return new ReplayView();
	}
    
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_playback, menu);
	}
	
	@Override
	public boolean 	onOptionsItemSelected(MenuItem item) {
		final long time = clock.getRelativeTime(SystemClock.uptimeMillis());
		switch(item.getItemId()) {
			case R.id.button_play_pause:
				if (clock.isPaused()) {
					item.setTitle(R.string.pause);
					clock.startClock(time);
				}
				else {
					item.setTitle(R.string.play);
					clock.pauseClock(time);
				}
				return true;
		}
		
		return false;
	}
	
    public class ReplayView extends View
    {
    	public ReplayView() {
    		super(FragmentPlayback.this.getActivity());
        }
    }
}
