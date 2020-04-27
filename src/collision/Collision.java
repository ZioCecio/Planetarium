package collision;

import center.of.mass.CelestialBody;
import center.of.mass.Moon;
import center.of.mass.Planet;
import center.of.mass.Star;

public class Collision {
//	public static void Collision(CelestialBody c1, CelestialBody c2) {
//		if(c1 instanceof Star)
//			if(c2 instanceof Moon)
//			{
//				Con
//			}
//		
//		
//	}
//	
//	private static void StarPlanetCollision(Star s, Planet p) {
//		Collision(s, p);
//	}
	
}
/*Praticamente, avendo il raggio_di_rivoluzione, ordino i pianeti tramite quello(si cerca un algoritmo che fa questo lavoro)
 * dopodiche controllo tutte le lune di un primo pianeta(P1) e le ordino e faccio lo stesso con quelle di un secondo pianeta(p2).
 *distMoonP1= prendo raggio_di_rivoluzione di P1 e lo sommo con la distanza della luna pi� vicina di P1. 
 *se e' maggiore o uguale del raggio_di_rivoluzione di P2 allora tutte le Lune di P1 collidono sia con P2 sia con tutte le sue Lune
 *altrimenti
 *distMoonP2= prendo raggio_di_rivoluzione di P2 e lo sottraggo con la distanza della luna pi� vicina di P2
 *se � maggiore o uguale a distMoonP1 allora collide con tutte le altre lune sia di P1 che di P2
 *si continua a ripetere il processo finquando non si verifica una collisione 
 *0 O-o-o O-o-o
 * */

