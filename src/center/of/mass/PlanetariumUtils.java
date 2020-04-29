package center.of.mass;


import java.util.ArrayList;

import it.unibs.fp.mylib.*;

/** Util static Class for input data */
public class PlanetariumUtils {
	private static final String MESSAGE_MASS = "Enter a valid mass: ";
	private static final String MESSAGE_X_AXIS = "Enter the position on the x-axis: ";
	private static final String MESSAGE_Y_AXIS = "Enter the position on the y-axis: ";
	private static final double MINIMUM_MASS = 0.0000000001;

	private static final String MESSAGE_REQUEST_NAME = "Enter the name of the celestial body: ";

	/**
	 * Instantiates a {@linkplain Planet} {@linkplain Object} starting from the
	 * <b>mass</b> and <b>position</b> obtained in {@linkplain Console}
	 * 
	 * @see {@linkplain #readPosition()}, {@linkplain #readNewMass()}
	 * @author Alessandra, remake by Simone
	 */
	public static Planet readPlanet() {
		return new Planet(InputDati.leggiStringaNonVuota(MESSAGE_REQUEST_NAME), readNewMass(), readPosition());
	}

	/**
	 * Instantiates a {@linkplain Moon} {@linkplain Object} starting from the
	 * <b>mass</b> and <b>position</b> obtained in {@linkplain Console}
	 * 
	 * @see {@linkplain #readPosition()}, {@linkplain #readNewMass()}
	 * @author Alessandra, remake by Simone
	 */

	public static Moon readMoon() {
		return new Moon(InputDati.leggiStringaNonVuota(MESSAGE_REQUEST_NAME), readNewMass(), readPosition());
	}

	public static Star readStar() {
		return new Star(InputDati.leggiStringaNonVuota(MESSAGE_REQUEST_NAME), readNewMass(), readPosition());
	}

	/**
	 * static method that requires in {@linkplain Console}, the values of the
	 * {@linkplain Position} about a {@linkplain CelestialBody}
	 * 
	 * @author Alessandra, Gabriele
	 */
	public static Position readPosition() {
		return new Position(InputDati.leggiDouble(MESSAGE_X_AXIS), InputDati.leggiDouble(MESSAGE_Y_AXIS));
	}

	/**
	 * static method that requires in {@linkplain Console}, the values of the
	 * <b>mass</b> about a {@linkplain CelestialBody}
	 * 
	 * @author Alessandra, edited by Simone
	 */
	public static double readNewMass() {
		double mass = 0;
		do {
			mass = InputDati.leggiDoubleConMinimo(MESSAGE_MASS, MINIMUM_MASS);
		} while (mass < MINIMUM_MASS);
		return mass;
	}
}
