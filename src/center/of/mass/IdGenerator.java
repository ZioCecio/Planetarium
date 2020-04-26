package center.of.mass;

import java.time.LocalDateTime;

public class IdGenerator {
	/**<b>to generate ID</b> I use the most significant information
* <p>
* <dl>-celestial body type</dl>
*<dl>-position (univocal)</dl>
*<dl>-date and time of discovery of the star.</dl></p>
Being the latter unambiguous, it goes beyond the limit case in which two stars respectively a removed and a created are in the same position. 
This allows you to keep past discoveries in the archive avoiding indistinguishable duplications
	 * @author Alessandra, edited by Simone
	*/
	public static String create(CelestialBody c) {	
		String id = null;
		if (c instanceof Planet) {
			id = "P";
		} else if (c instanceof Moon) {
			id = "M";
		} else if (c instanceof Star) {
			id = "S";
		}
		String position = c.getPosition().toString();
		LocalDateTime time = LocalDateTime.now();
		return id + position + "\t" + time;
	}
}
