package org.pumatech.teams.Skynet;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class T1K extends AbstractPlayer {
	// Stays around the flag and moves to intercept opposing players within a square
	
	private Location T1KLoc;
	
	public T1K(Location startLocation) {
		super(startLocation);
	}



	public Location getMoveLocation() {
		//pulling the location from skynet 
		if (T1KLoc != null){
			
			return T1KLoc;
		}
		else {
		return this.getLocation();	
		}
		
	
	}
}
