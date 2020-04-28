package center.of.mass;

import java.util.ArrayList;

public class StarSystem {
	/**The {@linkplain StarSystem} CENTER {@value }*/
	public static final Position CENTER = new Position(0, 0);

	private Star star;
	private Position centerOfMass;

	public StarSystem(Star star) {
		this.star = star;

		this.centerOfMass = this.calcCenterOfMass();
	}

	/***
	 * <h1>Method to calculate the center of mass</h1> I attach the formula source
	 * in two-dimensional case in "@See" section
	 * @see<a href=" https://www.khanacademy.org/science/physics/linear-momentum/center-of-mass/a/what-is-center-of-mass">Center
	 *        Of Mass </a>
	 * 
	 * @author Alessandra, edited by Simone
	 */
	public Position calcCenterOfMass() {
		double partialX = this.star.getPosition().getX() * this.star.getMass();
		double mass = 0;
		double partialY = this.star.getPosition().getY() * this.star.getMass();
		double totMass = 0;
		ArrayList<Planet> p = this.star.getPlanets();

		for (int i = 0; i < p.size(); i++) {
			mass = p.get(i).getMass();
			partialX += (p.get(i).getPosition().getX()) * mass;
			partialX += p.get(i).partialXMoon();
			partialY += (p.get(i).getPosition().getY()) * mass;
			partialY += p.get(i).partialYMoon();
		}

		totMass = totalMass(this.star, p);

		return new Position(partialX / totMass, partialY / totMass);
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
	 * Add a new {@code Planet} in {@code this} SolarSystem
	 * @param planet
	 */
	public void addPlanet(Planet planet) {
		this.star.addPlanet(planet);
	}

	/**
	 * Add a new {@linkplain Moon} in {@code this} SolarSystem which orbits around
	 * the {@linkplain Planet} specified by the id
	 * @param moon 
	 * @param idOfPlanet
	 */
	public void addMoonToPlanetWithId(Moon moon, String idOfPlanet) {
		Planet planet = this.star.getPlanetById(idOfPlanet);

		if(planet == null) {
			return;
		}

		planet.addMoon(moon);
	}
	
	/**
	 * Add a new {@linkplain Moon} in {@code this} SolarSystem which orbits around
	 * the {@linkplain Planet}  specified by the name
	 * @param moon 
	 * @param nameOfPlanet
	 */
	public void addMoonToPlanetWithName(Moon moon, String nameOfPlanet) {
		Planet planet = this.star.getPlanetByName(nameOfPlanet);

		if(planet == null) {
			return;
		}

		planet.addMoon(moon);
	}

	/**
	 * @return the centerOfMass
	 */
	public Position getCenterOfMass() {
		return centerOfMass;
	}
}
