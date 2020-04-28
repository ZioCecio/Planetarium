package center.of.mass;

import java.util.ArrayList;

public class Star extends CelestialBody {
	private static final int MAX_PLANETS = 26000;

	private  ArrayList<Planet> planets;
	
	public Star(String name, double mass, Position position) {
		super(name, mass, position);
		this.planets = new ArrayList<Planet>();
	}

	/**
	* Add a new {@code Planet} that orbits around {@code this} star
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
	 * Get the {@code Moon} specified by the name
	 * @param position
	 * @return the {@code Moon} specified
	 */
	public Planet getMoonByPosition(Position position) {
		for(Planet planet : this.planets) {
			if(planet.getPosition().equals(position)) {
				return planet;
			}
		}

		return null;
	}

	/**
	 * @return the planets
	 */
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
}
