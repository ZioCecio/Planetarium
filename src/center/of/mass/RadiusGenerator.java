package center.of.mass;

import it.unibs.fp.mylib.MyMath;

/**
 * Static {@linkplain Class} to generate the <b>radius</b> 
 */
public class RadiusGenerator {
	/**Calculate the Radius from the
 * {@linkplain StarSystem#CENTER}*/
	public static double createRadiusFromCenter(CelestialBody celestial_body) {
		return MyMath.distance(StarSystem.CENTER.getX(), StarSystem.CENTER.getY(), celestial_body.getPosition().getX(),
				celestial_body.getPosition().getY());
	}
	
}
