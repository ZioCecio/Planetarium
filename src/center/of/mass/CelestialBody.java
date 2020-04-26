package center.of.mass;

public class CelestialBody extends StarSystem{
	private String id;
	private double mass;
	private Position position;
	private double radius;

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = IdGenerator.create(this);
	}

//	public double getRadius() {
//		return radius;
//	}
//
//	public void setRadius() {
//		this.radius = RadiusGenerator.create(this);
//	}
//


}
