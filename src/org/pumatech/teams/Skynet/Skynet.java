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
//		first we need to check in an 8 grid radius of the flag, 
//		returning first found target for t1ks to go to, wotherwise they will not move
//		then we check on our side, assigning closest one to T850 to chase, else t850 wont move
//		processing and updating of target lists goes here
		return this.getLocation();
	}
}
