package org.pumatech.teams.Resistance;

import java.awt.Color;
import org.pumatech.ctf.Team;

import info.gridworld.grid.Location;

public class ResistTeam extends Team{
	public ResistTeam() {
		this("Resistance", Color.DARK_GRAY);
	}
	
	public ResistTeam(String name) {
		this(name, Color.DARK_GRAY);
	}
	
	public ResistTeam(Color color) {
		this("Resistance", color);
	}
	
	public ResistTeam(String name, Color color) {
		super(name, color);
		addPlayer(new Moto(new Location(5, 30)));
		addPlayer(new Moto(new Location(10, 30)));
		addPlayer(new T1K(new Location(15, 30)));
		addPlayer(new SkynetDupe(new Location(20, 30)));
		addPlayer(new T850(new Location(30, 30)));
		addPlayer(new T1K(new Location(35, 30)));
		addPlayer(new Moto(new Location(40, 30)));
		addPlayer(new Moto(new Location(45, 30)));
	}
}
