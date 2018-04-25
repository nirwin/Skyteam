package org.pumatech.teams.Skynet;

import java.awt.Color;
import org.pumatech.ctf.Team;
//import org.pumatech.teams.sample.BeelinePlayer;
import org.pumatech.teams.sample.RandomPlayer;

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
		addPlayer(new T850(new Location(5, 30)));
		addPlayer(new T850(new Location(10, 30)));
		addPlayer(new T850(new Location(15, 30)));
		addPlayer(new T850(new Location(20, 30)));
		addPlayer(new T850(new Location(30, 30)));
		addPlayer(new T850(new Location(35, 30)));
		addPlayer(new T850(new Location(40, 30)));
		addPlayer(new T850(new Location(45, 30)));
	}
}
