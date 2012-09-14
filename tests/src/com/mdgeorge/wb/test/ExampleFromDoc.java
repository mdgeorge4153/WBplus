package com.mdgeorge.wb.test;

import android.test.AndroidTestCase;

import com.mdgeorge.wb.AnimationClock;


public class ExampleFromDoc extends AndroidTestCase {
	public void runExample() {
		AnimationClock clock = new AnimationClock(10, false);
		// assert(clock.time == 0);
		assertEquals(clock.getRelativeTime(20), 10);
		assertEquals(clock.getRelativeTime(30), 20);
		assertEquals(clock.getRelativeTime(10), 40);
		clock.pauseClock(20);
		// assert(clock.time == 50);
		assertEquals(clock.getRelativeTime(30), 50);
		clock.startClock(50);
		// assert(clock.time == 50); 
		assertEquals(clock.getRelativeTime(60), 60);
	}
}