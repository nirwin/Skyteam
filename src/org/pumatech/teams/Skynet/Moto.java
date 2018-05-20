package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.*;

import info.gridworld.actor.*;
import info.gridworld.grid.Location;

/*
This is Skynet's main offensive player.
This class is my pride and joy. Break it and I might do bad things to you. 
-Jeffrey Collins
*/

public class Moto extends MovingPlayer {

	private Location pastLocation;
	private ArrayList<Location> stuckOnYou = new ArrayList<Location>();

	public Moto(Location startLocation) {
		super(startLocation);
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
		if (stuckOnYou.size() > blacklistSize) {
			for (int i = 0; i < stuckOnYou.size() - blacklistSize; i++) {
				stuckOnYou.remove(stuckOnYou.size() - 1);
			}
		}
		if (hasFlag()) {
			return avoid(possibleMoveLocations, this.getTeam().getFlag().getLocation());
		}
		return avoid(possibleMoveLocations, this.getTeam().getOpposingTeam().getFlag().getLocation());
	}

	public Location avoid(List<Location> scan, Location target) {
		if (scan.size() <= 0) {
			return this.getLocation();
		}
		ArrayList<Location> temp = new ArrayList<Location>(scan);
		for (Location test : scan) {
			if (locationBlacklist.contains(test)) {
				temp.remove(test);
			}
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
		scan = temp;

		if (scan.size() <= 0) {
			return this.getLocation();
		}

		// determine optimal direction
		int minDir = 360;
		Location best = scan.get(0);
		for (Location l : scan) {
			int a = this.getLocation().getDirectionToward(l);
			int t = this.getLocation().getDirectionToward(target);
			if (Math.abs(t - a) < minDir) {
				if (this.getGrid().getEmptyAdjacentLocations(l).size() > 1) {
					if (!locationBlacklist.contains(l) && !l.equals(pastLocation)) {
						best = l;
					}
				}
				minDir = Math.abs(t - a);
			}
		}
		if (Math.abs(
				this.getLocation().getDirectionToward(best) - this.getLocation().getDirectionToward(target)) >= 90) {
			if (!locationBlacklist.contains(best)) {
				locationBlacklist.add(best);
			}
		}
		if (best.equals(pastLocation)) {
			if (!locationBlacklist.contains(best)) {
				locationBlacklist.add(best);
				if (!stuckOnYou.contains(best)) {
					stuckOnYou.add(best);
				}
			}
		}
		pastLocation = this.getLocation();
		return best;
	}

	public void takeOnMe() {
		// print out game map
		List<Location> occupied = this.getGrid().getOccupiedLocations();
		for (int row = 0; row < this.getGrid().getNumRows(); row++) {
			for (int col = 0; col < this.getGrid().getNumCols(); col++) {
				Location scan = new Location(row, col);
				if (occupied.contains(scan)) {
					// occupied space
					Actor obj = this.getGrid().get(scan);
					if (obj instanceof Rock) {
						System.out.print("#");
					} else if (obj instanceof Moto) {
						System.out.print("M");
					} else if (obj instanceof T850) {
						System.out.print("A");
					} else if (obj instanceof T1K) {
						System.out.print("T");
					} else if (obj instanceof SkynetDupe) {
						System.out.print("S");
					} else if (obj instanceof Flag) {
						System.out.print("F");
					}
				} else if (locationBlacklist.contains(scan)) {
					System.err.print("#");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}

}
