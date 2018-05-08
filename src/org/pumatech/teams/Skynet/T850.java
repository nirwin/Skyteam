package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class T850 extends AbstractPlayer {

	// processes a list of targets and moves to intercept, then returns
	// to the flag if there are no more targets given to it by Skynet
	
	private ArrayList<Location> targets = new ArrayList<Location>();
	
	public T850(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		//get closest target
		Location best = this.getLocation();
		int minDist = 100;
		for(Location l : targets) {
			int dx = Math.abs(l.getCol()-this.getLocation().getCol());
			int dy = Math.abs(l.getRow()-this.getLocation().getRow());
			if((int)Math.sqrt(((double)dx) + ((double)dy)) < minDist) {
				minDist = (int)Math.sqrt(((double)dx) + ((double)dy));
				best = l;
			}
		}
		//if closest is tagged, remove from list
		
		
		List<Location> possibleMoveLocations = this.getGrid().getEmptyAdjacentLocations(getLocation()); 
		//plot a course to intercept
		// return avoid( possibleMoveLocations, best );
		return best;
		/*System.out.println("t850");
		if (loc1 == null) {
			System.out.println("loc 1 sent");
			return this.getLocation();
		} else {
			System.err.println("null for loc 1");
			return loc1;

		}*/
	}
	
	public void setTarget(Location targ) {
		targets.add(targ);
	}
}

