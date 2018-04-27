package org.pumatech.teams.Skynet;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class T1K extends AbstractPlayer {
	// Stays around the flag and moves to intercept opposing players within a square
	// 8 tile proximity
	public T1K(Location startLocation) {
		super(startLocation);
	}
	
	public Location getMoveLocation() {
		// choose target here
		Location best = this.getLocation();
		// calculate optimal route to target here
		return best;
	}
}
