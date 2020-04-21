package PuntiRette;

public class Retta {

	// ATTRIBUTI
	private String nome;
	float coefficienteAngolare;
	float termineNoto;
	boolean verticale;

	private static final double EPSILON = 0.0001;

	// COSTRUTTORI
	public Retta(String nome, boolean verticale, float coefficienteAngolare, float termineNoto) {
		this.coefficienteAngolare = coefficienteAngolare;
		this.termineNoto = termineNoto;
		this.nome = nome;
		this.verticale = verticale;

	}

	/*
	 * costruttore vuoto (crea una retta identica a quella delle X
	 */
	public Retta() {
		this.coefficienteAngolare = 0;
		this.termineNoto = 0;
		this.nome = "X";
		this.verticale = false;

	}

	/**
	 * ATTENZIONE VERIFICARE CHE I PUNTI siano distinti
	 */
	public Retta(String nome_retta, Punto p1, Punto p2) {
		setNome(nome_retta);
		if (isVerticale(p1, p2)) {
			verticale = true;
			termineNoto = p1.getX();
		} else {
			verticale = false;
			setCoefficienteAngolare(p1, p2);
			setTermineNoto(p1);

		}
	}

	/*
	 * restituise true se i due punti sono verticali
	 */
	private boolean isVerticale(Punto p1, Punto p2) {
		if (p1.getX() == p2.getX())
			return true;
		return false;
	}

	// METODI PER ATTRIBUTO verticale
	public boolean getVerticale() {
		return verticale;
	}

	public void setVerticale(boolean verticale) {
		this.verticale = verticale;
	}

	// METODI PER ATTRIBUTO nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// METODI PER ATTRIBUTO coefficientaAngolare
	public float getCoefficienteAngolare() {
		return coefficienteAngolare;
	}

	/*
	 * set che calcola il coefficiente Angolare e lo assegna al suo attributo
	 */
	private void setCoefficienteAngolare(Punto p1, Punto p2) {

		this.coefficienteAngolare = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
	}

	// METODI PER ATTRIBUTO termineNoto
	public float getTermineNoto() {
		return termineNoto;
	}

	/*
	 * set che calcola il termine noto e lo assegna al suo attributo
	 */
	private void setTermineNoto(Punto p) {
		this.termineNoto = p.getY() - this.coefficienteAngolare * p.getX();
	}

	public String toString() {

		if (verticale)
			return String.format("%s: x= %.4f", getNome(), getTermineNoto());
		else if(getTermineNoto()>EPSILON) 
			return String.format("%s: y= %.4fx + %.4f", getNome(), getCoefficienteAngolare(), getTermineNoto());
		else 
			return String.format("%s: y= %.4fx", getNome(), getCoefficienteAngolare());
	}

	public boolean appartiene(Punto p)
	 {
	  if (!verticale)
		 return ( p.getY() == (coefficienteAngolare*p.getX() + termineNoto));
	  else
		 return (termineNoto== p.getX());
	 }
	
	public String belAppartiene(Punto p) {
		if(appartiene(p))
			return "Il punto "+p.getNome()+" appartiene alla retta "+getNome();
		return "Il punto "+p.getNome()+" NON appartiene alla retta "+getNome();
	}

}
