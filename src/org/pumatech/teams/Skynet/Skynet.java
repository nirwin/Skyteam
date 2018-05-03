package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class Skynet extends AbstractPlayer {
	// Updates static variables of other players and defends the flag against
	// attackers
	public Skynet(Location startLocation) {
		super(startLocation);
	}

	public static Location T1kLoc;
	public static Location T850Loc;
	private int eo = 1;

	public static Location getT1kLoc() {
		return T1kLoc;
	}

	public static void setT1kLoc(Location t1kLoc) {
		T1kLoc = t1kLoc;
	}

	public static Location getT850Loc() {
		return T850Loc;
	}

	public static void setT850Loc(Location t850Loc) {
		T850Loc = t850Loc;
	}

	public Location getMoveLocation() {

		// importing enemy players into ArrayList a
		List<AbstractPlayer> a = this.getTeam().getOpposingTeam().getPlayers();

		// putting length form flag into closest
		int flagc = getTeam().getFlag().getLocation().getCol();
		int flagr = getTeam().getFlag().getLocation().getRow();
		ArrayList<Integer> Closest = new ArrayList<Integer>();

		// creating a array of the distance of enemy players to flags

		for (int i = 0; i < a.size(); i++) {
			int x = a.get(i).getLocation().getCol();
			int y = a.get(i).getLocation().getRow();
			int distance = (int) Math.sqrt(((flagc - x) * (flagc - x)) + ((flagr - y) * (flagr - y)));
			Closest.add(i, distance);

		}
		int ClosestID = 0;
		int ClosestD = 100;

		// calculate closest player(s)
		for (int i = 0; i < Closest.size(); i++) {
			if (Closest.get(i) < ClosestD) {
				ClosestID = i;
				ClosestD = Closest.get(i);

			}

		}

		// Choosing T1k targets

		if (ClosestD <= 8 && this.getTeam().onSide(a.get(ClosestID).getLocation()) == true) {
			// send target to t1k's
			T1kLoc = a.get(ClosestID).getLocation();
		} else {
			T1kLoc = null;
			// send each t1k to an opposite corner of flag by giving them an empty variable
		}

		// choosing target for T850

		if (ClosestD <= 24 && this.getTeam().onSide(a.get(ClosestID).getLocation()) == true) {
			T1kLoc = a.get(ClosestID).getLocation();

		} else {
			T1kLoc = null;

			// send each t1k to an opposite corner of flag by giving them an empty variable
		}
		Location a1 = new Location(0, 0);
		Location a2 = new Location(50, 100);
		if (eo % 2 <= 0) {
			eo++;
			return a1;
		} else {
			eo++;
			return a2;
		}

	}

}
