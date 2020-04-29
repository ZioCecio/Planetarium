package center.of.mass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import exceptions.CelestialBodyNotFoundException;
import exceptions.InvalidPositionException;
import it.unibs.fp.mylib.MyMath;

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
	 * @author Gabriele
	 * @return the planets
	 */
	public ArrayList<Planet> getPlanets() {
		return this.star.getPlanets();
	}

	/**
	 * Get the {@code Planet} with the specified name
	 * @author Gabriele
	 * @param name
	 * @return the planet
	 */
	public Planet getPlanetByName(String name) {
		return this.star.getPlanetByName(name);
	}

	/**
	 * Get the {@code Moon} whit the specified name
	 * @author Gabriele
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
	 * @author Gabriele
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
	 * @author Gabriele
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
	 * Get the {@code CelestialBdoy} specified by the name
	 * @author Gabriel
	 * @param name
	 * @return the celestial body
	 */
	public CelestialBody getCelestialBodyByName(String name) {
		CelestialBody celestialBody = this.getPlanetByName(name);

		if(celestialBody == null) {
			celestialBody = this.getMoonByName(name);
		}

		if(this.star.getName().equals(name)) {
			return this.star;
		}

		return celestialBody;
	}

	/**
	 * Remove the {@code Planet} specified by the id from the {@code StarSystem}
	 * @author Gabriele
	 * @param id
	 * @return the removed planet
	 */
	public Planet removePlanetById(String id) {
		return this.star.removePlanetById(id);
	}

	/**
	 * Remove the {@code Planet} specified by the name from the {@code StarSystem}
	 * @author Gabriele
	 * @param name
	 * @return the removed planet
	 */
	public Planet removePlanetByName(String name) {
		return this.star.removePlanetByName(name);
	}

	/**
	 * Remove the {@code Moon} specified by the id from the {@code StarSystem}
	 * @author Gabriele
	 * @param id
	 * @return the removed moon
	 */
	public Moon removeMoonById(String id) {
		Moon moon = null;

		for(Planet planet : this.star.getPlanets()) {
			moon = planet.removeMoonById(id);

			if(moon != null) {
				return moon;
			}
		}

		return moon;
	}

	/**
	 * Remove the {@code Moon} specified by the name from the {@code StarSystem}
	 * @author Gabriele
	 * @param name
	 * @return the removed moon
	 */
	public Moon removeMoonByName(String name) {
		Moon moon = null;
		
		for(Planet planet : this.star.getPlanets()) {
			moon = planet.removeMoonByName(name);

			if(moon != null) {
				return moon;
			}
		}

		return moon;
	}

	/**
	 * Get the route to reach a specified {@code CelestialBody} starting from another
	 * specified {@code CelestialBody}
	 * @author Gabriele
	 * @param start
	 * @param destination
	 * @return an {@code ArrayList} of celestial bodies which represents the route
	 */
	public ArrayList<CelestialBody> getRoute(CelestialBody start, CelestialBody destination) {
		if(start instanceof Star) {
			return this.getRouteFromTheStar(destination);
		}

		if(start.equals(destination)) {
			return new ArrayList<CelestialBody>(Arrays.asList(start));
		}

		if(start instanceof Moon && destination instanceof Moon) {
			if(((Moon) start).getPlanet().equals(((Moon) destination).getPlanet())) {
				return new ArrayList<CelestialBody>(Arrays.asList(start, ((Moon) start).getPlanet(), destination));
			}
		}

		ArrayList<CelestialBody> startRoute = this.getRouteFromTheStar(start);
		ArrayList<CelestialBody> destinationRoute = this.getRouteFromTheStar(destination);

		destinationRoute.remove(0);
		Collections.reverse(startRoute);
		startRoute.addAll(destinationRoute);

		return startRoute;
	}

	/**
	 * Get the route to reach a specified {@code CelestialBody} starting from the star of {@code this StarSystem}
	 * @author Gabriele
	 * @param celestialBody
	 * @return an {@code ArrayList} of celestial bodies which represents the route
	 */
	public ArrayList<CelestialBody> getRouteFromTheStar(CelestialBody celestialBody) {
		ArrayList<CelestialBody> route = new ArrayList<CelestialBody>();

		route.add(celestialBody);

		if(celestialBody instanceof Star) {
			return route;
		}

		if(celestialBody instanceof Moon) {
			Moon moon = (Moon) celestialBody;
			route.add(moon.getPlanet());
		}

		route.add(this.star);
		Collections.reverse(route);

		return route;
	}

	/**
	 * Get the length of a route
	 * @param route
	 * @return the length of the route
	 */
	public double getRouteLength(ArrayList<CelestialBody> route) {
		double length = 0;

		for(int i = 0; i < route.size() - 1; i++) {
			length += MyMath.distance(route.get(i).getPosition().getX(), route.get(i + 1).getPosition().getX(),
						route.get(i).getPosition().getY(), route.get(i + 1).getPosition().getY());
		}

		return length;
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
