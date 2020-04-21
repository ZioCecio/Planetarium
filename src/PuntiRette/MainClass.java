package PuntiRette;

public class MainClass {

	public static final String PUNTO_CREATO = "Punto creato correttamente";
	public static final String PUNTI_UGUALI = "ATTENZIONE il primo punto inserito e questo sono uguali\nRinserire->";
	public static final String PUNTI_CREATI = "Sono stati creati 2 punti";

	public static void main(String[] args) {

		Punto a = new Punto(), b = new Punto();

		System.out.println("Salve, inserisca i dati per un nuovo punto: ");
		UtilPuntiRette.acquisisciPunto(a);
		System.out.println(PUNTO_CREATO);
		UtilPuntiRette.distanziamento(2);
		System.out.println("Inserisca ora un nuovo punto");
		do {
			UtilPuntiRette.acquisisciPunto(b);
			if (a.distanza(b) == 0)
				System.err.println(PUNTI_UGUALI);

		} while (a.distanza(b) == 0);
		System.out.println(PUNTO_CREATO);
		UtilPuntiRette.distanziamento(2);
		System.out.println(String.format("Sono stati creati 2 punti" + '\n' + a.belToString() + '\n' + b.belToString()
				+ '\n' + "di distanza %.2f ", a.distanza(b)));
		
		
		Retta r1 = UtilPuntiRette.acquisisciRetta(a, b);
		System.out.println(r1.toString());
		
		UtilPuntiRette.distanziamento(2);
		
		Punto c = new Punto();
		System.out.println("Inserisci un terzo punto");
		UtilPuntiRette.acquisisciPunto(c);
		System.out.println("E' stato creato ");
		System.out.println(c.belToString());
		UtilPuntiRette.distanziamento(2);
		System.out.println(r1.belAppartiene(c));

	}

}
