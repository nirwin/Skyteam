package org.pumatech.teams.Skynet;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class Skynet extends AbstractPlayer {
	// Updates static variables of other players and defends the flag against
	// attackers
	public Skynet(Location startLocation) {
		super(startLocation);
		
		
		
	}
	
	public Location getMoveLocation() {
		
		//processing and updating of target lists goes here
		return this.getLocation();
	}
}
