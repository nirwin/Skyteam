package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.*;

import info.gridworld.actor.Actor;
//import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
//import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class T850 extends AbstractPlayer {

	//Location myLoc = this.getLocation();
	Grid<Actor> myGrid = this.getGrid();
	Team myTeam = this.getTeam();
	Team theirTeam = myTeam.getOpposingTeam();
	List<AbstractPlayer> theirPlayers = theirTeam.getPlayers();
	Flag theirFlag = theirTeam.getFlag();
	
	public T850(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		List<Location> possibleMoveLocations = getGrid().getEmptyAdjacentLocations(getLocation()); 
		if (possibleMoveLocations.size() == 0) return null;
		if (hasFlag())
			return avoid( possibleMoveLocations, getTeam().getFlag().getLocation() );
		return avoid( possibleMoveLocations, theirFlag.getLocation() );
	}
	
	public Location avoid(List<Location> scan, Location target) {
		for(Location test : scan) {
			if(test.getCol() != this.getLocation().getCol() && test.getRow() != this.getLocation().getRow()) {
				//test for attacker 'auras'
				for(AbstractPlayer detect : theirPlayers) {
					if(this.getGrid().get(test) == detect) {
						scan.remove(test);
					}
					for(Actor a : myGrid.getNeighbors(test)) {
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
