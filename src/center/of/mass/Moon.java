package center.of.mass;

import it.unibs.fp.mylib.MyMath;

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
	 * @return the radius of the orbit
	 * @author Gabriele, edited by Simone 
	 */
	private double calcRadius() {
		return MyMath.distance(planet.getPosition().getX(), this.getPosition().getX(), planet.getPosition().getY(),
				this.getPosition().getY());
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the {@linkplain Planet} which {@code this} {@linkplain Moon} orbits
	 */
	public Planet getPlanet() {
		return planet;
	}

	/**
	 *@param the {@linkplain Planet} which {@code this} {@linkplain Moon} orbits
	 */
	public void setPlanet(Planet planet) {
		this.planet = planet;

		this.radius = this.calcRadius();
	}
}
