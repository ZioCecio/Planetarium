package center.of.mass;

import java.util.ArrayList;

import collision.Collision;
import exceptions.CelestialBodyNotFoundException;
import exceptions.InvalidMassException;
import exceptions.InvalidNameException;
import exceptions.InvalidPositionException;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Manager {
	private StarSystem starSystem;
	private String initialMessage;

	private MyMenu mainMenu;
	private MyMenu planetMenu;
	private MyMenu moonMenu;

	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";

	/**
	 * Construsctor of the {@code Manager} wich manage the program
	 * 
	 * @author Gabriele
	 * @param initialMessage
	 */
	public Manager(String initialMessage) {
		this.initialMessage = initialMessage;

		this.mainMenu = new MyMenu("Planetarium", new String[] { "Manage planets", "Manage moons",
				"See the route between 2 celestial bodies", "Show the center of mass of the current star system", "Check if two celestial bodies can collide" });
		this.planetMenu = new MyMenu("What do you want to do?", new String[] { "Add a new planet", "Remove a planet",
				"View a list of all planets", "View the information about a specific planet" });
		this.moonMenu = new MyMenu("What do you want to do?", new String[] { "Add a new moon", "Remove a moon",
				"View a list of all moons", "View the information about a specific moon" });
	}

	/**
	 * Start the program and the interation with the user
	 * 
	 * @author Gabriele
	 */
	public void startProgram() {
		this.writeOKMessage(this.initialMessage);

		this.starSystem = new StarSystem(PlanetariumUtils.readStar());

		this.useMainMenu();
	}

	/**
	 * Start the main menu
	 * 
	 * @author Gabriele
	 */
	private void useMainMenu() {
		int choose;

		do {
			choose = this.mainMenu.scegli();

			switch (choose) {
			case 1:
				this.usePlanetMenu();
				break;

			case 2:
				this.useMoonMenu();
				break;

			case 3:
				this.visualizeRoute(InputDati.leggiStringaNonVuota("Name of the starting celestial body: "),
						InputDati.leggiStringaNonVuota("Name of the destination celestial body: "));
				break;
			case 4:
				this.visualizeCenter();
				break;

			case 5:
				this.checkCollision(InputDati.leggiStringaNonVuota("Name of the first celestial body: "), InputDati.leggiStringaNonVuota("Name of the second celestial body: "));
				break;
			}

		} while (choose != 0);
	}

	/**
	 * Start the menu used to manage the planets of the star system
	 * 
	 * @author Gabriele
	 */
	private void usePlanetMenu() {
		int choose;

		do {
			choose = this.planetMenu.scegli();

			switch (choose) {
			case 1:
				this.addCelestialBody(PlanetariumUtils.readPlanet());
				break;

			case 2:
				this.removePlanetByName(InputDati.leggiStringaNonVuota("Name of the planet to remove: "));
				break;

			case 3:
				this.showCelestialBodiesNames(new ArrayList<CelestialBody>(this.starSystem.getPlanets()));
				break;

			case 4:
				this.viusalizeInfo(this.starSystem
						.getPlanetByName(InputDati.leggiStringaNonVuota("Name of the planet to visualize: ")));
				break;
			}

		} while (choose != 0);
	}

	/**
	 * Start the menu used to manage the moons of the star system
	 * 
	 * @author Gabriele
	 */
	private void useMoonMenu() {
		int choose;

		do {
			choose = this.moonMenu.scegli();

			switch (choose) {
			case 1:
				this.addCelestialBody(PlanetariumUtils.readMoon());
				break;

			case 2:
				this.removeMoonByName(InputDati.leggiStringaNonVuota("Name of the moon to remove: "));
				break;

			case 3:
				this.showCelestialBodiesNames(new ArrayList<CelestialBody>(this.starSystem.getMoons()));
				break;

			case 4:
				this.viusalizeInfo(this.starSystem
						.getMoonByName(InputDati.leggiStringaNonVuota("Name of the moon to visualize: ")));
				break;
			}

		} while (choose != 0);
	}

	/**
	 * Add a new {@code CelestialBody} in the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @param celestialBody
	 */
	private void addCelestialBody(CelestialBody celestialBody) {
		if (celestialBody instanceof Planet) {
			try {
				this.starSystem.addPlanet((Planet) celestialBody);
				this.writeOKMessage("Planet added succesfully!");
			} catch (InvalidPositionException exception) {
				this.writeErrorMessage(exception.getMessage());
			} catch (InvalidNameException exception) {
				this.writeErrorMessage(exception.getMessage());
			} catch (InvalidMassException exception) {
				this.writeErrorMessage(exception.getMessage());
			}
		}

		if (celestialBody instanceof Moon) {
			try {
				String nameOfPlanet = InputDati
						.leggiStringaNonVuota("Name of the planet around which the moon orbits: ");
				this.starSystem.addMoonToPlanetWithName((Moon) celestialBody, nameOfPlanet);
				this.writeOKMessage("Moon added succesfully!");

			} catch (InvalidPositionException exception) {
				this.writeErrorMessage(exception.getMessage());

			} catch (CelestialBodyNotFoundException exception) {
				this.writeErrorMessage(exception.getMessage());

			} catch (InvalidNameException exception) {
				this.writeErrorMessage(exception.getMessage());
			} catch (InvalidMassException exception) {
				this.writeErrorMessage(exception.getMessage());
			}
		}
	}

	/**
	 * Remove the {@code Planet} specified by the name from the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @param name
	 */
	private void removePlanetByName(String name) {
		Planet planet = this.starSystem.removePlanetByName(name);

		if (planet == null) {
			this.writeWarningMessage("The specified planet doesn't exist");
		} else {
			this.writeOKMessage("Planet removed succesfully!");
		}
	}

	/**
	 * Remove the {@code Moon} specified by the name from the {@code StarSystem}
	 * 
	 * @author Gabriele
	 * @param name
	 */
	private void removeMoonByName(String name) {
		Moon moon = this.starSystem.removeMoonByName(name);

		if (moon == null) {
			this.writeWarningMessage("The specified moon doesn't exist");
		} else {
			this.writeOKMessage("Moon removed succesfully!");
		}
	}

	/**
	 * Visualize the information about the specified {@code CelestialBody}
	 * 
	 * @param celestialBody
	 */
	private void viusalizeInfo(CelestialBody celestialBody) {
		if (celestialBody == null) {
			this.writeWarningMessage("The specified celestial body doesn't exist");
		} else {
			this.writeOKMessage(celestialBody.toString());
			;
		}
	}

	private void visualizeRoute(String start, String destination) {
		CelestialBody startCelestialBody = this.starSystem.getCelestialBodyByName(start);
		CelestialBody destinationCelestialBody = this.starSystem.getCelestialBodyByName(destination);

		if (startCelestialBody == null) {
			this.writeWarningMessage("The specified starting celestial body doesn't exist");
			return;
		}

		if (destinationCelestialBody == null) {
			this.writeWarningMessage("The specified destination celestial body doesn't exist");
			return;
		}

		ArrayList<CelestialBody> route = this.starSystem.getRoute(startCelestialBody, destinationCelestialBody);
		this.writeOKMessage(String.format("The route starting from %s to reach %s is: ", start, destination));
		this.showCelestialBodiesNames(route);
		this.writeOKMessage(String.format("The route is long %.2f", this.starSystem.getRouteLength(route)));
	}

	/**
	 * visualize tha center of mass of the current system
	 * 
	 * @author Alessandra
	 */
	private void visualizeCenter() {
		if (this.starSystem.getCenterOfMass() == null) {
			this.writeWarningMessage("No celestial bodies in this Star System");
		} else {
			this.writeOKMessage("The center of mass is " + this.starSystem.getCenterOfMass().toString());
		}
	}

	private void checkCollision(String celestialBodyName1, String celestialBodyName2) {
		CelestialBody celestialBody1 = this.starSystem.getCelestialBodyByName(celestialBodyName1);
		CelestialBody celestialBody2 = this.starSystem.getCelestialBodyByName(celestialBodyName2);

		if (celestialBody1 == null) {
			this.writeWarningMessage("The specified first celestial body doesn't exist");
			return;
		}

		if (celestialBody2 == null) {
			this.writeWarningMessage("The specified second celestial body doesn't exist");
			return;
		}

		this.writeWarningMessage(Collision.collisionMenu(celestialBody1, celestialBody2));
	}

	/**
	 * Write a RED message on the cmd
	 * 
	 * @author Gabriele
	 * @param message
	 */
	private void writeErrorMessage(String message) {
		System.out.println(RED + message + RESET);
	}

	/**
	 * Write a GREEN message on the cmd
	 * 
	 * @author Gabriele
	 * @param message
	 */
	private void writeOKMessage(String message) {
		System.out.println(GREEN + message + RESET);
	}

	/**
	 * Write a YELLOW message on the cmd
	 * 
	 * @author Gabriele
	 * @param message
	 */
	private void writeWarningMessage(String message) {
		System.out.println(YELLOW + message + RESET);
	}

	/**
	 * Write to the cmd the names of the celestial bodies specified
	 * 
	 * @author Gabriele
	 * @param celestialBodies
	 */
	private void showCelestialBodiesNames(ArrayList<CelestialBody> celestialBodies) {
		for (CelestialBody celestialBody : celestialBodies) {
			this.writeOKMessage("->  " + celestialBody.getName());
		}
	}
}