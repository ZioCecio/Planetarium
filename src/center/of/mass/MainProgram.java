package center.of.mass;

import java.util.ArrayList;

public class MainProgram {

	public static void main(String[] args) {
		/*
		Star sole = new Star("Sole", 30, new Position(0, 0));

		Planet pianeta1 = new Planet("Terra", 5, new Position(0, -3));
		Planet pianeta2 = new Planet("Giove", 7, new Position(3, 3));

		Moon luna1 = new Moon("Luna1", 1, new Position(-1, -4));
		Moon luna2 = new Moon("Luna2", 2, new Position(2, 3));
		Moon luna3 = new Moon("Luna3", 1, new Position(4, 4));

		StarSystem starSystem = new StarSystem(sole);

		starSystem.addPlanet(pianeta1);
		starSystem.addPlanet(pianeta2);

		System.out.println(pianeta2.getStar().getName());

		Planet p = PlanetariumUtils.readPlanet();
		System.out.println(p);
		*/

		Manager manager = new Manager("Welcome in the Planetarium app!");

		manager.startProgram();
	}

}
