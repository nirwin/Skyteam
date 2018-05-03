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
	}


	public Location getMoveLocation() {
		if (T850Loc == null){
			return this.getLocation();	
			
		}
		else {
			return T850Loc;
		
		}
	}


	@Override
	public Location getMoveLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	


