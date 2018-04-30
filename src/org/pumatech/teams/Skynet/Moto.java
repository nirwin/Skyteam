package org.pumatech.teams.Skynet;

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
		/*for(Location test : scan) {
			if(test.getCol() != this.getLocation().getCol() && test.getRow() != this.getLocation().getRow()) {
				//test for attacker 'auras'
				List<AbstractPlayer> theirPlayers = this.getTeam().getOpposingTeam().getPlayers();
				for(AbstractPlayer detect : theirPlayers) {
					if(this.getGrid().get(test) == detect) {
						scan.remove(test);
					}
					for(Actor a : this.getGrid().getNeighbors(test)) {
						if(a == detect) {
							scan.remove(test);
						}
					}
				}
			}
		}*/
		
		// Current issue: ignores both target and returned value from avoid() and goes for (0,0)
		//determine optimal direction
		int minDir = 360;
		Location best = scan.get(0);
		for(Location l : scan) {
			int a = this.getLocation().getDirectionToward(l);
			if(Math.abs(this.getLocation().getDirectionToward(target)-a) < minDir) {
				if(getGrid().getEmptyAdjacentLocations(l).size() > 1) {
					best = l;
				}
				minDir = a;
			}
		}
		return best;
	}
	
}
