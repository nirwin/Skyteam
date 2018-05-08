package org.pumatech.teams.Skynet;

import java.util.ArrayList;
import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public abstract class SkynetDupe extends AbstractPlayer {

	public SkynetDupe(Location startLocation) {
		super(startLocation);
	}
		// Updates static variables of other players and defends the flag against
		// attackers

		public Location getMoveLocation() {

			// importing enemy players into ArrayList a
			List<AbstractPlayer> a = this.getTeam().getOpposingTeam().getPlayers();
			List<AbstractPlayer> b = this.getTeam().getPlayers();
			AbstractPlayer sample = null;
			AbstractPlayer sample2 = null;
			AbstractPlayer sample3 = null;
			for (AbstractPlayer p : b) {
				if (p instanceof T1K) {
					if (sample != null) {
						sample2 = p;
					} else {
						sample = p;
					}
					if (p instanceof T850) {
						sample3 = p;
					}
				}
			}

			// putting length from flag into closest
			int flagc = getTeam().getFlag().getLocation().getCol();
			int flagr = getTeam().getFlag().getLocation().getRow();
			ArrayList<Integer> Closest = new ArrayList<Integer>();

			// creating a array of the distance of enemy players to flags

			// calculate closest player(s)
			
			// Choosing T1k targets

			// choosing target for T850

			if (ClosestD <= 24 && this.getTeam().onSide(a.get(ClosestID).getLocation()) == true) {
				//loc2 = a.get(ClosestID).getLocation();
				//System.out.println("sent");
			} else {
				//loc2 = null;

				// send each t1k to an opposite corner of flag by giving them an empty variable
			}
			int eo = 0;
			Location a1 = new Location(0, 0);
			Location a2 = new Location(50, 100);
			if (eo % 2 <= 0) {
				eo++;
				return a1;
			} else {
				eo++;
			}
			return a2;
		}
}
