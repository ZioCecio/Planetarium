package center.of.mass;

public class Position {
	private static final String FRAME_1= "(";
	private static final String FRAME_2=")";
	private static final String COMMA=",";
	private double x;
	private double y;
	/*
	 * Nota bene: tutte le posizioni sono ricavate
	 * prendendo come punto di riferimento 
	 * il sole in posizione (0,0)
	 * 
	 */
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String toString() {
		return FRAME_1+getX()+COMMA+getY()+FRAME_2;
	}
	
}
