package center.of.mass;

import java.util.ArrayList;

public class Star extends CelestialBody {
	private static final int MAX_PLANETS = 26000;

	private  ArrayList<Planet> planets;
	
	/**
	 * Constructor of the {@code Star}
	 * @author Gabriele
	 * @param name
	 * @param mass
	 * @param position
	 */
	public Star(String name, double mass, Position position) {
		super(name, mass, position);
		this.planets = new ArrayList<Planet>();
	}

	/**
	* Add a new {@code Planet} that orbits around {@code this} star
	@author Gabriele
	* @param planet
	*/
	public void addPlanet(Planet planet) {
		if(this.planets.size() >= MAX_PLANETS) {
			return;
		}

		planet.setStar(this);

		this.planets.add(planet);
	}

	/**
	 * Add new several {@code Planet} that orbit around {@code this} star
	 * @author Gabriele
	 * @param planets
	 */
	public void addPlanets(ArrayList<Planet> planets) {
		if(this.planets.size() >= MAX_PLANETS + planets.size()) {
			return;
		}

		for(Planet planet : this.planets) {
			planet.setStar(this);
		}

		this.planets.addAll(planets);
	}

	/**
	 * Get the {@code Planet} specified by the id
	 * @author Gabriele
	 * @param id
	 * @return the {@code Planet} specified
	 */
	public Planet getPlanetById(String id) {
		for(Planet planet : this.planets) {
			if(planet.getId().equals(id)) {
				return planet;
			}
		}

		return null;
	}

	/**
	 * Get the {@code Planet} specified by the name
	 * If there are several planets with the same name, only the first will be retuned
	 * @author Gabriele
	 * @param name
	 * @return the {@code Planet} specified
	 */
	public Planet getPlanetByName(String name) {
		for(Planet planet : this.planets) {
			if(planet.getName().equals(name)) {
				return planet;
			}
		}

		return null;
	}

	/**
	 * Get the {@code Planet} specified by the position
	 * @author Gabriele
	 * @param position
	 * @return the {@code Planet} specified
	 */
	public Planet getPlanetByPosition(Position position) {
		for(Planet planet : this.planets) {
			if(planet.getPosition().equals(position)) {
				return planet;
			}
		}

		return null;
	}

	/**
	 * Remove the {@code Planet} specified by the id
	 * @author Gabriele
	 * @param id
	 */
	public void removePlanetById(String id) {
		Planet planet = this.getPlanetById(id);

		this.planets.remove(planet);
	}

	/**
	 * Remove the {@code Planet} specified by the name
	 * If there are several planets with the same name, only the first will be removed
	 * @author Gabriele
	 * @param name
	 */
	public void removePlanetByName(String name) {
		Planet planet = this.getPlanetByName(name);

		this.planets.remove(planet);
	}

	/**
	 * Remove the {@code Planet} specified by the position
	 * @author Gabriele
	 * @param position
	 */
	public void removePlanetByPosition(Position position) {
		Planet planet = this.getPlanetByPosition(position);

		this.planets.remove(planet);
	}

	/**
	 * @author Gabriele
	 * @return the planets
	 */
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
}
