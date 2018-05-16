package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.*;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

/*
This is Skynet's main offensive player.
This class is my pride and joy. Break it and I will do bad things to you. 
-Jeffrey Collins
*/

public class Moto extends MovingPlayer {

	private Location pastLocation;
	private ArrayList<Location> stuckOnYou = new ArrayList<Location>();
	// static ArrayList<Location> locationBlacklist = new ArrayList<Location>();

	public Moto(Location startLocation) {
		super(startLocation);
		// pastLocation = this.getLocation();
	}

	public Location getMoveLocation() {
		List<Location> possibleMoveLocations = this.getGrid().getEmptyAdjacentLocations(getLocation());
		if (possibleMoveLocations.size() == 0) {
			return null;
		}
		if (locationBlacklist.size() > blacklistSize) {
			for (int i = 0; i < locationBlacklist.size() - blacklistSize; i++) {
				locationBlacklist.remove(locationBlacklist.size() - 1);
			}
		}
		if (hasFlag()) {
			return avoid(possibleMoveLocations, this.getTeam().getFlag().getLocation());
		}
		return avoid(possibleMoveLocations, this.getTeam().getOpposingTeam().getFlag().getLocation());
	}

	public Location avoid(List<Location> scan, Location target) {
		ArrayList<Location> temp = new ArrayList<Location>(scan);
		for (Location test : scan) {
			for (Location temmie : locationBlacklist) {
				if (test == temmie) {
					temp.remove(test);
				}
			}
			if (test.getCol() != this.getLocation().getCol() && test.getRow() != this.getLocation().getRow()) {
				// test for attacker 'auras'
				List<AbstractPlayer> theirPlayers = this.getTeam().getOpposingTeam().getPlayers();
				for (AbstractPlayer detect : theirPlayers) {
					if (this.getGrid().get(test) == detect) {
						temp.remove(test);
					}
					for (Actor a : this.getGrid().getNeighbors(detect.getLocation())) {
						if (a.equals(detect)) {
							temp.remove(test);
						}
						if (!(detect.getTeam() instanceof SkynetTeam)) {
							if (detect.getMoveLocation() != null) {
								for (Location tem : getGrid().getEmptyAdjacentLocations(detect.getMoveLocation())) {
									if (a == getGrid().get(tem)) {
										temp.remove(test);
									}
								}
							}
						}
					}
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
					if (!locationBlacklist.contains(l) && !l.equals(pastLocation)) {
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
						stuckOnYou.add(l);
						//System.out.println("added "+l+", past = "+pastLocation);
					}
				}
			}
		}
		pastLocation = this.getLocation();
		return best;
	}
	
	public String toString() //sample text
	{
		return (/*super.toString() + */"Stuck on " + stuckOnYou);
	}

}
