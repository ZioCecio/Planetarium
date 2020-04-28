package center.of.mass;

public class Position {

	/*
	 * Nota bene: tutte le posizioni sono ricavate prendendo come punto di
	 * riferimento il sole in posizione (0,0)
	 * 
	 */

	private static final String FRAME_1 = "(";
	private static final String FRAME_2 = ")";
	private static final String COMMA = ",";

	private double x;
	private double y;

	/**
	 * constructor using the 2 parameters
	 * 
	 * @param
	 * @author Simone
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * return the {@linkplain #x} attribute
	 * 
	 * @author Alessandra
	 */
	public double getX() {
		return x;
	}

	/**
	 * set {@linkplain #x} attribute
	 * 
	 * @param x to set
	 * @author Alessandra
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * return the {@linkplain #y} attribute
	 * 
	 * @author Alessandra
	 */
	public double getY() {
		return y;
	}

	/**
	 * set {@linkplain #y} attribute
	 * 
	 * @param y to set
	 * @author Alessandra
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * <h1>toString method</h1> the {@linkplain String} return is like
	 * ({@linkplain #x},{@linkplain #y})
	 * 
	 * @author Alessandra
	 */
	public String toString() {
		return FRAME_1 + getX() + COMMA + getY() + FRAME_2;
	}

	/**
	 * Check if two {@code Position} are the same
	 * @param position
	 * @return {@code true} if {@code this} position is equals as that passed as parameter
	 */
	public boolean equals(Position position) {
		return this.x == position.x && this.y == position.y;
	}

}
