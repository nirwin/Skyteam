package org.pumatech.teams.Skynet;

import java.util.List;

import org.pumatech.ctf.AbstractPlayer;

import info.gridworld.grid.Location;

public class T850 extends AbstractPlayer {
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

	@Override
	public Location getMoveLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
