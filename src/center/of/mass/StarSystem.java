package center.of.mass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import exceptions.CelestialBodyNotFoundException;
import exceptions.InvalidMassException;
import exceptions.InvalidNameException;
import exceptions.InvalidPositionException;
import it.unibs.fp.mylib.MyMath;

public class StarSystem {
	/** The {@linkplain StarSystem} CENTER {@value } */
	public static final Position CENTER = new Position(0, 0);

	private Star star;
	private Position centerOfMass;

	/**
	 * Constructor of {@code StartSystem}
	 * 
	 * @author Gabriele
	 * @param star
	 */
	public StarSystem(Star star) {
		this.star = star;
		this.centerOfMass = calculateCenter();
	}

	/**
	 * calculate the center of mass
	 * please refer to the following bidimensional formula:
	 * See: <a href=" http://hyperphysics.phy-astr.gsu.edu/hbase/cm.html"> 
	 * http://hyperphysics.phy-astr.gsu.edu/hbase/cm.html</a>
	 * @Author Alessandra
	 * @return {@code Position} of the center of mass
	 */
	public Position calculateCenter() {
		double x = 0;
		double y = 0;
		double totalMass = 0;
		double partialX = 0;
		double partialY = 0;
		ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();

		celestialBodies.add(this.star);
		celestialBodies.addAll(this.getPlanets());
		celestialBodies.addAll(this.getMoons());

		for (CelestialBody celestialBody : celestialBodies) {
			partialX += (celestialBody.getPosition().getX()) * (celestialBody.getMass());
			partialY += (celestialBody.getPosition().getY()) * (celestialBody.getMass());
			totalMass += celestialBody.getMass();
		}
		x = partialX / totalMass;
		y = partialY / totalMass;
		return new Position(x, y);

	}

	/**
	 * Add a new {@code Planet} in {@code this} SolarSystem
	 * 
	 * @author Gabriele
	 * @param planet
	 * @throws InvalidPositionException if the position of the planet is already
	 *                                  occupied by another celestial body
	 * @throws InvalidNameException     if the name of the planet is already in use
	 * 
	 */
	public void addPlanet(Planet planet) throws InvalidPositionException, InvalidNameException, InvalidMassException {
		try {
			checkValidity(planet);

		} catch (InvalidPositionException exception) {
			throw exception;
		} catch (InvalidNameException exception) {
			throw exception;
		} 

		if(planet.getMass() > this.star.getMass()) {
			throw new InvalidMassException("The mass is too high");
		}

		this.star.addPlanet(planet);
	}

	/**
	 * Add a new {@linkplain Moon} in {@code this} SolarSystem which orbits around
	 * the {@linkplain Planet} specified by the id
	 * 
	 * @author Gabriele
	 * @param moon
	 * @param idOfPlanet
	 * @throws InvalidPositionException       if the position of the moon is already
	 *                                        occupied by another celestial body
	 * @throws CelestialBodyNotFoundException if the planet with the specified id
	 *                                        doesn't exist
	 * @throws InvalidNameException           if the name of the moon is already in
	 *                                        use
	 * @throws IvalidMassException            if the mass of the planet is higher
	 *                                        than the one of the star
	 */
	public void addMoonToPlanetWithId(Moon moon, String idOfPlanet) throws InvalidPositionException,
			CelestialBodyNotFoundException, InvalidNameException, InvalidMassException {
		try {
			checkValidity(moon);

		} catch (InvalidPositionException exception) {
			throw exception;
		} catch (InvalidNameException exception) {
			throw exception;
		}

		Planet planet = this.star.getPlanetById(idOfPlanet);

		if (planet == null) {
			throw new CelestialBodyNotFoundException("The planet with the spiecified id doesn't exist");
		}

		if(moon.getMass() > planet.getMass()) {
			throw new InvalidMassException("The mass is too high");
		}

		planet.addMoon(moon);
	}

	/**
	 * Add a new {@linkplain Moon} in {@code this} SolarSystem which orbits around
	 * the {@linkplain Planet} specified by the name
	 * 
	 * @author Gabriele
	 * @param moon
	 * @param nameOfPlanet
	 * @throws InvalidPositionException       if the position of the planet is
	 *                                        already occupied by another celestial
	 *                                        body
	 * @throws CelestialBodyNotFoundException if the planet with the specified name
	 *                                        doesn't exist
	 * @throws InvalidNameException           if the name of the planet is already
	 *                                        in use
	 * @throws InvalidMassException           if the mass of the moon is higher than
	 *                                        the one of its planet
	 */
	public void addMoonToPlanetWithName(Moon moon, String nameOfPlanet) throws InvalidPositionException,
			CelestialBodyNotFoundException, InvalidNameException, InvalidMassException {
		try {
			checkValidity(moon);

		} catch (InvalidPositionException exception) {
			throw exception;

		} catch (InvalidNameException exception) {
			throw exception;
		}

		Planet planet = this.star.getPlanetByName(nameOfPlanet);

		if (planet == null) {
			throw new CelestialBodyNotFoundException("The planet with the spiecified name doesn't exist");
		}

		if(moon.getMass() > planet.getMass()) {
			throw new InvalidMassException("The mass is too high");
		}

		planet.addMoon(moon);
	}

	/**
	 * Get all the {@code Planet} of the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @return the planets
	 */
	public ArrayList<Planet> getPlanets() {
		return this.star.getPlanets();
	}

	/**
	 * Get the {@code Planet} with the specified name
	 * 
	 * @author Gabriele
	 * @param name
	 * @return the planet
	 */
	public Planet getPlanetByName(String name) {
		return this.star.getPlanetByName(name);
	}

