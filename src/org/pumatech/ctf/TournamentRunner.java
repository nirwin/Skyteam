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
		// *Terminator joke*
		teams.add(new SkynetTeam("Skynet", Color.WHITE));
		teams.add(new SkynetTeam("Resistance", Color.LIGHT_GRAY));
		// chaos vs. serenity
		teams.add(new SampleTeam("Blender Blimp", Color.RED));
		teams.add(new SkynetTeam("Karmadillo", Color.ORANGE));
		// APCS jokes
		teams.add(new SkynetTeam("Herbert", Color.YELLOW));
		teams.add(new SkynetTeam("Squiggum", Color.BLUE));
		// indifference vs. panic
		teams.add(new SampleTeam("Eh", Color.PINK));
		teams.add(new SkynetTeam("AAAAAAAAAAAAAAAA", Color.CYAN));
		// Exploding Kittens
		/*
		teams.add(new SkynetTeam("Defuse", new Color(153, 255, 0)));
		teams.add(new SkynetTeam("Exploding Kitten"));
		// programming jokes
		teams.add(new SkynetTeam("College Board", Color.BLACK));
		teams.add(new SampleTeam("Git", Color.MAGENTA));
		// IDK
		teams.add(new SkynetTeam("Totodiles", new Color(0, 204, 255)));
		teams.add(new SampleTeam("StackOverflow", new Color(255, 102, 0)));
		// Eh
		teams.add(new SkynetTeam("IDK LOL", new Color(255, 153, 51)));
		teams.add(new SampleTeam("Human Geography", new Color(102, 102, 0)));
		*/

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
