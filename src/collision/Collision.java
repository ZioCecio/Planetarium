package collision;

import center.of.mass.CelestialBody;
import center.of.mass.Moon;
import center.of.mass.Planet;
import center.of.mass.PlanetariumUtils;
import center.of.mass.Star;
import center.of.mass.StarSystem;
import it.unibs.fp.mylib.MyMath;

public class Collision {
	private static final String MOON = "M";
	private static final String PLANET = "P";
	private static final String STAR = "S";
	public static final Double MINIMUN_DISTANCE = PlanetariumUtils.MINIMUM_MASS;
	private static final String IT_COULD_BE_A_COLLISION = "It could be a Collision";
	private static final String NO_COLLISION = "No Collision";
	private static final String CANNOT_COLLIDE = "A %s cannot collide with a %s ";
	/** {@value} */
	private static final String NOT_CELESTIALBODY_DAUGHTER = "You must use a daughter class of "
			+ CelestialBody.class.getSimpleName() + " example:\n -Star;\n -Planet;\n -Moon.";

	/**
	 * This is the main method
	 * 
	 * @param c1, the first {@linkplain CelestialBody}, <b>it must be his own
	 *            daughter class</b>
	 * @param c2, the second {@linkplain CelestialBody}, <b>it must be his own
	 *            daughter class</b>
	 *            
	 * @return a {@linkplain String}, which says whether
	 *         {@linkplain #IT_COULD_BE_A_COLLISION} or {@linkplain #NO_COLLISION}.
	 *         if a daughter class of {@linkplain CelestialBody} has not passed, the
	 *         method returns {@linkplain #NOT_CELESTIALBODY_DAUGHTER}
	 * @author Simone
	 */
	public static String collisionMenu(CelestialBody c1, CelestialBody c2) {
		switch (c1.whichCelestialBodyIs()) {
		case STAR:
			switch (c2.whichCelestialBodyIs()) {
			case STAR:
				return String.format(CANNOT_COLLIDE, Star.class.getSimpleName(), Star.class.getSimpleName());
			case PLANET:
				return String.format(CANNOT_COLLIDE, Star.class.getSimpleName(), Planet.class.getSimpleName());

			case MOON: {
				if (StarMoonCollision((Star) c1, (Moon) c2))
					return IT_COULD_BE_A_COLLISION;
				return NO_COLLISION;
			}
			default:
				return NOT_CELESTIALBODY_DAUGHTER;
			}
		case PLANET:
			switch (c2.whichCelestialBodyIs()) {
			case STAR:
				return String.format(CANNOT_COLLIDE, Planet.class.getSimpleName(), Star.class.getSimpleName());
			case PLANET:
				if (PlanetPlanetCollision((Planet) c1, (Planet) c2))
					return IT_COULD_BE_A_COLLISION;
				return NO_COLLISION;
			case MOON: {
				if (PlanetMoonCollision((Planet) c1, (Moon) c2))
					return IT_COULD_BE_A_COLLISION;
				return NO_COLLISION;
			}
			default:
				return NOT_CELESTIALBODY_DAUGHTER;
			}
		case MOON:
			switch (c2.whichCelestialBodyIs()) {
			case STAR: {
				if (StarMoonCollision((Star) c2, (Moon) c1))
					return IT_COULD_BE_A_COLLISION;
				return NO_COLLISION;
			}
			case PLANET: {
				if (PlanetMoonCollision((Planet) c2, (Moon) c1))
					return IT_COULD_BE_A_COLLISION;
				return NO_COLLISION;
			}

			case MOON: {
				if (MoonMoonCollision((Moon) c1, (Moon) c2))
					return IT_COULD_BE_A_COLLISION;
				return NO_COLLISION;
			}
			default:
				return NOT_CELESTIALBODY_DAUGHTER;
			}
		default:
			return NOT_CELESTIALBODY_DAUGHTER;
		}
	}

	/**
	 * If the <b>distance</b> between {@code this} {@linkplain Star} and
	 * {@code this} {@linkplain Moon} is minor/same as {@code this}
	 * {@link Moon#getRadius()} means that will Collide. <br>
	 * <code>The code is like: (m.getPlanet().getRadius() <= m.getRadius())</code>
	 * 
	 * @return true if collide, false otherwise
	 * @param s
	 *            is the {@linkplain Star}
	 * @param m
	 *            is the {@linkplain Moon}
	 * @author Simone
	 * 
	 */
	private static boolean StarMoonCollision(Star s, Moon m) {
		return (m.getPlanet().getRadius() <= m.getRadius());
	}

	/**
	 * If the <b>distance</b> from the {@linkplain Star} of {@code this} two
	 * {@linkplain Planet} is the same AND they r not the same {@linkplain Planet},
	 * they will Collide
	 * 
	 * @return true if collide, false otherwise
	 * @param p1,
	 *            is the first {@linkplain Planet}
	 * @param p2,
	 *            is the second {@linkplain Planet}
	 * @author Simone
	 */
	private static boolean PlanetPlanetCollision(Planet p1, Planet p2) {
		if (p1.equals(p2))
			return false;
		return (p1.getRadius() == p2.getRadius());
	}

	/**
	 * If {@code this} {@linkplain Moon}'s <b>{@code radius}</b> is greater than the
	 * <b>distance</b> between its orbiting {@linkplain Planet}
	 * ({@link Moon#getPlanet()}) and the {@linkplain Planet} to be compared, then
	 * it will Collide
	 * 
	 * @return true if collide, false otherwise
	 * @param p
	 *            is the {@linkplain Planet}
	 * @param m
	 *            is the {@linkplain Moon}
	 * @author Simone
	 */
	private static boolean PlanetMoonCollision(Planet p, Moon m) {
		double offset = Math.abs(p.getRadius() - m.getPlanet().getRadius());
		if (offset < MINIMUN_DISTANCE)
			return (PlanetPlanetCollision(p, m.getPlanet()));
		return ((offset <= m.getRadius()));
	}

	/**
	 * Two {@linkplain Moon} can Collide only if:
	 * <p>
	 * <dl>
	 * - orbit around the same {@linkplain Planet} and have the same
	 * <b><code>radius</code></b>
	 * </dl>
	 * <dl>
	 * - the <b>distance </b>from the {@linkplain Star} of the
	 * <code><b>first</b></code> {@linkplain Moon}'s {@linkplain Planet} plus its
	 * {@code radius} is greater than the <b>distance </b> of the
	 * <b>{@code second}</b> {@linkplain Moon}'s {@linkplain Planet} minus its
	 * {@code radius}
	 * </dl>
	 * <br>
	 * Example {@code ((m1.getRadius() + m1.getPlanet().getRadius() }<b>>
	 * </b>{@code m2.getPlanet().getRadius() - m2.getRadius()) }
	 * 
	 * @param m1,
	 *            the first {@linkplain Moon}
	 * @param m2,
	 *            the second {@linkplain Moon}
	 * @author Simone
	 */
	private static boolean MoonMoonCollision(Moon m1, Moon m2) {
		if (m1.equals(m2))
			return false;
		if (m1.getPlanet() == m2.getPlanet())
			if (m1.getRadius() == m2.getRadius())
				return true;
			else
				return false;
		if (m1.getPlanet().getRadius() < m2.getPlanet().getRadius())
			return ((m2.getPlanet().getRadius() - m2.getRadius()) <= (m1.getRadius() + m1.getPlanet().getRadius()));
		return ((m1.getPlanet().getRadius() - m1.getRadius()) <= (m2.getRadius() + m2.getPlanet().getRadius()));

	}
}
