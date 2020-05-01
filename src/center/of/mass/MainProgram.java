package center.of.mass;

import collision.Collision;

public class MainProgram {

	public static void main(String[] args) {
		Star sole = new Star("sole", 30, new Position(0, 0));

		Planet pianeta1 = new Planet("pianeta1", 5, new Position(0, -3));
		Planet pianeta2 = new Planet("pianeta2", 7, new Position(3, 3));

		Moon luna1 = new Moon("luna1", 1, new Position(-1, -4));
		Moon luna2 = new Moon("luna2", 2, new Position(2, 3));
		Moon luna3 = new Moon("luna3", 1, new Position(4, 4));

		pianeta1.addMoon(luna1);

		pianeta2.addMoon(luna2);
		pianeta2.addMoon(luna3);

		sole.addPlanet(pianeta1);
		sole.addPlanet(pianeta2);

		StarSystem starSystem = new StarSystem(sole);

//		Manager manager = new Manager("Welcome in the Planetarium app!");
//
//		manager.startProgram(starSystem);

		System.out.println(Collision.collisionMenu(pianeta1, luna1));
	}

}
