package center.of.mass;

import java.util.ArrayList;

import it.unibs.fp.mylib.MyMath;

public class Moon extends CelestialBody {
	private double radius;
	private Planet planet;

	/**
	 * <h1>Constructor with minimal attributes</h1> the {@linkplain #planet}
	 * attribute will set when {@code this} {@linkplain Moon}, will added to the
	 * {@linkplain Planet} {@linkplain Moon} {@linkplain ArrayList}
	 * 
	 * @param name
	 *            is the {@code name }of {@code this }new {@linkplain Moon}
	 * @param mass
	 *            is the {@code mass }of {@code this }new {@linkplain Moon}
	 * @param position
	 *            is the {@linkplain Position} of {@code this }new {@linkplain Moon}
	 * @see {@linkplain Planet#addMoon}
	 * @author Simone, edited by Gabriele
	 */
	public Moon(String name, double mass, Position position) {
		super(name, mass, position);
		this.planet = null;
		this.radius = 0;
	}

	/**
	 * <h1>Constructor where is specified the {@linkplain #planet} attribute</h1>
	 * 
	 * @param name
	 *            is the {@code name } of {@code this }new {@linkplain Moon}
	 * @param mass
	 *            is the {@code mass }of {@code this }new {@linkplain Moon}
	 * @param position
	 *            is the {@linkplain Position} of {@code this }new {@linkplain Moon}
	 * @param planet
	 *            is the {@linkplain Planet} where {@code this } new
	 *            {@linkplain Moon} will orbit
	 * @author Simone
	 */
	public Moon(String name, double mass, Position position, Planet planet) {
		super(name, mass, position);
		this.planet = planet;
		this.radius = calcRadius();
	}

	/**
	 * Calculate the radius of the orbit
	 * 
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
	 * @param the
	 *            {@linkplain Planet} which {@code this} {@linkplain Moon} orbits
	 */
	public void setPlanet(Planet planet) {
		this.planet = planet;

		this.radius = this.calcRadius();
	}
}
