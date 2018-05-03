package org.pumatech.ctf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.pumatech.teams.Skynet.SkynetTeam;
import org.pumatech.teams.sample.SampleTeam;

public class TournamentRunner {

	public static void main(String[] args) {
		List<Team> teams = new ArrayList<Team>();
		teams.add(new SkynetTeam("Skynet A",Color.RED));
		teams.add(new SampleTeam("Sample A",Color.GREEN));
		//teams.add(new SkynetTeam("Skynet B",Color.ORANGE));
		teams.add(new SampleTeam("Sample H",Color.ORANGE));
		teams.add(new SampleTeam("Sample B",Color.BLUE));
		teams.add(new SampleTeam("Sample E",Color.YELLOW));
		teams.add(new SampleTeam("Sample F",Color.CYAN));
		teams.add(new SampleTeam("Sample G",Color.MAGENTA));
		teams.add(new SampleTeam("Sample H",Color.PINK));
		
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
