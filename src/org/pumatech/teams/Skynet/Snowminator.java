package org.pumatech.teams.Skynet;

import java.util.List;

import org.pumatech.ctf.*;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class Snowminator extends AbstractPlayer {
	
	public Snowminator(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		List<Location> possibleMoveLocations = getGrid().getEmptyAdjacentLocations(getLocation()); 
		if (possibleMoveLocations.size() == 0) return null;
		if (hasFlag())
			return avoid( possibleMoveLocations, getTeam().getFlag().getLocation() );
		Flag theirFlag = this.getTeam().getOpposingTeam().getFlag();
		return avoid( possibleMoveLocations, theirFlag.getLocation() );
	}
	
	public Location avoid(List<Location> scan, Location target) {
		for(Location test : scan) {
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
		}
		//determine optimal direction
		int minDir = 360;
		Location best = scan.get(0);
		for(Location l : scan) {
			int a = this.getLocation().getDirectionToward(l);
			if(Math.abs(this.getLocation().getDirectionToward(target)-a) < minDir) {
				best = l;
				minDir = a;
			}
		}
		return best;
	}

}
