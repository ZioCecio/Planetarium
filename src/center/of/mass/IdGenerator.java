package center.of.mass;

import java.time.LocalDateTime;
import java.util.UUID;

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
	 * -a pseudorandom number generator.
	 * </dl>
	 * </p>
	 * Being the latter unambiguous, it goes beyond the limit case in which two
	 * stars respectively a removed and a created are in the same position. This
	 * allows you to keep past discoveries in the archive avoiding indistinguishable
	 * duplications
	 * 
	 * @see {@linkplain Position}, {@linkplain LocalDateTime}, {@linkplain UUID}
	 * @param 
	 * @author Alessandra, edited by Simone
	 */
	public static String create(CelestialBody celestial_body) {
		String id = null;
		if (celestial_body instanceof Planet) {
			id = "P";
		} else if (celestial_body instanceof Moon) {
			id = "M";
		} else if (celestial_body instanceof Star) {
			id = "S";
		}
		String position = celestial_body.getPosition().toString();
		return id + position + LocalDateTime.now() + UUID.randomUUID();
	}
}
