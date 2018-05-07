package org.pumatech.teams.Skynet;

import info.gridworld.grid.Location;

public class T850 extends STC {

	// processes a list of targets and moves to intercept, then returns
	// to the flag if there are no more targets given to it by Skynet
	
	private Location loc1;
	
	public T850(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		System.out.println("t850");
		if (loc1 == null) {
			System.out.println("loc 1 sent");
			return this.getLocation();

		} else {
			System.err.println("null for loc 1");
			return loc1;

		}
	}
}

