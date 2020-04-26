package center.of.mass;

import java.util.ArrayList;

import it.unibs.fp.mylib.MyMath;
import it.unibs.fp.mylib.MyMenu;

public class StarSystem {
	private static final String ID_IS = "The id is:";
	private static final String CENTRE_OF_MASS = "The centre of mass is:";
	private static final String TITLE = "Welcome to the StarSystem";
	private static final String[] ITEMS = { "Add a new celestial body", "Remove a celestial body",
			"Look for a celestial body ", "Display a planet's moons", "Display the path to reach a moon","Calculate center of mass" };
	private static final String[] ITEMS_2 = { "to enter a planet", "to enter a moon" };
	private static final String NOTHING_FOUND = "Nothing has been found";
/*
 * menu centrale visualizzato 
 * si compone di un menu principale e uno secondario
 * la variabile label rimanda alle varie azioni 
 * decise nel menu principale 
 */
	public void displayMenu(Star s) {
		MyMenu mainMenu = new MyMenu(TITLE, ITEMS);
		int label = 0;
		boolean exit = false;
		do {
			MyTime.wait(1);
			int decision = mainMenu.scegli();
			switch (decision) {
			case 0:
				exit = true;
				break;
			case 1:
				label = 1;
				subMenu(s, label);
				break;
			case 2:
				label = 2;
				subMenu(s, label);
				break;
			case 3:
				this.lookFor(s);
				break;
			case 4:
				Planet p1=new Planet();
				p1.displayMoons();
				break;
			case 5:
				
				break;
			case 6:
				System.out.println(CENTRE_OF_MASS+calcCentreMass(s));
				break;

			}
		} while (!exit);
	}

	public void subMenu(Star s, int label) {
		MyMenu subMenu = new MyMenu(TITLE, ITEMS_2);
		boolean exit = false;
		do {
			int decision = subMenu.scegli();
			switch (decision) {
			case 0:
				exit = true;
				break;
			case 1:
				s.actionPlanet(label);
				exit = true;
				break;
			case 2:
				Planet p1=PlanetariumUtils.readPlanetLinkedTo(s);
				p1.actionMoon(label);
				exit = true;
				break;
			}
		} while (!exit);

	}


	/*
	 * per calcolare il centro di massa 
	 * allego la formula nel caso bidimensionale al seguente link
	 * https://www.khanacademy.org/science/physics/linear-momentum/center-of-mass/a/what-is-center-of-mass
	 * 
	 * 
	 * 
	 *  
	 */
	public String calcCentreMass(Star s) {
		Position p1 = new Position();
		double partialX = 0;
		double partialY = 0;
		double totMass=0;
		ArrayList<Planet> p=new ArrayList<Planet>();
		p.addAll(s.getPlanets());
		for (int i = 0; i < p.size(); i++) {
			double mass = 0;
			mass = p.get(i).getMass();
			partialX += (p.get(i).getPosition().getX()) * mass;
			partialX+=p.get(i).partialXMoon();
			partialY+=(p.get(i).getPosition().getY()) * mass;
			partialY+=p.get(i).partialYMoon();
		}
		totMass=totalMass(s,p);
		p1.setX((partialX/totMass));
		p1.setY((partialY/totMass));
		return p1.toString();
	}
	/*
	 * 
	 * suddivido la gestione del numeratore
	 * e del denominatore (metodo sottostante)
	 * per brevita di scrittura e
	 * il calcolo della massa totale potrebbe 
	 * potenzialmente avere un riutilizzo
	 * 
	 * 
	 * 
	 * 
	 */
	public double totalMass(Star s,ArrayList<Planet> p) {
		double totalMass=0;
		totalMass+=s.getMass();
		for (int i = 0; i < p.size(); i++) {
			totalMass+=p.get(i).getMass();
			totalMass+=p.get(i).totalMassMoon();
		}
		return totalMass;
	}
	//fix
	public void lookFor(Star s) {
		Position pos = new Position();
		pos = PlanetariumUtils.readPosition();
		try {
			String id=s.lookForPlanet(pos).getId();
			System.out.println(ID_IS + id);
		} catch (Exception NullPointerException) {
			System.out.println(NOTHING_FOUND);
			
		}
	}
}
