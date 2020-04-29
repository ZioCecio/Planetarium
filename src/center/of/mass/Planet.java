package center.of.mass;

import java.util.ArrayList;

import it.unibs.fp.mylib.MyMath;

public class Planet extends CelestialBody {
	private static final int MAX_MOONS = 5000;

	private ArrayList<Moon> moons;
	private double radius;
	private Star star;
	/**
	 * <h1>Constructor with minimal attributes</h1> the {@linkplain #star}
	 * attribute will set when {@code this} {@linkplain Planet}, will added to the
	 * {@linkplain Star} {@linkplain Planet} {@linkplain ArrayList}
	 * 
	 * @param name
	 *            is the {@code name }of {@code this }new {@linkplain Planet}
	 * @param mass
	 *            is the {@code mass }of {@code this }new {@linkplain Planet}
	 * @param position
	 *            is the {@linkplain Position} of {@code this }new {@linkplain Planet}
	 * @see {@linkplain Star#addPlanet(Planet)}
	 * @author Simone, edited by Gabriele
	 */
	public Planet(String name, double mass, Position position) {
		super(name, mass, position);
		this.star = null;
		this.radius =0;
		this.moons = new ArrayList<Moon>();
	}

	/**
	 * <h1>Constructor where is specified the {@linkplain #star} attribute</h1> 
	 * @param name
	 *            is the {@code name }of {@code this }new {@linkplain Planet}
	 * @param mass
	 *            is the {@code mass }of {@code this }new {@linkplain Planet}
	 * @param position
	 *            is the {@linkplain Position} of {@code this }new {@linkplain Planet}
	 
	 * @param star
	 *            is the {@linkplain Star} where {@code this } new
	 *            {@linkplain Planet} will orbit
	 * @author Simone
	 */
	 public Planet(String name, double mass, Position position, Star star) {
		super(name, mass, position);
		this.star = star;
		this.radius = calcRadius();
	}

	public double partialXMoon() {
		double partialXMoon = 0;
		double mass = 0;
		try {
			for (int i = 0; i < moons.size(); i++) {
				mass = moons.get(i).getMass();
				partialXMoon += mass * (moons.get(i).getPosition().getX());
			}
		} catch (Exception NullPointerException) {
			partialXMoon = 0;
		}
		return partialXMoon;
	}

	public double partialYMoon() {
		double partialYMoon = 0;
		double mass = 0;
		try {
			for (int i = 0; i < moons.size(); i++) {
				mass = moons.get(i).getMass();
				partialYMoon += mass * (moons.get(i).getPosition().getY());
			}
		} catch (Exception NullPointerException) {
			partialYMoon = 0;
		}
		return partialYMoon;
	}

	public double totalMassMoon() {

		double totalMass = 0;
		try {
			for (int i = 0; i < moons.size(); i++) {
				totalMass += moons.get(i).getMass();
			}

		} catch (Exception NullPointerException) {
			totalMass = 0;
		}
		return totalMass;
	}

	/**
	 * Add a new {@code Moon} that orbits around {@code this} planet
	 * @author Gabriele
	 * @param moon
	 */
	public void addMoon(Moon moon) {
		if(this.moons.size() >= MAX_MOONS) {
			return;
		}

		moon.setPlanet(this);

		this.moons.add(moon);
	}

	/**
	 * Add new several {@code Moon} that orbit around {@code this} planet
	 * @author Gabriele
	 * @param moons
	 */
	public void addMoons(ArrayList<Moon> moons) {
		if(this.moons.size() >= MAX_MOONS + moons.size()) {
			return;
		}

		for(Moon moon : moons) {
			moon.setPlanet(this);
		}

		this.moons.addAll(moons);
	}

	/**
	 * Get the {@code Moon} specified by the id
	 * @author Gabriele
	 * @param id
	 * @return the {@code Moon} specified
	 */
	public Moon getMoonById(String id) {
		for(Moon moon : this.moons) {
			if(moon.getId().equals(id)) {
				return moon;
			}
		}

		return null;
	}

	/**
	 * Get the {@code Moon} specified by the name
	 * If there are several moons with the same name, only the first will be retuned
	 * @author Gabriele
	 * @param name
	 * @return the {@code Moon} specified
	 */
	public Moon getMoonByName(String name) {
		for(Moon moon : this.moons) {
			if(moon.getName().equals(name)) {
				return moon;
			}
		}

		return null;
	}

	/**
	 * Get the {@code Moon} specified by the position
	 * @author Gabriele
	 * @param position
	 * @return the {@code Moon} specified
	 */
	public Moon getMoonByPosition(Position position) {
		for(Moon moon : this.moons) {
			if(moon.getPosition().equals(position)) {
				return moon;
			}
		}

		return null;
	}

	/**
	 * Remove the {@code Moon} specified by the id
	 * @author Gabriele
	 * @param id
	 * @return the removed moon
	 */
	public Moon removeMoonById(String id) {
		Moon moon = this.getMoonById(id);

		this.moons.remove(moon);

		return moon;
	}

	/**
	 * Remove the {@code Moon} specified by the name
	 * If there are several moons with the same name, only the first will be removed
	 * @author Gabriele
	 * @param name
	 * @return the removed moon
	 */
	public Moon removeMoonByName(String name) {
		Moon moon = this.getMoonByName(name);

		this.moons.remove(moon);

		return moon;
	}

	/**
	 * Remove the {@code Moon} specified by the position
	 * @author Gabriele
	 * @param position
	 * @return the removed moon
	 */
	public Moon removeMoonByPosition(Position position) {
		Moon moon = this.getMoonByPosition(position);

		this.moons.remove(moon);

		return moon;
	}

	/**
	 * Calculate the radius of the orbit
	 * @author Gabriele
	 * @param star
	 * @return the radius of the orbit
	 */
	private double calcRadius() {
		return MyMath.distance(star.getPosition().getX(), this.getPosition().getX(), star.getPosition().getY(), this.getPosition().getY());
	}

	/**
	 * @author Gabriele
	 * @return the moons
	 */
	public ArrayList<Moon> getMoons() {
		return moons;
	}

	/**
	 * @author Gabriele
	 * @return the star
	 */
	public Star getStar() {
		return star;
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
	 * @param star the star to set
	 */
	public void setStar(Star star) {
		this.star = star;

		this.radius = this.calcRadius();
	}

	@Override
	public String toString() {
		String string = String.format("Planet %s with mass %.2f in position %s", this.getName(), this.getMass(), this.getPosition());

		if(this.moons.size() == 0) {
			return string;
		}

		string = string.concat("\nMoons which orbit around:");

		for(Moon moon : this.moons) {
			string = string.concat(String.format("\n->%s", moon.getName()));
		}

		return string;
	}
}
