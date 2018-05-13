package org.pumatech.ctf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.pumatech.teams.Skynet.SkynetTeam;
import org.pumatech.teams.Resistance.ResistTeam;
//import org.pumatech.teams.sample.SampleTeam;

public class TournamentRunner {

	public static void main(String[] args) {
		List<Team> teams = new ArrayList<Team>();
		// chaos vs. serenity
		teams.add(new SkynetTeam("Blender Blimp",Color.RED));
		teams.add(new ResistTeam("Karmadillo",Color.ORANGE));
		// programming jokes
		teams.add(new ResistTeam("Herbert",Color.YELLOW));
		teams.add(new SkynetTeam("Squiggum",Color.BLUE));
		// indifference vs. panic
		teams.add(new SkynetTeam("Eh",Color.BLACK));
		teams.add(new ResistTeam("AAAAAAAAAAAAAAAA",Color.CYAN));
		// battle of the great destroyers
		teams.add(new SkynetTeam("Skynet",Color.WHITE));
		teams.add(new ResistTeam("Exploding Kitten"/*Color.WHITE*/));
		
		Bracket bracket = new Bracket(teams);
		JFrame bracketViewer = new JFrame("Capture The Flag 2018 Bracket");
		bracketViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bracketViewer.setResizable(false);
		bracketViewer.add(bracket);
		bracketViewer.pack();
		bracketViewer.setLocationRelativeTo(null);
		bracketViewer.setVisible(true);
		
		bracket.getWinner();
	}
	
}
