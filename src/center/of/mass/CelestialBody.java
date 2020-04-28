package center.of.mass;


public class CelestialBody {
	private String id;
	private String name;
	private double mass;
	private Position position;
	
	public CelestialBody(String name, double mass, Position position) {
		
		this.name = name;
		this.mass = mass;
		this.position = position;
		this.id = IdGenerator.create(this);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * @param the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Check if two {@code CelestialBody} are the same
	 * @param celestialBody
	 * @return {@code true} if {@code this} CelestialBody is equals as that passed as parameter
	 */
	public boolean equals(CelestialBody celestialBody) {
		return this.name == celestialBody.name && this.mass == celestialBody.mass;
	}
}
