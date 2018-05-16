package org.pumatech.teams.Skynet;


import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class T1K extends MovingPlayer {
	// Stays around the flag and moves to intercept opposing players within a square
	
	private ArrayList<AbstractPlayer> targets = new ArrayList<AbstractPlayer>();
	private Location post;
	private Location pastLocation;
	//static ArrayList<Location> locationBlacklist = new ArrayList<Location>();
	
	public T1K(Location startLocation) {
		super(startLocation);
		//pastLocation = this.getLocation();
	}

	public Location getMoveLocation() {
		// eliminate targets not on side
		ArrayList<AbstractPlayer> temp = new ArrayList<AbstractPlayer>(targets);
		for (AbstractPlayer enemy : targets) {
			if (!this.getTeam().onSide(enemy.getLocation())) {
				temp.remove(enemy);
			}
		}
		targets = temp;

		// path finding
		List<Location> possibleMoveLocations = this.getGrid().getEmptyAdjacentLocations(getLocation());
		if (possibleMoveLocations.size() <= 0) {
			return null;
		}
		/*if (locationBlacklist.size() > blacklistSize) {
			for (int i = 0; i < locationBlacklist.size() - blacklistSize; i++) {
				locationBlacklist.remove(locationBlacklist.size() - 1);
			}
		}*/
		if (targets.size() > 0) {
			return avoid(possibleMoveLocations, targets.get(0).getLocation());
		} else if(post != null){
			//System.out.println(this+" returning to post "+post);
			return avoid(possibleMoveLocations, post);
		} else {
			return this.getLocation();
		}
	}
	
	public void addTarget(AbstractPlayer targ) {
		targets.add(targ);
	}
	
	public void setPost(Location def) {
		post = def;
	}
	
	public Location avoid(List<Location> scan, Location target) {
		ArrayList<Location> temp = new ArrayList<Location>(scan);
		for (Location test : scan) {
			if (locationBlacklist.contains(test)) {
				temp.remove(test);
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
						if (this.getGrid().getEmptyAdjacentLocations(l).size() > 1) {
							if (!locationBlacklist.contains(l) && !l.equals(pastLocation)) {
								best = l;
							}
						}
						// originally here
						minDir = Math.abs(t - a);
					}
				}
				if (Math.abs(this.getLocation().getDirectionToward(best) - 
						this.getLocation().getDirectionToward(target)) >= 90) {
					// new Rock().putSelfInGrid(this.getGrid(),best);
					if (!locationBlacklist.contains(best)) {
						locationBlacklist.add(best);
					}
				}
				if (best.equals(pastLocation)) {
					if (!locationBlacklist.contains(best)) {
						locationBlacklist.add(best);
						// System.out.println("added "+l+", past = "+pastLocation);
					}
				}
				pastLocation = this.getLocation();
				return best;
	}
}
