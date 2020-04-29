package collision;

import center.of.mass.CelestialBody;
import center.of.mass.Moon;
import center.of.mass.Planet;
import center.of.mass.PlanetariumUtils;
import center.of.mass.Star;
import center.of.mass.StarSystem;
import it.unibs.fp.mylib.MyMath;


public class Collision {
	private static final String MOON = "M";
	private static final String PLANET = "P";
	private static final String STAR = "S";
	public static final Double MINIMUN_DISTANCE= PlanetariumUtils.MINIMUM_MASS;
	private static final String IT_COULD_BE_A_COLLISION = "It could be a Collision";
	private static final String NO_COLLISION = "No Collision";
	private static final String CANNOT_COLLIDE = "A %s cannot collide with a %s ";

	public static String Collision(CelestialBody c1,CelestialBody c2) {
		switch (c1.whichCelestialBodyIs()) {
		case STAR:
			switch (c2.whichCelestialBodyIs()) 
			{
			case STAR:
				return String.format(CANNOT_COLLIDE, Star.class.getSimpleName(),Star.class.getSimpleName());
			case PLANET:
				return String.format(CANNOT_COLLIDE, Star.class.getSimpleName(),Planet.class.getSimpleName());
					
			case MOON:{
				if(StarMoonCollision((Star) c1, (Moon) c2))
					return IT_COULD_BE_A_COLLISION;
				return NO_COLLISION;
				}
			}
		case PLANET:
			switch (c2.whichCelestialBodyIs()) {
			case STAR:
				return String.format(CANNOT_COLLIDE, Star.class.getSimpleName(),Star.class.getSimpleName());
			case PLANET:
				return String.format(CANNOT_COLLIDE, Planet.class.getSimpleName(),Planet.class.getSimpleName());
			case MOON:
				
			}
	
		}
		}

	

	private static boolean StarMoonCollision(Star s, Moon m) {
		return (m.getPlanet().getRadius() - m.getRadius() <=MINIMUN_DISTANCE);
	}
//	private static boolean PlanetPlanetCollision(Planet p1, Planet p2) {
//		return (p1.getRadius()-p2.getRadius()<=MINIMUN_DISTANCE);
//	}
	private static boolean PlanetMoon(Planet p, Moon m) {
		if(p.equals(m.getPlanet()))
			return false;//String.format(CANNOT_COLLIDE,Planet.class.getSimpleName(),"with his own"+Moon.class.getSimpleName() );
		if(p.getRadius()==m.getPlanet().getRadius())
			if(MyMath.distance(p.getPosition().getX(), x2, y1, y2))
	}
}
///*
// * Praticamente, avendo il raggio_di_rivoluzione, ordino i pianeti tramite
// * quello(si cerca un algoritmo che fa questo lavoro) dopodiche controllo tutte
// * le lune di un primo pianeta(P1) e le ordino e faccio lo stesso con quelle di
// * un secondo pianeta(p2). distMoonP1= prendo raggio_di_rivoluzione di P1 e lo
// * sommo con la distanza della luna pi� vicina di P1. se e' maggiore o uguale
// * del raggio_di_rivoluzione di P2 allora tutte le Lune di P1 collidono sia con
// * P2 sia con tutte le sue Lune altrimenti distMoonP2= prendo
// * raggio_di_rivoluzione di P2 e lo sottraggo con la distanza della luna pi�
// * vicina di P2 se � maggiore o uguale a distMoonP1 allora collide con tutte
// * le altre lune sia di P1 che di P2 si continua a ripetere il processo
// * finquando non si verifica una collisione 0 O-o-o O-o-o
// */
