package center.of.mass;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMath;

public class PlanetariumUtils {
	private static final int POSITION_STAR = 0;
	private static final String MOON_ALREADY_EXIST = "A moon in this position has already been created,please look for another planet";
	private static final String MOON_LINKED_PLANET = "A moon must be linked to his own planet please enter planet positiono>";
	private static final String MESSAGE_MASS = "Enter a valid mass>";
	private static final String MESSAGE_X_AXIS = "Enter the position on the x-axis>";
	private static final String MESSAGE_Y_AXIS = "Enter the position on the y-axis>";
	private static final double MINIMUM = 0.0000000001;

	public static Planet readPlanet() {
		Planet p = new Planet();
		readNewMass(p);
		p.setPosition(readPosition());
		p.setId();
		//p.setRadius();
		return p;
	}

	public static Moon readMoon() {
		Moon m = new Moon();
		readNewMass(m);
		m.setPosition(readPosition());
		m.setId();
		// p.setRadius();
		return m;
	}

	public static Planet readPlanetLinkedTo(Star s) {
		CelestialBody c = new CelestialBody();
		Planet p = new Planet();
		Moon m = new Moon();
		boolean exit = false;
		do {
			System.out.println(MOON_LINKED_PLANET);
			c = s.lookForPlanet(readPosition());
			if (c.getClass().isInstance(p)) {
				c = (Planet) p;
				exit = true;
				return p;
			} else if (c.getClass().isInstance(m)) {
				System.out.println(MOON_ALREADY_EXIST);
				exit = false;
			}

		} while (!exit);

		return null;
	}

	public static Position readPosition() {
		Position pos = new Position();
		double x = 0;
		double y = 0;
		do {
			x = InputDati.leggiDouble(MESSAGE_X_AXIS);
		} while (MyMath.compareDouble(x,POSITION_STAR));
		pos.setX(x);
		do {
			y = InputDati.leggiDouble(MESSAGE_Y_AXIS);
		} while (MyMath.compareDouble(y,POSITION_STAR));
		pos.setY(y);
		// checkValue(pos);
		// check position already used
		return pos;
	}

	public static void readNewMass(CelestialBody c) {
		double mass = 0;
		do {
			mass = InputDati.leggiDoubleConMinimo(MESSAGE_MASS, MINIMUM);
		} while (mass < MINIMUM);
		c.setMass(mass);
	}

}