	/**
	 * Get the {@code Moon} whit the specified name
	 * 
	 * @author Gabriele
	 * @param name
	 * @return the moon
	 */
	public Moon getMoonByName(String name) {
		ArrayList<Moon> moons = this.getMoons();

		for (Moon moon : moons) {
			if (name.equals(moon.getName())) {
				return moon;
			}
		}

		return null;
	}

	/**
	 * Get all the {@code Moon} od the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @return the moons
	 */
	public ArrayList<Moon> getMoons() {
		ArrayList<Moon> moons = new ArrayList<Moon>();

		for (Planet planet : this.star.getPlanets()) {
			moons.addAll(planet.getMoons());
		}

		return moons;
	}

	/**
	 * Get all the {@code Moon} which orbits around a specified {@code Planet}
	 * 
	 * @author Gabriele
	 * @param planetName the planet name
	 * @return the moons
	 */
	public ArrayList<Moon> getMoons(String planetName) {
		Planet planet = this.star.getPlanetByName(planetName);

		return planet == null ? null : planet.getMoons();
	}

	/**
	 * Get the {@code CelestialBody} specified by the name
	 * 
	 * @author Gabriel
	 * @param name
	 * @return the celestial body
	 */
	public CelestialBody getCelestialBodyByName(String name) {
		CelestialBody celestialBody = this.getPlanetByName(name);

		if (celestialBody == null) {
			celestialBody = this.getMoonByName(name);
		}

		if (this.star.getName().equals(name)) {
			return this.star;
		}

		return celestialBody;
	}

	/**
	 * Remove the {@code Planet} specified by the id from the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @param id
	 * @return the removed planet
	 */
	public Planet removePlanetById(String id) {
		return this.star.removePlanetById(id);
	}

	/**
	 * Remove the {@code Planet} specified by the name from the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @param name
	 * @return the removed planet
	 */
	public Planet removePlanetByName(String name) {
		return this.star.removePlanetByName(name);
	}

	/**
	 * Remove the {@code Moon} specified by the id from the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @param id
	 * @return the removed moon
	 */
	public Moon removeMoonById(String id) {
		Moon moon = null;

		for (Planet planet : this.star.getPlanets()) {
			moon = planet.removeMoonById(id);

			if (moon != null) {
				return moon;
			}
		}

		return moon;
	}

	/**
	 * Remove the {@code Moon} specified by the name from the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @param name
	 * @return the removed moon
	 */
	public Moon removeMoonByName(String name) {
		Moon moon = null;

		for (Planet planet : this.star.getPlanets()) {
			moon = planet.removeMoonByName(name);

			if (moon != null) {
				return moon;
			}
		}

		return moon;
	}

	/**
	 * Get the route to reach a specified {@code CelestialBody} starting from
	 * another specified {@code CelestialBody}
	 * 
	 * @author Gabriele
	 * @param start
	 * @param destination
	 * @return an {@code ArrayList} of celestial bodies which represents the route
	 */
	public ArrayList<CelestialBody> getRoute(CelestialBody start, CelestialBody destination) {
		if (start instanceof Star) {
			return this.getRouteFromTheStar(destination);
		}

		if (start.equals(destination)) {
			return new ArrayList<CelestialBody>(Arrays.asList(start));
		}

		if (start instanceof Moon && destination instanceof Moon) {
			if (((Moon) start).getPlanet().equals(((Moon) destination).getPlanet())) {
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
	 * Get the route to reach a specified {@code CelestialBody} starting from the
	 * star of {@code this StarSystem}
	 * 
	 * @author Gabriele
	 * @param celestialBody
	 * @return an {@code ArrayList} of celestial bodies which represents the route
	 */
	public ArrayList<CelestialBody> getRouteFromTheStar(CelestialBody celestialBody) {
		ArrayList<CelestialBody> route = new ArrayList<CelestialBody>();

		route.add(celestialBody);

		if (celestialBody instanceof Star) {
			return route;
		}

		if (celestialBody instanceof Moon) {
			Moon moon = (Moon) celestialBody;
			route.add(moon.getPlanet());
		}

		route.add(this.star);
		Collections.reverse(route);

		return route;
	}

	/**
	 * Get the length of a route
	 * 
	 * @param route
	 * @return the length of the route
	 */
	public double getRouteLength(ArrayList<CelestialBody> route) {
		double length = 0;

		for (int i = 0; i < route.size() - 1; i++) {
			length += MyMath.distance(route.get(i).getPosition().getX(), route.get(i + 1).getPosition().getX(),
					route.get(i).getPosition().getY(), route.get(i + 1).getPosition().getY());
		}

		return length;
	}

	/**
	 * @author Alessandra
	 * @return the centerOfMass
	 */
	public Position getCenterOfMass() {
		this.centerOfMass = this.calculateCenter();
		return this.centerOfMass;
	}

	/**
	 * Check if the specified {@code CelestialBody} can be added at
	 * {@code this StarSystem}
	 * 
	 * @author Gabriele
	 * @throws InvalidPositionException if the position is already occupied
	 * @throws InvalidNameException     if the name is already in use
	 * @throws InvalidMassException     if the value of the mass is too high
	 */
	private void checkValidity(CelestialBody celestialBodyToCheck)
			throws InvalidPositionException, InvalidNameException {
		ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();

		Position position = celestialBodyToCheck.getPosition();
		String name = celestialBodyToCheck.getName();

		celestialBodies.add(this.star);
		celestialBodies.addAll(this.getPlanets());
		celestialBodies.addAll(this.getMoons());

		for (CelestialBody celestialBody : celestialBodies) {
			if (position.equals(celestialBody.getPosition())) {
				throw new InvalidPositionException(
						"The specified position is already occupied by another celestial body");
			}
			if (name.equals(celestialBody.getName())) {
				throw new InvalidNameException("The specified name is already in use");
			}
		}
	}
}
