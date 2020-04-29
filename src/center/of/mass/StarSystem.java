package center.of.mass;

import java.util.ArrayList;

import exceptions.CelestialBodyNotFoundException;
import exceptions.InvalidPositionException;

public class StarSystem {
	/**The {@linkplain StarSystem} CENTER {@value }*/
	public static final Position CENTER = new Position(0, 0);

	private Star star;
	private Position centerOfMass;

	/**
	 * Constructor of {@code StartSystem}
	 * @author Gabriele
	 * @param star
	 */
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
	 * @author Gabriele
	 * @param planet
	 * @throws InvalidPositionException if the position of the planet is already occupied by another celestial body
	 */
	public void addPlanet(Planet planet) throws InvalidPositionException {
		if(!isValidPosition(planet.getPosition())) {
			throw new InvalidPositionException("The position of the planet is already occupied by another celestial body");
		}

		this.star.addPlanet(planet);
	}

	/**
	 * Add a new {@linkplain Moon} in {@code this} SolarSystem which orbits around
	 * the {@linkplain Planet} specified by the id
	 * @author Gabriele
	 * @param moon 
	 * @param idOfPlanet
	 * @throws InvalidPositionException if the position of the moon is already occupied by another celestial body
	 * @throws CelestialBodyNotFoundException if the planet with the specified id doesn't exist
	 */
	public void addMoonToPlanetWithId(Moon moon, String idOfPlanet) throws InvalidPositionException, CelestialBodyNotFoundException {
		if(!isValidPosition(moon.getPosition())) {
			throw new InvalidPositionException("The position of the moon is already occupied by another celestial body");
		}

		Planet planet = this.star.getPlanetById(idOfPlanet);

		if(planet == null) {
			throw new CelestialBodyNotFoundException("The planet with the spiecified id doesn't exist");
		}

		planet.addMoon(moon);
	}
	
	/**
	 * Add a new {@linkplain Moon} in {@code this} SolarSystem which orbits around
	 * the {@linkplain Planet}  specified by the name
	 * @author Gabriele
	 * @param moon 
	 * @param nameOfPlanet
	 * @throws InvalidPositionException if the position of the planet is already occupied by another celestial body
	 * @throws CelestialBodyNotFoundException if the planet with the specified name doesn't exist
	 */
	public void addMoonToPlanetWithName(Moon moon, String nameOfPlanet) throws InvalidPositionException, CelestialBodyNotFoundException {
		if(!isValidPosition(moon.getPosition())) {
			throw new InvalidPositionException("The position of the moon is already occupied by another celestial body");
		}

		Planet planet = this.star.getPlanetByName(nameOfPlanet);

		if(planet == null) {
			throw new CelestialBodyNotFoundException("The planet with the spiecified name doesn't exist");
		}

		planet.addMoon(moon);
	}

	/**
	 * Get all the {@code Planet} of the {@code StarSystem}
	 * @return the planets
	 */
	public ArrayList<Planet> getPlanets() {
		return this.star.getPlanets();
	}

	/**
	 * Get the {@code Planet} with the specified name
	 * @param name
	 * @return the planet
	 */
	public Planet getPlanetByName(String name) {
		return this.star.getPlanetByName(name);
	}

	/**
	 * Get the {@code Moon} whit the specified name
	 * @param name
	 * @return the moon
	 */
	public Moon getMoonByName(String name) {
		ArrayList<Moon> moons = this.getMoons();

		for(Moon moon : moons) {
			if(name.equals(moon.getName())) {
				return moon;
			}
		}

		return null;
	}

	/**
	 * Get all the {@code Moon} od the {@code StarSystem}
	 * @return the moons
	 */
	public ArrayList<Moon> getMoons() {
		ArrayList<Moon> moons = new ArrayList<Moon>();

		for(Planet planet : this.star.getPlanets()) {
			moons.addAll(planet.getMoons());
		}

		return moons;
	}

	/**
	 * Get all the {@code Moon} which orbits around a specified {@code Planet}
	 * @param planetName the planet name
	 * @return the moons
	 */
	public ArrayList<Moon> getMoons(String planetName) {
		Planet planet = this.star.getPlanetByName(planetName);

		return planet == null 
			? null
			: planet.getMoons();
	}

	/**
	 * Remove the {@code Planet} specified by the id from the {@code StarSystem}
	 * @author Gabriele
	 * @param id
	 */
	public void removePlanetById(String id) {
		this.star.removePlanetById(id);
	}

	/**
	 * Remove the {@code Planet} specified by the name from the {@code StarSystem}
	 * @author Gabriele
	 * @param name
	 */
	public void removePlanetByName(String name) {
		this.star.removePlanetByName(name);
	}

	/**
	 * Remove the {@code Moon} specified by the id from the {@code StarSystem}
	 * @author Gabriele
	 * @param id
	 */
	public void removeMoonById(String id) {
		for(Planet planet : this.star.getPlanets()) {
			planet.removeMoonById(id);
		}
	}

	/**
	 * Remove the {@code Moon} specified by the name from the {@code StarSystem}
	 * @author Gabriele
	 * @param name
	 */
	public void removeMoonByName(String name) {
		for(Planet planet : this.star.getPlanets()) {
			planet.removeMoonByName(name);
		}
	}

	/**
	 * @author Gabriele
	 * @return the centerOfMass
	 */
	public Position getCenterOfMass() {
		return centerOfMass;
	}

	/**
	 * Check if the specified position is already occupied by a {@code CelestialBody}
	 * @author Gabriele
	 * @param position
	 * @return {@code tue} if the position is already occupied, {@code false} otherwise
	 */
	private boolean isValidPosition(Position position) {
		ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();

		celestialBodies.add(this.star);
		celestialBodies.addAll(this.getPlanets());
		celestialBodies.addAll(this.getMoons());

		for(CelestialBody celestialBody : celestialBodies) {
			if(position.equals(celestialBody.getPosition())) {
				return false;
			}
		}

		return true;
	}
}
