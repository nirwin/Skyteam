package org.pumatech.ctf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.pumatech.teams.Skynet.SkynetTeam;
//import org.pumatech.teams.sample.SampleTeam;

public class TournamentRunner {

	public static void main(String[] args) {
		List<Team> teams = new ArrayList<Team>();
		teams.add(new SkynetTeam("Blender Blimp",Color.RED));
		teams.add(new SkynetTeam("Karmadillo",Color.BLUE));
		teams.add(new SkynetTeam("Exploding Kitten"/*Color.WHITE*/));
		teams.add(new SkynetTeam("Herbert",Color.YELLOW));
		teams.add(new SkynetTeam("AAAAAAAAAAAAAAAA",Color.CYAN));
		teams.add(new SkynetTeam("Skynet",Color.WHITE));
		
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
