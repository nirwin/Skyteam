package org.pumatech.ctf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.pumatech.teams.Skynet.SkynetTeam;
import org.pumatech.teams.Resistance.ResistTeam;
import org.pumatech.teams.sample.SampleTeam;

public class TournamentRunner {

	public static void main(String[] args) {
		List<Team> teams = new ArrayList<Team>();
		// chaos vs. serenity
		teams.add(new SampleTeam("Blender Blimp", Color.RED));
		teams.add(new ResistTeam("Karmadillo", Color.ORANGE));
		// APCS jokes
		teams.add(new ResistTeam("Herbert", Color.YELLOW));
		teams.add(new SkynetTeam("Squiggum", Color.BLUE));
		// indifference vs. panic
		teams.add(new SampleTeam("Eh", Color.PINK));
		teams.add(new ResistTeam("AAAAAAAAAAAAAAAA", Color.CYAN));
		// Exploding Kittens
		teams.add(new ResistTeam("Defuse", new Color(153, 255, 0)));
		teams.add(new SkynetTeam("Exploding Kitten"/* Color.WHITE */));
		// programming jokes
		teams.add(new SkynetTeam("College Board", Color.BLACK));
		teams.add(new ResistTeam("Git", Color.MAGENTA));
		// IDK
		teams.add(new SkynetTeam("Totodiles", new Color(0, 204, 255)));
		teams.add(new SampleTeam("StackOverflow", new Color(255, 102, 0)));
		// *Terminator joke*
		teams.add(new SkynetTeam("Skynet", Color.WHITE));
		teams.add(new ResistTeam("Resistance", Color.LIGHT_GRAY));
		// Eh
		teams.add(new SkynetTeam("IDK LOL", new Color(255, 153, 51)));
		teams.add(new SampleTeam("Human Geography", new Color(102, 102, 0)));

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
