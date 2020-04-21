package PuntiRette;

import it.unibs.fp.mylib.InputDati;

public class UtilPuntiRette {

//	public static String BIG_PUNTIRETTE = "######  ##  ##  ##   ## ######   ####           #######         ######  ####### ######  ######  #######\n" + 
//			" ##  ## ##  ##  ###  ## # ## #    ##             ##   #          ##  ##  ##   # # ## #  # ## #   ##   #\n" + 
//			" ##  ## ##  ##  #### ##   ##      ##             ## #            ##  ##  ## #     ##      ##     ## #\n" + 
//			" #####  ##  ##  ## ####   ##      ##             ####            #####   ####     ##      ##     ####\n" + 
//			" ##     ##  ##  ##  ###   ##      ##             ## #            ## ##   ## #     ##      ##     ## #\n" + 
//			" ##     ##  ##  ##   ##   ##      ##             ##   #          ##  ##  ##   #   ##      ##     ##   #\n" + 
//			"####    ######  ##   ##  ####    ####           #######         ###  ## #######  ####    ####   ####### by Simone Giacomin";

	/*
	 * acquisisce un punto con input da tastiera
	 */
	public static void acquisisciPunto(Punto p) {

		acquisisciNomePunto(p);
		acquisisciX(p);
		acquisisciY(p);
		// return p;
	}

	private static void acquisisciX(Punto p) {

		float x = (float) InputDati.leggiDouble("Inserisci la coordinata X ");
		p.setX(x);
	}

	private static void acquisisciY(Punto p) {

		float y = (float) InputDati.leggiDouble("Inserisci la coordinata Y ");
		p.setY(y);
	}

	private static void acquisisciNomePunto(Punto p) {

		String nome = InputDati.leggiStringaNonVuota("Inserisci il nome del punto ");
		p.setNome(nome);
	}

	public static void cambiaNomeRetta(Retta r) {

		String nome = InputDati.leggiStringaNonVuota("Inserisci il nuovo nome della retta ");
		r.setNome(nome);
	}

	public static Retta acquisisciRetta( Punto p1, Punto p2) {

		Retta r = new Retta(InputDati.leggiStringaNonVuota("Inserisci il nome della retta "), p1, p2);
		return r;
	}

	/*
	 * distanzia output in console
	 */
	public static void distanziamento(int n_di_righe_vuote) {
		for (int i = 0; i < n_di_righe_vuote; i++) {
			System.out.println("");
		}
	}
	
}
