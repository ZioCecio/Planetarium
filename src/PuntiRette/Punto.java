package PuntiRette;

public class Punto {

	private static final int DIMENSIONI = 2;

	private String nome;
	private float X;
	private float Y;

	public Punto(String nome, float x, float y) {

		this.nome = nome;
		X = x;
		Y = y;
	}

	public Punto() {
		this.nome = "O";
		X = 0;
		Y = 0;
	}

	// Metodi per l'attributo nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// Metodi per l'attributo X
	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	// Metodi per l'attributo Y
	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}

	// to string
	public String belToString() {

		return String.format("Il punto %s alle coordinate X:%.4f; Y:%.4f ", getNome(), getX(), getY());

	}

	public String toString() {

		String pv = "; ";
		String fine = getNome() + pv + "(" + getX() + pv + getY() + ")";

		return fine;
	}

	public float distanza(Punto p) {

		return (float) Math.sqrt(Math.pow(X - p.getX(), 2) + Math.pow(Y - p.getY(), 2));
	}

	public String distanzaString(Punto p) {

		if (distanza(p) == 0)
			return "La distanza è 0, in quanto, il punto " + getNome() + " ed il punto " + p.getNome()
					+ " sono la stesso punto";

		return String.format("La distanza tra %s e %s è di %.4f", getNome(), p.getNome(), distanza(p));

	}

	// public boolean puntoUguale(Punto p) {
	// if( getX()==p.getX() && p.getY()==getY())
	// return true;
	// return false;
	// }
}
