package center.of.mass;

import java.time.LocalDateTime;

public class IdGenerator {
	/*per generare id utilizzo le informazioni
	 * più significative 
	 * -tipologia di corpo celeste
	 * -posizione (univoca)
	 * -data e orario di scoperta della stella
	 * l'ultima essendo univoca esula il caso limite in cui 
	 * due stelle rispettativamente una rimossa e una creata 
	 * si trovino nella stessa posizione
	 * questo permette di tenere in archivio scoperte passate 
	 * evitando doppioni indinstinguibili
	 * 
	 * 
	*/
	public static String create(CelestialBody c) {
		Star s = new Star();
		Planet p = new Planet();
		Moon m = new Moon();
		String id = null;
		if (c.getClass().isInstance(p)) {
			id = "P";
		} else if (c.getClass().isInstance(m)) {
			id = "M";
		} else if (c.getClass().isInstance(s)) {
			id = "S";
		}

		String position = c.getPosition().toString();
		LocalDateTime time = LocalDateTime.now();
		return id + position + "\t" + time;

	}
}
