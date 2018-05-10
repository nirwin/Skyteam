package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class T850 extends AbstractPlayer {

	// processes a list of targets and moves to intercept, then returns
	// to the flag if there are no more targets given to it by Skynet

	private ArrayList<Location> targets = new ArrayList<Location>();
	private Location post;
	private Location pastLocation;
	static ArrayList<Location> locationBlacklist = new ArrayList<Location>();

	public T850(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		// eliminate targets not on side
		ArrayList<Location> temp = new ArrayList<Location>(targets);
		for (Location enemy : targets) {
			if (!this.getTeam().onSide(enemy)) {
				temp.remove(enemy);
			}
		}
		targets = temp;

		// path finding
		List<Location> possibleMoveLocations = this.getGrid().getEmptyAdjacentLocations(getLocation());
		if (possibleMoveLocations.size() <= 0) {
			return null;
		}
		if (locationBlacklist.size() > 8) {
			for (int i = 0; i < locationBlacklist.size() - 4; i++) {
				locationBlacklist.remove(locationBlacklist.size() - 1);
			}
		}
		if (targets.size() > 0) {
			System.out.println("T850 Targets:"+targets);
			return avoid(possibleMoveLocations, targets.get(0));
		} else {
			return avoid(possibleMoveLocations, post);
		}
	}

	public void addTarget(Location targ) {
		targets.add(targ);
	}

	public void setPost(Location def) {
		post = def;
	}

	public Location avoid(List<Location> scan, Location target) {
		if(target == null) { return this.getLocation(); }
		ArrayList<Location> temp = new ArrayList<Location>(scan);
		for (Location test : scan) {
			for (Location temmie : locationBlacklist) {
				if (test == temmie) {
					temp.remove(test);
				}
			}
		}
		scan = temp;

		// determine optimal direction
		int minDir = 360;
		Location best = scan.get(0);
		for (Location l : scan) {
			int a = this.getLocation().getDirectionToward(l);
			int t = this.getLocation().getDirectionToward(target);
			if (Math.abs(t - a) < minDir) {
				if (this.getGrid().getEmptyAdjacentLocations(l).size() > 1
						&& Math.abs(this.getLocation().getDirectionToward(l)
								- this.getLocation().getDirectionToward(target)) <= 90) {
					if (!locationBlacklist.contains(l)) {
						best = l;
					}
				} else {
					if (!locationBlacklist.contains(l)) {
						locationBlacklist.add(l);
					}
				}
				minDir = Math.abs(t - a);
				if (l.equals(pastLocation)) {
					if (!locationBlacklist.contains(l)) {
						locationBlacklist.add(l);
					}
				}
			}
		}
		pastLocation = this.getLocation();
		return best;
	}
}
