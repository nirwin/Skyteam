package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class Skynet extends AbstractPlayer {
	// Updates static variables of other players and defends the flag against
	// attackers
	public Skynet(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		// first we need to check in an 8 grid radius of the flag,
		// returning first found target for t1ks to go to, otherwise they will not move
		// then we check on our side, assigning closest one to T850 to chase, else t850
		// wont move
		// processing and updating of target lists goes here
		List<AbstractPlayer> a = this.getTeam().getOpposingTeam().getPlayers();
		int flagc = getTeam().getFlag().getLocation().getCol();
		int flagr = getTeam().getFlag().getLocation().getRow();
		int closest[] = new int[7];
		/*
		 * creating a array of the distance of enemy players to flags
		 */
		for (int i = 0; i < a.size(); i++) {
			int x = a.get(i).getLocation().getCol();
			int y = a.get(i).getLocation().getRow();
			int distance = (int) Math.sqrt(((flagc - x) * (flagc - x)) + ((flagr - y) * (flagr - y)));
			closest[i] = distance;
		}
	
		//Choosing T1k targets
		for (int i = 0; i < closest.length; i++) {
			if (closest[i]<= 8) {
				// send target to t1k's 
				break;
			} else {
				// send each t1k to an opposite corner of flag 
			}	
		}
		//choosing target for T850

		for (int j = 0; j < closest.length; j++) {

		}

		return this.getLocation();

	}

}
