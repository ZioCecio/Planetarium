package center.of.mass;

import java.util.ArrayList;

import it.unibs.fp.mylib.*;

public class StarSystem {
	private static final String ID_IS = "The id is:";
	private static final String CENTRE_OF_MASS = "The centre of mass is:";
	private static final String TITLE = "Welcome to the StarSystem";
	private static final String[] ITEMS = { "Add a new celestial body", "Remove a celestial body",
			"Look for a celestial body ", "Display a planet's moons", "Display the path to reach a moon",
			"Calculate center of mass" };
	private static final String[] ITEMS_2 = { "to enter a planet", "to enter a moon" };
	private static final String NOTHING_FOUND = "Nothing has been found";
	public static final Position CENTER = new Position(0, 0);

	/**
	 * central menu displayed: consists of a main and a secondary menu the label
	 * variable refers to the various actions decided in the main menu
	 * 
	 * @param
	 * @author Alessandra
	 */
	public void displayMenu(Star star) {
		MyMenu mainMenu = new MyMenu(TITLE, ITEMS);
		int label = 0;
		boolean exit = false;
		do {
			it.unibs.fp.mylib.MyTime.wait(1);
			int decision = mainMenu.scegli();
			switch (decision) {
			case 0:
				exit = true;
				break;
			case 1:
				label = 1;
				subMenu(star, label);
				break;
			case 2:
				label = 2;
				subMenu(star, label);
				break;
			case 3:
				this.lookFor(star);
				break;
			case 4:
				Planet p1 = new Planet();
				p1.displayMoons();
				break;
			case 5:

				break;
			case 6:
				System.out.println(CENTRE_OF_MASS + calcCentreMass(star));
				break;

			}
		} while (!exit);
	}

	/**
	 * <b>A submenu </b>that leads to available subsections
	 * 
	 * @param
	 * @author Alessandra
	 */
	public void subMenu(Star star, int label) {
		MyMenu subMenu = new MyMenu(TITLE, ITEMS_2);
		boolean exit = false;
		do {
			int decision = subMenu.scegli();
			switch (decision) {
			case 0:
				exit = true;
				break;
			case 1:
				star.actionPlanet(label);
				exit = true;
				break;
			case 2:
				Planet p1 = PlanetariumUtils.readPlanetLinkedTo(star);
				p1.actionMoon(label);
				exit = true;
				break;
			}
		} while (!exit);

	}

	/***
	 * <h1>Method to calculate the center of mass</h1> I attach the formula source
	 * in two-dimensional case in "@See" section
	 * @see<a href=" https://www.khanacademy.org/science/physics/linear-momentum/center-of-mass/a/what-is-center-of-mass">Center
	 *        Of Mass </a>
	 * 
	 * @author Alessandra, edited by Simone
	 */
	public String calcCentreMass(Star s) {
		double partialX = 0;
		double mass = 0;
		double partialY = 0;
		double totMass = 0;
		ArrayList<Planet> p = (s.getPlanets());
		for (int i = 0; i < p.size(); i++) {
			mass = p.get(i).getMass();
			partialX += (p.get(i).getPosition().getX()) * mass;
			partialX += p.get(i).partialXMoon();
			partialY += (p.get(i).getPosition().getY()) * mass;
			partialY += p.get(i).partialYMoon();
			mass = 0;// set to 0 for next cycle
		}
		totMass = totalMass(s, p);
		return new Position(partialX / totMass, partialY / totMass).toString();
	}

	/*
	 * 
	 * suddivido la gestione del numeratore e del denominatore (metodo sottostante)
	 * per brevita di scrittura e il calcolo della massa totale potrebbe
	 * potenzialmente avere un riutilizzo
	 */
	public double totalMass(Star s, ArrayList<Planet> p) {

		double totalMass = s.getMass();
		for (int i = 0; i < p.size(); i++) {
			totalMass += p.get(i).getMass();
			totalMass += p.get(i).totalMassMoon();
		}
		return totalMass;
	}

	/**
	 * Look for a {@linkplain Planet} in a {@linkplain Position}, request in input
	 * 
	 * @see PlanetariumUtils#readPosition
	 */
	public void lookFor(Star s) {
		Position pos = PlanetariumUtils.readPosition();
		try {
			String id = s.lookForPlanet(pos).getId();
			System.out.println(ID_IS + id);
		} catch (Exception NullPointerException) {
			System.out.println(NOTHING_FOUND);

		}
	}
}
