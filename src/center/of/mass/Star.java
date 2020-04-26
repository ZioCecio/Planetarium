package center.of.mass;

import java.util.ArrayList;

import center.of.mass.Planet;
import it.unibs.fp.mylib.MyMath;

public class Star extends CelestialBody {
	private static final String NO_SPACE = "No space left";
	private static final String FOUND = "Planet has been found";
	private static final int LIM_PLANETS = 26000;
	private  ArrayList<Planet> planets;


	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}
	/*
	 * action on the planets are managed by the star
	 * w/o using getters and setters
	 * 
	 * 
	 */
	public void actionPlanet(int label) {
		Planet p1 = new Planet();
		switch (label) {
		case 1:
			p1 = PlanetariumUtils.readPlanet();
			checkPlace(p1);
			break;
		case 2:
			p1 = (Planet) lookForPlanet(PlanetariumUtils.readPosition());
			try{
				p1.getMoons().removeAll(p1.getMoons());
				planets.remove(p1);
			}
			catch(Exception NullPointerException){
				planets.remove(p1);
			}
			break;
		default:
			System.out.println("Error");
		}
	}
/*
 * per la ricerca di un corpo celeste utilizzo 
 * la posizione che assumo essere univoca
 * in Planetarium.Utils si fa un controllo anche
 * di questo tipo
 * tutto questo gestito dallo starSystem
 * che controlla anche la posizione del sole 
 * che assumo essere (0,0)
 * 
 */

	public CelestialBody lookForPlanet(Position pos) {
		boolean planetContainX = false;
		boolean planetContainY = false;
		for (int i = 0; i < planets.size(); i++) {
			Planet p1=new Planet();
			p1=planets.get(i);
			planetContainX = MyMath.compareDouble(p1.getPosition().getX(), pos.getX());
			planetContainY = MyMath.compareDouble(p1.getPosition().getY(), pos.getY());
			if (planetContainX && planetContainY) {
				System.out.println(FOUND);
				return p1;
			}
			else {
				Moon m=new Moon();
				try{
					m=p1.lookForMoon(pos);
					return m;
				}
				catch(Exception NullPointerException) {
					m=null;
				}
			}
		}
		return null;
		
	}
	public void checkPlace(Planet p) {
		if(planets.size()<LIM_PLANETS) {
			planets.add(p);
		}
		else {
			System.out.println(NO_SPACE);
		}
	}
}
