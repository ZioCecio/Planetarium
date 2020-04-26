package collision;

public class Collision {
	/*DEVO ASPETTARE I VARI METODI DI Planet, Sun...*/
}
/*Praticamente, avendo il raggio_di_rivoluzione, ordino i pianeti tramite quello(si cerca un algoritmo che fa questo lavoro)
 * dopodiche controllo tutte le lune di un primo pianeta(P1) e le ordino e faccio lo stesso con quelle di un secondo pianeta(p2).
 *distMoonP1= prendo raggio_di_rivoluzione di P1 e lo sommo con la distanza della luna più vicina di P1. 
 *se e' maggiore o uguale del raggio_di_rivoluzione di P2 allora tutte le Lune di P1 collidono sia con P2 sia con tutte le sue Lune
 *altrimenti
 *distMoonP2= prendo raggio_di_rivoluzione di P2 e lo sottraggo con la distanza della luna più vicina di P2
 *se è maggiore o uguale a distMoonP1 allora collide con tutte le altre lune sia di P1 che di P2
 *si continua a ripetere il processo finquando non si verifica una collisione 
 *0 O-o-o O-o-o
 * */

