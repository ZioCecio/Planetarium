package center.of.mass;


import java.util.ArrayList;


public class MainProgram {

	public static void main(String[] args) {
		StarSystem ss=new StarSystem();
		Star s = new Star(30);
		Planet p =new Planet();
//		ArrayList<Planet> p = new ArrayList<Planet>();
//		s.setPlanets(p);
	System.out.println(s.getId());
	System.out.println(p.getId());
	System.out.println(s.getRadius());
		//ss.displayMenu(s);
		//testing unit-> nevermind
//		for (int i = 0; i < p.size(); i++) {
//			System.out.println("weight" + p.get(i).getMass());
//			System.out.println("position"+p.get(i).getPosition().toString());
//			System.out.println("id"+p.get(i).getId());
//				
//		}

	}

}
