package org.pumatech.teams.Skynet;

import java.util.List;
import org.pumatech.ctf.AbstractPlayer;
import info.gridworld.grid.Location;

public class T850 extends SkynetSTC {
	// Since there is only going to be one, can we name him Arnold?�
	// processes a list of targets and moves to intercept, then returns
	// to the flag if there are no more targets given to it by Skynet
	public T850(Location startLocation) {
		super(startLocation);
		this.setTarget(null);
	}
	public T850(Location startLocation, Location target) {
		super(startLocation);
		this.setTarget(target);
	}

	private Location pastLocation;
	public static Location target;

	public void setTarget(Location l) {
		target = l;
	}

	public Location getMoveLocation() {
		List<Location> possibleMoveLocations = this.getGrid().getEmptyAdjacentLocations(getLocation()); 
		if(target != null && this.getGrid().get(target) != null) {
			//path to target
			return goTowards(possibleMoveLocations, target);
		}else if(this.getLocation() != this.getTeam().getFlag().getLocation()){
			//path to flag
			return goTowards(possibleMoveLocations, this.getTeam().getFlag().getLocation());
		}else {
			return this.getLocation();
		}
	}
	
	public Location goTowards(List<Location> scan, Location target) {
		// determine optimal direction
		int minDir = 360;
		Location best = scan.get(0);
		for(Location l : scan) {
			int a = this.getLocation().getDirectionToward(l);
			int t = this.getLocation().getDirectionToward(target);
			if(Math.abs(t-a) < minDir) {
				if(getGrid().getEmptyAdjacentLocations(l).size() > 1 && l != pastLocation) {
					best = l;
					pastLocation = this.getLocation();
				}
				minDir = Math.abs(t-a);
			}
		}
		return best;
	}
}
