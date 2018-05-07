package org.pumatech.teams.Skynet;

import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class T850 extends STC {

	// processes a list of targets and moves to intercept, then returns
	// to the flag if there are no more targets given to it by Skynet

	public T850(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		if (loc1 == null) {
			return this.getLocation();

		} else {
			return loc1;

		}
	}

}
