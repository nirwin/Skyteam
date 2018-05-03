package org.pumatech.teams.Skynet;

import info.gridworld.grid.Location;

public class T1K extends SkynetSTC {
	// Stays around the flag and moves to intercept opposing players within a square

	public T1K(Location startLocation) {
		super(startLocation);
	}



	public Location getMoveLocation() {
		//pulling the location from skynet 
		if (T850Loc != null){
			
			return T850Loc;
		}
		else {
		return this.getLocation();	
		}
		
	
	}
}
