package center.of.mass;

/**Mother class representing any celestial body
 * @author Alessandra, edited by Simone
 * */
public class CelestialBody {
	private String id;
	private double mass;
	private Position position;
	

	/**<h1>Constructor with attributes</h1>
	 * @author Simone*/
	public CelestialBody(double mass, Position position) {
		setMass(mass);
		setPosition(position);
		setId();
	}
	/**<h1>empty constructor</h1>
	 * @author Simone*/
	public CelestialBody() {
		super();
	}
	/**
	 * @author Alessandra*/
	public double getMass() {
		return mass;
	}
	
	/**
	 * @author Alessandra*/
	public void setMass(double mass) {
		this.mass = mass;
	}
	/**
	 * @author Alessandra*/
	public Position getPosition() {
		return position;
	}
	/**
	 * @author Alessandra*/
	public void setPosition(Position position) {
		this.position = position;
	}
	/**
	 * @author Alessandra*/
	public String getId() {
		return id;
	}
	/**
	 * @author Alessandra*/
	private void setId() {
		this.id = IdGenerator.create(this);
	}
	
}
