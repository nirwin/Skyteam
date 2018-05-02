package org.pumatech.teams.Skynet;

import info.gridworld.grid.Location;

public class T1K extends SkynetSTC {
	// Stays around the flag and moves to intercept opposing players within a square
	// 8 tile proximity
	public T1K(Location startLocation) {
		super(startLocation);
	}

	public static Location target;

	public void SetTarget(Location l) {
		target = l;
	}

	public Location getMoveLocation() {
		// choose target here
		Location best = this.getLocation();
		// calculate optimal route to target here
		return best;
	}
}
