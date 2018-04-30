package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.*;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

/* This is the main offensive player on Skynet's team. */

public class Moto extends AbstractPlayer {
	
	public Moto(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		List<Location> possibleMoveLocations = this.getGrid().getEmptyAdjacentLocations(getLocation()); 
		if (possibleMoveLocations.size() == 0) { return null; }
		if (hasFlag()) {
			return avoid( possibleMoveLocations, this.getTeam().getFlag().getLocation() );
		}
		//System.out.println(possibleMoveLocations);
		return avoid( possibleMoveLocations, this.getTeam().getOpposingTeam().getFlag().getLocation() );
	}
	
	public Location avoid(List<Location> scan, Location target) {
		ArrayList<Location> temp = new ArrayList<Location>(scan);
		for(Location test : scan) {
			if(test.getCol() != this.getLocation().getCol() && test.getRow() != this.getLocation().getRow()) {
				//test for attacker 'auras'
				List<AbstractPlayer> theirPlayers = this.getTeam().getOpposingTeam().getPlayers();
				for(AbstractPlayer detect : theirPlayers) {
					if(this.getGrid().get(test) == detect) {
						temp.remove(test);
					}
					for(Actor a : this.getGrid().getNeighbors(test)) {
						if(a == detect) {
							temp.remove(test);
						}
					}
				}
			}
		}
		scan = temp;
		
		// Current issue: get last location and don't go back
		//determine optimal direction
		int minDir = 360;
		Location best = scan.get(0);
		for(Location l : scan) {
			int a = this.getLocation().getDirectionToward(l);
			int t = this.getLocation().getDirectionToward(target);
			if(Math.abs(t-a) < minDir) {
				if(getGrid().getEmptyAdjacentLocations(l).size() > 1) {
					best = l;
				}
				minDir = Math.abs(t-a);
			}
		}
		return best;
	}
	
}
