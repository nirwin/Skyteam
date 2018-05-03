package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.*;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

/* This is Skynet's main offensive player. */

public class Moto extends AbstractPlayer {
	
	private Location pastLocation;
	static ArrayList<Location> locationBlacklist = new ArrayList<Location>();
	static ArrayList<Integer> decayBlacklist = new ArrayList<Integer>();
	
	public Moto(Location startLocation) {
		super(startLocation);
		pastLocation = this.getLocation();
	}

	public Location getMoveLocation() {
		List<Location> possibleMoveLocations = this.getGrid().getEmptyAdjacentLocations(getLocation()); 
		if (possibleMoveLocations.size() == 0) { return null; }
		/*if(!decayBlacklist.isEmpty() && !locationBlacklist.isEmpty()) {
			ArrayList<Integer> temp = new ArrayList<Integer>(decayBlacklist);
			for(int i=0; i<decayBlacklist.size(); i++) {
				if((int)(decayBlacklist.get(i)) > 0) {
					temp.set(i,new Integer(decayBlacklist.get(i)-1));
				}else {
					temp.remove(i);
					i--;
				}
			}
			decayBlacklist = temp;
		}*/
		if(decayBlacklist.size() > 4) {
			for(int i=0; i<decayBlacklist.size()-4; i++) {
				decayBlacklist.remove(decayBlacklist.size()-1);
			}
		}
		if (hasFlag()) {
			return avoid( possibleMoveLocations, this.getTeam().getFlag().getLocation() );
		}
		return avoid( possibleMoveLocations, this.getTeam().getOpposingTeam().getFlag().getLocation() );
	}
	
	public Location avoid(List<Location> scan, Location target) {
		ArrayList<Location> temp = new ArrayList<Location>(scan);
		for(Location test : scan) {
			for(Location temmie : locationBlacklist) {
				if(test == temmie/*pastLocation*/) {
					temp.remove(test);
				}
			}
			if(test.getCol() != this.getLocation().getCol() && test.getRow() != this.getLocation().getRow()) {
				//test for attacker 'auras'
				List<AbstractPlayer> theirPlayers = this.getTeam().getOpposingTeam().getPlayers();
				for(AbstractPlayer detect : theirPlayers) {
					if(this.getGrid().get(test) == detect) {
						temp.remove(test);
					}
					for(Actor a : this.getGrid().getNeighbors(detect.getLocation())) {
						if(a.equals(detect)) {
							temp.remove(test);
						}
						//System.out.println(detect);
						if(detect.getMoveLocation() != null) {
							for(Location tem : getGrid().getEmptyAdjacentLocations(detect.getMoveLocation())) {
								if(a == getGrid().get(tem)) { temp.remove(test); }
							}
						}
					}
				}
			}
		}
		scan = temp;
		
		// Current issue: it's ignoring my pastLocation check
		//determine optimal direction
		int minDir = 360;
		Location best = scan.get(0);
		for(Location l : scan) {
			int a = this.getLocation().getDirectionToward(l);
			int t = this.getLocation().getDirectionToward(target);
			if(Math.abs(t-a) < minDir) {
				//System.out.println("size: "+this.getGrid().getEmptyAdjacentLocations(l).size());
				if(this.getGrid().getEmptyAdjacentLocations(l).size() > 1 && 
					Math.abs(this.getLocation().getDirectionToward(l) - this.getLocation().
					getDirectionToward(target)) <= 90) {
					if(!locationBlacklist.contains(l)) {
						best = l;
					}
				}else {
					if(!locationBlacklist.contains(l)) {
						locationBlacklist.add(l);
						decayBlacklist.add(5);
					}
					//System.out.println("Blacklisted "+l);
				}
			minDir = Math.abs(t-a);
			if(l.equals(pastLocation)) {
				if(!locationBlacklist.contains(l)) {
					locationBlacklist.add(l);
					decayBlacklist.add(5);
				}
			}
			}
		}
		//System.out.println("Blacklist: "+locationBlacklist);
		pastLocation = this.getLocation();
		return best;
	}
	
}
