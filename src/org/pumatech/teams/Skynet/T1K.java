package org.pumatech.teams.Skynet;


import info.gridworld.grid.Location;

public class T1K extends STC {
	// Stays around the flag and moves to intercept opposing players within a square
	
	
	
	public T1K(Location startLocation) {
		super(startLocation);
	}



	public Location getMoveLocation() {
		//pulling the location from skynet 
		System.out.println("t1k");
		if (loc2!= null){
			System.out.println("loc 2 sent");
			return loc2;
		}
		else {
			System.err.println("null for loc 2");
		return this.getLocation();	
		}
		
	
	}
}
