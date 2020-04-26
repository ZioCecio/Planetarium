package center.of.mass;


import java.util.ArrayList;


public class MainProgram {

	public static void main(String[] args) {
		StarSystem ss=new StarSystem();
		Position pos=new Position();
		pos.setX(0);
		pos.setY(0);
		Star s = new Star();
		s.setPosition(pos);
		s.setId();
		s.setMass(30); //maximum mass
		ArrayList<Planet> p = new ArrayList<Planet>();
		s.setPlanets(p);
		ss.displayMenu(s);
		//testing unit-> nevermind
		for (int i = 0; i < p.size(); i++) {
			System.out.println("weight" + p.get(i).getMass());
			System.out.println("position"+p.get(i).getPosition().toString());
			System.out.println("id"+p.get(i).getId());
				
		}

	}

}
