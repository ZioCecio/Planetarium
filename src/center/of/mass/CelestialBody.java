package center.of.mass;

public class CelestialBody {
	private String id;
	private String name;
	private double mass;
	private Position position;
	
	/**
	 * Constructor of {@code CelestialBody}
	 * @author Gabriele
	 * @param name
	 * @param mass
	 * @param position
	 */
	public CelestialBody(String name, double mass, Position position) {	
		this.name = name;
		this.mass = mass;
		this.position = position;
		this.id = IdGenerator.create(this);
	}

	/**
	 * @author Gabriele
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @author Gabriele
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @author Gabriele
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @author Gabriele
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @author Gabriele
	 * @param the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * @author Gabriele
	 * @param the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Check if two {@code CelestialBody} are the same
	 * @author Gabriele
	 * @param celestialBody
	 * @return {@code true} if {@code this} CelestialBody is equals as that passed as parameter
	 */
	public boolean equals(CelestialBody celestialBody) {
		return this.name == celestialBody.name && this.mass == celestialBody.mass;
	}
}
