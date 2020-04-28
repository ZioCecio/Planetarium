package center.of.mass;

import it.unibs.fp.mylib.MyMath;

public class Moon extends CelestialBody {
	private double radius;
	private Planet planet;

	/**
	 * Constructor of the {@code Moon}
	 * @author Gabriele
	 * @param name
	 * @param mass
	 * @param position
	 */
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
	 * @author Gabriele
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @author Gabriele
	 * @return the {@linkplain Planet} which {@code this} {@linkplain Moon} orbits
	 */
	public Planet getPlanet() {
		return planet;
	}

	/**
	 * @author Gabriele
	 * @param the {@linkplain Planet} which {@code this} {@linkplain Moon} orbits
	 */
	public void setPlanet(Planet planet) {
		this.planet = planet;

		this.radius = this.calcRadius();
	}

	@Override
	public String toString() {
		String string = String.format("Moon %s with mass %.2f in position %s", this.getName(), this.getMass(), this.getPosition());

		if(this.planet != null) {
			string = string.concat(String.format(" which orbits around %s", this.planet.getName()));
		}

		return string;
	}
}
