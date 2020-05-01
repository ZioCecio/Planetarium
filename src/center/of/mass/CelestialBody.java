 package center.of.mass;

import java.lang.annotation.Target;

import javax.xml.ws.FaultAction;

import it.unibs.fp.mylib.MyMath;

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
		
		return (this.id.compareTo(celestialBody.getId())==0  &&this.name.compareTo(celestialBody.getName())==0&& this.mass == celestialBody.getMass());
	}
	/**@return a {@linkplain String} indicating what type of {@linkplain CelestialBody} it is.<blockquote>
	 * if it don't found any correspondence ->{@code null}</blockquote>
	 * @author Simone*/
	public String whichCelestialBodyIs() {
		String id = null;
		if (this instanceof Planet) {
			id = "P";
		} else if (this instanceof Moon) {
			id = "M";
		} else if (this instanceof Star) {
			id = "S";
		}
		return id;
	}
	/**
	 * to calculate the distance between two celestial body
	 * @author Alessandra
	 * @param celestialbody
	 * @return the distance 
	 */
	public double distanceBetween(CelestialBody celestialbody) {
		double d;
		d=MyMath.distance(this.getPosition().getX(), celestialbody.getPosition().getX(), this.getPosition().getY(),
				celestialbody.getPosition().getY());
		return d;
		
	}
}
