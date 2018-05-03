package org.pumatech.teams.Skynet;

import info.gridworld.grid.Location;

public class T1K extends SkynetSTC {
	// Stays around the flag and moves to intercept opposing players within a square

	public T1K(Location startLocation) {
		super(startLocation);
	}

int loc = 1;

	public Location getMoveLocation() {
		//pulling the 
		if (T1kLoc != null){
			loc++;
			return T1kLoc;
		}
		else {
		return this.getLocation();	
		}
		
	
	}
}
