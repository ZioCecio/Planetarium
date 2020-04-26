package center.of.mass;
/**{@linkplain Class} that identifies a {@linkplain Moon}*/
public class Moon extends CelestialBody {
	double radius;
	/**<h1>empty constructor</h1>
	 * @author Simone*/
	public Moon() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**<h1>Constructor with attributes</h1>
	 * @author Simone*/
	public Moon(double mass, Position position) {
		super(mass, position);
		setRadius();
	}

	private void setRadius() {
		this.radius = RadiusGenerator.create(this);
	}

	public double getRadius() {
		return radius;
	}
}
