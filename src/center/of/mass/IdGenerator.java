package center.of.mass;

import java.time.LocalDateTime;

public class IdGenerator {
	/**
	 * <b>to generate ID</b> I use the most significant information
	 * <p>
	 * <dl>
	 * -celestial body type
	 * </dl>
	 * <dl>
	 * -position (univocal)
	 * </dl>
	 * <dl>
	 * -date and time of discovery of the star
	 * </dl>
	 * <dl>
	 * </dl>
	 * </p>
	 * Being the latter unambiguous, it goes beyond the limit case in which two
	 * stars respectively a removed and a created are in the same position. This
	 * allows you to keep past discoveries in the archive avoiding indistinguishable
	 * duplications
	 * 
	 * @see {@linkplain Position}, {@linkplain LocalDateTime}
	 * @param
	 * @author Alessandra, edited by Simone
	 */
	public static String create(CelestialBody celestial_body) {
		return celestial_body.whicCelestialBodyIs() + celestial_body.getPosition().toString() + LocalDateTime.now();
	}
}
