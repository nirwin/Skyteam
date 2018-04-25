package org.pumatech.teams.Skynet;

import java.awt.Color;
import org.pumatech.ctf.Team;

import info.gridworld.grid.Location;

public class SkynetTeam extends Team{
	public SkynetTeam() {
		this(Color.DARK_GRAY);
	}
	
	public SkynetTeam(Color color) {
		this("Team Skynet", color);
	}
	
	public SkynetTeam(String name, Color color) {
		super(name, color);
		addPlayer(new Snowminator(new Location(5, 30)));
		addPlayer(new Snowminator(new Location(10, 30)));
		addPlayer(new Snowminator(new Location(15, 30)));
		addPlayer(new Snowminator(new Location(20, 30)));
		addPlayer(new Snowminator(new Location(30, 30)));
		addPlayer(new Snowminator(new Location(35, 30)));
		addPlayer(new Snowminator(new Location(40, 30)));
		addPlayer(new Snowminator(new Location(45, 30)));
	}
}
