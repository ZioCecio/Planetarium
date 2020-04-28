package center.of.mass;

public class Moon extends CelestialBody {
	private double radius;
	private Planet planet;

	public Moon(String name, double mass, Position position) {
		super(name, mass, position);
		this.planet = null;

		this.radius = 0;
	}

	/**
	 * Calculate the radius of the orbit
	 * @param planet
	 * @return the radius of the orbit
	 */
	private double calcRadius(Planet planet) {
		return Math.sqrt(Math.pow(this.planet.getPosition().getX() - this.getPosition().getX(), 2) + Math.pow(this.planet.getPosition().getY() - this.getPosition().getY(), 2));
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the planet
	 */
	public Planet getPlanet() {
		return planet;
	}

	/**
	 * @param planet the planet to set
	 */
	public void setPlanet(Planet planet) {
		this.planet = planet;

		this.radius = this.calcRadius(planet);
	}
}
